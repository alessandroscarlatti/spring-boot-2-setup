package com.scarlatti;

import com.scarlatti.model.FruitType;
import com.scarlatti.model.Orange;
import com.scarlatti.model.Pineapple;
import com.scarlatti.processor.FruitProcessorFactory;
import com.scarlatti.processor.OrangeProcessor;
import com.scarlatti.processor.PineappleProcessor;
import com.scarlatti.service.WasherService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Wednesday, 6/13/2018
 */
@Configuration
public class FruitProcessorConfig {

    private WasherService washerService;

    public FruitProcessorConfig(WasherService washerService) {
        this.washerService = washerService;
    }

    @Bean
    FruitProcessorFactory fruitProcessorFactory() {
        return FruitProcessorFactory
            .emptyFactory()
            .associate(FruitType.ORANGE, this::orangeProcessorCreator)
            .associate(FruitType.PINEAPPLE, this::pineappleProcessorCreator)
            .build();
    }

    public PineappleProcessor pineappleProcessorCreator(Pineapple fruit) {
        return new PineappleProcessor(washerService, fruit);
    }

    public OrangeProcessor orangeProcessorCreator(Orange fruit) {
        return new OrangeProcessor(washerService, fruit);
    }
}
