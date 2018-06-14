package com.scarlatti.processor;

import com.scarlatti.model.Pineapple;
import com.scarlatti.service.WasherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Wednesday, 6/13/2018
 */
public class PineappleProcessor implements FruitProcessor {
    private WasherService washerService;
    private Pineapple pineapple;
    private static final Logger log = LoggerFactory.getLogger(PineappleProcessor.class);


    public PineappleProcessor(WasherService washerService, Pineapple pineapple) {
        this.washerService = washerService;
        this.pineapple = pineapple;
    }

    @Override
    public void process() {
        log.info("In processor {}", this);
        log.info("Processing pineapple {}", pineapple);
    }
}
