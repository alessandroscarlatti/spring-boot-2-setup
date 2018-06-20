package com.scarlatti.processor;

import com.scarlatti.model.FruitType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Tuesday, 6/19/2018
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface FruitProcessorDefinition {
    FruitType value();
}
