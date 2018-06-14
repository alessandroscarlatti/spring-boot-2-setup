package com.scarlatti;

import com.scarlatti.service.Service;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Saturday, 6/9/2018
 */
@SpringBootTest
public class ServiceTest {

    @Test
    public void serviceTest() {
        new Service("asdf").serve();
    }
}
