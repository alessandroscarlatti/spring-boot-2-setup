package com.scarlatti;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.scarlatti.model.Fruit;
import com.scarlatti.model.Orange;
import com.scarlatti.model.Penguin;
import com.scarlatti.model.Pineapple;
import com.scarlatti.processor.FruitProcessor;
import com.scarlatti.processor.FruitProcessorFactory;
import com.scarlatti.service.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Saturday, 6/9/2018
 */
@SpringBootApplication
public class App implements CommandLineRunner {

    private ApplicationContext applicationContext;
    private FruitProcessorFactory fruitProcessorFactory;
    private static final Logger log = LoggerFactory.getLogger(App.class);

    public App(ApplicationContext applicationContext, FruitProcessorFactory fruitProcessorFactory) {
        this.applicationContext = applicationContext;
        this.fruitProcessorFactory = fruitProcessorFactory;
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("running app.");

        Service service = applicationContext.getBean(Service.class);
        service.serve();

        Service service2 = applicationContext.getBean(Service.class);
        service2.serve();

//        Service service3 = applicationContext.getBean(Service.class, "Asdf");
//        service3.serve();

        processFruit();
        processYml();
    }

    private void processFruit() {
        List<Fruit> fruits = getFruits();
        for (Fruit fruit : fruits) {
            FruitProcessor processor = fruitProcessorFactory.getFruitProcessor(fruit);
            processor.process();
        }

    }

    private List<Fruit> getFruits() {
        return Arrays.asList(
            new Pineapple(),
            new Orange(),
            new Pineapple()
        );
    }

    private void processYml() {
        try {
            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            Penguin penguin = objectMapper.readValue(new Scanner(getClass().getResourceAsStream("/stuff.yml")).useDelimiter("\\Z").next(), Penguin.class);
            log.info("created penguin {}", penguin);
        } catch (Exception e) {
            throw new RuntimeException("Error reading yml", e);
        }
    }
}
