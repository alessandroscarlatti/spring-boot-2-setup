package com.scarlatti

import com.scarlatti.model.Orange
import com.scarlatti.model.Pineapple
import com.scarlatti.processor.FruitProcessor
import com.scarlatti.processor.FruitProcessorFactory
import com.scarlatti.processor.OrangeProcessor
import com.scarlatti.processor.PineappleProcessor
import com.scarlatti.service.WasherService
import spock.lang.Specification

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Saturday, 6/9/2018
 */
class AppTest extends Specification {

    def "factory has correct associations using builder from individual creators"() {
        setup:
            FruitProcessorConfig config = new FruitProcessorConfig(Mock(WasherService))

            FruitProcessorFactory fruitProcessorFactory = FruitProcessorFactory.emptyFactory()
                    .withFruitProcessorCreator(config.orangeProcessorCreator())
                    .withFruitProcessorCreator(config.pineappleProcessorCreator())
                    .build()

        when:
            FruitProcessor orangeProcessor = fruitProcessorFactory.getFruitProcessor(new Orange())
        then:
            orangeProcessor != null
            orangeProcessor instanceof OrangeProcessor

        when:
            FruitProcessor pineappleProcessor = fruitProcessorFactory.getFruitProcessor(new Pineapple())
        then:
            pineappleProcessor != null
            pineappleProcessor instanceof PineappleProcessor
    }

    def "factory has correct associations using builder from config class"() {
        setup:
            FruitProcessorConfig config = new FruitProcessorConfig(Mock(WasherService))

            FruitProcessorFactory fruitProcessorFactory = FruitProcessorFactory.emptyFactory()
                    .withFruitProcessorCreatorConfig(config)
                    .build()

        when:
            FruitProcessor orangeProcessor = fruitProcessorFactory.getFruitProcessor(new Orange())
        then:
            orangeProcessor != null
            orangeProcessor instanceof OrangeProcessor

        when:
            FruitProcessor pineappleProcessor = fruitProcessorFactory.getFruitProcessor(new Pineapple())
        then:
            pineappleProcessor != null
            pineappleProcessor instanceof PineappleProcessor
    }

    def "test reflection"() {
        expect:
            Layer1.class.getMethod("something1").getAnnotations().length == 1
            Layer1.class.getMethod("something2").getAnnotations().length == 1
            Layer2.class.getMethod("something1").getAnnotations().length == 1
            Layer2.class.getMethod("something2").getAnnotations().length == 0

            Layer1.class.getAnnotations().length == 1
            Layer2.class.getAnnotations().length == 1
    }

    @ThisIsInherited
    class Layer1 {

        @ThisIsInherited
        void something1() {
        }

        @ThisIsInherited
        void something2() {
        }
    }

    class Layer2 extends Layer1 {

        @ThisIsInherited
        @Override
        void something1() {
            super.something1()
        }

        @Override
        void something2() {
            super.something2()
        }
    }
}
