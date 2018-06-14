package com.scarlatti.processor;

import com.scarlatti.model.Fruit;
import com.scarlatti.model.FruitType;

import java.util.Objects;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Wednesday, 6/13/2018
 */
public abstract class FruitProcessorCreator {

    private FruitType fruitType;

    protected FruitProcessorCreator(FruitType fruitType) {
        this.fruitType = fruitType;
    }

    public boolean handlesFruit(Fruit fruit) {
        Objects.requireNonNull(fruit, "Fruit may not be null");
        return fruit.getType() == fruitType;
    }

    public abstract FruitProcessor build(Fruit fruit);
}
