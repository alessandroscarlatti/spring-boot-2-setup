package com.scarlatti;

import com.scarlatti.model.FruitType;
import com.scarlatti.processor.FruitProcessorFactory;
import com.scarlatti.service.Service;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Monday, 6/11/2018
 */
@Configuration
public class Config {

    private static int count = 0;

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public Service service() {
        count++;
        return new Service(String.valueOf(count));
    }
}
