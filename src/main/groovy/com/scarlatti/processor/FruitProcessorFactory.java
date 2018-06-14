package com.scarlatti.processor;

import com.scarlatti.model.Fruit;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Wednesday, 6/13/2018
 */
@Component
public class FruitProcessorFactory {

    // search a list of factories to find the right one
    // use the factory to get a fruit processor.

    private List<FruitProcessorCreator> factories;

    public FruitProcessorFactory(List<FruitProcessorCreator> factories) {
        this.factories = factories;
    }

    public FruitProcessor getFruitProcessor(Fruit fruit) {
        FruitProcessorCreator factory = getFruitProcessorFactory(fruit);
        return factory.build(fruit);
    }

    protected FruitProcessorCreator getFruitProcessorFactory(Fruit fruit) {
        for (FruitProcessorCreator factory : factories) {
            if (factory.handlesFruit(fruit)) {
                return factory;
            }
        }

        throw new RuntimeException("Unable to find factory for fruit " + fruit);
    }
}
