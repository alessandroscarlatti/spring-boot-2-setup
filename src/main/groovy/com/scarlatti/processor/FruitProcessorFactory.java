package com.scarlatti.processor;

import com.scarlatti.model.Fruit;
import com.scarlatti.model.FruitType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Wednesday, 6/13/2018
 */
public class FruitProcessorFactory {

    // search a list of definitions to find the right one
    // use the factory to get a fruit processor.

    private List<FruitProcessorDefinition> definitions;

    public FruitProcessorFactory(List<FruitProcessorDefinition> definitions) {
        this.definitions = definitions;
    }

    public FruitProcessor getFruitProcessor(Fruit fruit) {
        FruitProcessorDefinition factory = getFruitProcessorFactory(fruit);
        return factory.build(fruit);
    }

    private FruitProcessorDefinition getFruitProcessorFactory(Fruit fruit) {
        for (FruitProcessorDefinition creator : definitions) {
            if (creator.handlesFruit(fruit)) return creator;
        }

        throw new RuntimeException("Unable to find factory for fruit " + fruit);
    }

    public static FruitProcessorFactoryBuilder emptyFactory() {
        return new FruitProcessorFactoryBuilder();
    }

    public static class FruitProcessorFactoryBuilder {

        private List<FruitProcessorDefinition> creators = new ArrayList<>();

        private FruitProcessorFactoryBuilder() {
        }

        public FruitProcessorFactoryBuilder withFruitProcessorCreator(FruitProcessorDefinition creator) {
            creators.add(creator);
            return this;
        }

        public <I extends Fruit, O extends FruitProcessor> FruitProcessorFactoryBuilder associate(FruitType fruitType, Function<I, O> function) {
            creators.add(new FruitProcessorDefinition() {
                @Override
                public FruitType getFruitType() {
                    return fruitType;
                }

                @Override
                @SuppressWarnings("unchecked")  // assume the client will check the class
                public FruitProcessor build(Fruit fruit) {
                    return function.apply((I) fruit);
                }
            });
            return this;
        }

        public FruitProcessorFactory build() {
            return new FruitProcessorFactory(creators);
        }
    }
}
