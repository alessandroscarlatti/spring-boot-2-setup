package com.scarlatti;

import com.scarlatti.model.Fruit;
import com.scarlatti.model.FruitType;
import com.scarlatti.model.Orange;
import com.scarlatti.model.Pineapple;
import com.scarlatti.processor.FruitProcessor;
import com.scarlatti.processor.FruitProcessorCreator;
import com.scarlatti.processor.OrangeProcessor;
import com.scarlatti.processor.PineappleProcessor;
import com.scarlatti.service.WasherService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Wednesday, 6/13/2018
 */
@Component
public class FruitProcessorConfig {

    private WasherService washerService;

    public FruitProcessorConfig(WasherService washerService) {
        this.washerService = washerService;
    }

    public PineappleProcessor pineappleProcessorCreator(Pineapple fruit) {
        return new PineappleProcessor(washerService, fruit);
    }

    public OrangeProcessor orangeProcessorCreator(Orange fruit) {
        return new OrangeProcessor(washerService, fruit);
    }

//    @Bean
//    public FruitProcessorCreator pineappleProcessorCreator() {
//        return new FruitProcessorCreator() {
//
//            @Override
//            public FruitType getFruitType() {
//                return FruitType.PINEAPPLE;
//            }
//
//            @Override
//            public FruitProcessor build(Fruit fruit) {
//                return new PineappleProcessor(washerService, (Pineapple) fruit);
//            }
//        };
//    }
//
//    @Bean
//    public FruitProcessorCreator orangeProcessorCreator() {
//        return new FruitProcessorCreator() {
//
//            @Override
//            public FruitType getFruitType() {
//                return FruitType.ORANGE;
//            }
//
//            @Override
//            public FruitProcessor build(Fruit fruit) {
//                return new OrangeProcessor(washerService, (Orange) fruit);
//            }
//        };
//    }
}
