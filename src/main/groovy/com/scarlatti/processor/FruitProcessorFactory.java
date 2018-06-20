package com.scarlatti.processor;

import com.scarlatti.model.Fruit;
import com.scarlatti.model.FruitType;
import com.scarlatti.model.Orange;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Wednesday, 6/13/2018
 */
public class FruitProcessorFactory {

    // search a list of factories to find the right one
    // use the factory to get a fruit processor.

    private List<FruitProcessorCreator> factories;

    public FruitProcessorFactory(List<FruitProcessorCreator> factories) {
        this.factories = factories;
    }

    public FruitProcessor getFruitProcessor(Fruit fruit) {
        FruitProcessorCreator factory = getFruitProcessorFactory(fruit);
        return factory.build(fruit);
    }

    protected FruitProcessorCreator getFruitProcessorFactory(Fruit fruit) {
        for (FruitProcessorCreator factory : factories) {
            if (factory.handlesFruit(fruit)) {
                return factory;
            }
        }

        throw new RuntimeException("Unable to find factory for fruit " + fruit);
    }

    public static FruitProcessorFactoryBuilder emptyFactory() {
        return new FruitProcessorFactoryBuilder();
    }

    public static class FruitProcessorFactoryBuilder {

        private List<FruitProcessorCreator> creators = new ArrayList<>();

        protected FruitProcessorFactoryBuilder() {
        }

        public FruitProcessorFactoryBuilder withFruitProcessorCreator(FruitProcessorCreator creator) {
            creators.add(creator);
            return this;
        }

        public <I extends Fruit, O extends FruitProcessor> FruitProcessorFactoryBuilder associate(FruitType fruitType, Function<I, O> function) {
            creators.add(new FruitProcessorCreator() {
                @Override
                public FruitType getFruitType() {
                    return fruitType;
                }

                @Override
                @SuppressWarnings("unchecked") // OK because we are counting on the factory working properly!
                public FruitProcessor build(Fruit fruit) {
                    return function.apply((I) fruit);
                }
            });
            return this;
        }

        public FruitProcessorFactoryBuilder withFruitProcessorCreatorConfig(Object o) {
            addFruitProcessorCreatorsFromConfig(o, o.getClass());
            return this;
        }

        private void addFruitProcessorCreatorsFromConfig(Object o, Class<?> clazz) {
            // process the class
            for (Method method : clazz.getMethods()) {
                FruitProcessorDefinition definition = method.getAnnotation(FruitProcessorDefinition.class);
                if (definition == null) continue;

                validateMethod(method);

                creators.add(new FruitProcessorCreator() {

                    @Override
                    public FruitType getFruitType() {
                        return definition.value();
                    }

                    @Override
                    public FruitProcessor build(Fruit fruit) {
                        try {
                            method.setAccessible(true);
                            return (FruitProcessor) method.invoke(o, fruit);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }

            Class<?> superclass = clazz.getSuperclass();
            if (superclass != Object.class)
                addFruitProcessorCreatorsFromConfig(o, superclass);
        }

        private void validateMethod(Method method) {
            Class<?>[] classes = method.getParameterTypes();
            if (classes.length != 1)
                throw new RuntimeException("Illegal method definition for default fruit processor creator: wrong number of args");
            if (!Fruit.class.isAssignableFrom(classes[0]))
                throw new RuntimeException("Illegal method definition for default fruit processor creator: wrong arg type");
            if (!FruitProcessor.class.isAssignableFrom(method.getReturnType()))
                throw new RuntimeException("Illegal method definition for default fruit processor creator: wrong retrun type");
        }

        public FruitProcessorFactory build() {
            return new FruitProcessorFactory(creators);
        }
    }
}
