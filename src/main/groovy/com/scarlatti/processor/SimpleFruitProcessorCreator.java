package com.scarlatti.processor;

import com.scarlatti.model.Fruit;
import com.scarlatti.model.FruitType;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Wednesday, 6/13/2018
 */
public class SimpleFruitProcessorCreator extends FruitProcessorCreator {

    private Function<Fruit, FruitProcessor> fruitProcessorFunction;

    public SimpleFruitProcessorCreator(FruitType fruitType, Function<Fruit, FruitProcessor> fruitProcessorFunction) {
        super(fruitType);
        this.fruitProcessorFunction = fruitProcessorFunction;
    }

    @Override
    public FruitProcessor build(Fruit fruit) {
        return fruitProcessorFunction.apply(fruit);
    }
}
