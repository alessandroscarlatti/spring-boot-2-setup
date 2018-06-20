package com.scarlatti.processor;

import com.scarlatti.model.Fruit;
import com.scarlatti.model.FruitType;

import java.util.function.Function;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Tuesday, 6/19/2018
 */
public class SimpleFruitProcessorCreator<I extends Fruit, O extends FruitProcessor> implements FruitProcessorCreator {

    private FruitType fruitType;
    private Function<I, O> function;

    public SimpleFruitProcessorCreator(FruitType fruitType, Function<I, O> function) {
        this.fruitType = fruitType;
        this.function = function;
    }

    @Override
    public FruitType getFruitType() {
        return fruitType;
    }

    @Override
    @SuppressWarnings("unchecked")  // assume the client will check the class
    public FruitProcessor build(Fruit fruit) {
        return function.apply((I) fruit);
    }
}
