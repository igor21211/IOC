package org.example.container;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.container.annotations.Bean;
import org.example.container.beans.BeanDefinition;
import org.example.container.beans.registry.BeanDefinitionRegistry;

import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class DefaultBeanFactory implements BeanFactory {

    Map<String, Object> beans = new HashMap<>();
    private final BeanDefinitionRegistry beanDefinitionRegistry;

    @Override
    public <T> List<T> getBeans(Class<T> tClass) {
        return beanDefinitionRegistry.find(tClass)
                .stream().map(BeanDefinition::getId)
                .map(this::getBean)
                .map(o->(T)o)
                .collect(Collectors.toList());
    }

    @Override
    public <T> T getBean(Class<T> tClass) {
        List<String> ids = beanDefinitionRegistry.find(tClass).stream()
                .map(BeanDefinition::getId)
                .collect(Collectors.toList());
        if(ids.size()>1 || ids.size() == 0){
            throw new RuntimeException("Cannot find candidate for "+ tClass.getName());
        }
        return (T) getBean(ids.get(0));
    }


    @Override
    public Object getBean(String beanId) {
        if (beans.containsKey(beanId)) {
            return beans.get(beanId);
        }
        BeanDefinition beanDefinition = beanDefinitionRegistry.find(beanId);
        if (beanDefinition == null) throw new RuntimeException("Beans not find  by id");
        beans.put(beanId, createBean(beanDefinition));
        return beans.get(beanId);
    }

    private Object createBean(BeanDefinition beanDefinition) {
        if (beanDefinition.getFactoryMethod() == null) {
            return createBeanByConstructor(beanDefinition.getClazz());
        } else {
            return createBeanByMethod(beanDefinition.getFactoryMethod());
        }
    }

    @SneakyThrows
    private Object createBeanByMethod(Method factoryMethod) {
        Object factory = getBean(factoryMethod.getDeclaringClass());

        Object[] arguments = resolveArguments(factoryMethod.getParameters());
        factoryMethod.setAccessible(true);
        return factoryMethod.invoke(factory, arguments);
    }

    @SneakyThrows
    private Object createBeanByConstructor(Class clazz) {
        Constructor constructor = clazz.getConstructors()[0];
        Object[] arguments = resolveArguments(constructor.getParameters());
        return constructor.newInstance(arguments);
    }

    @SneakyThrows
    private Object[] resolveArguments(Parameter[] parameters) {
        Object[] objects = new Object[parameters.length];
        int i = 0;
        for (Parameter parameter : parameters) {
            if (List.class.isAssignableFrom(parameter.getType())) {
                Type type = ((ParameterizedType) (parameter.getParameterizedType())).getActualTypeArguments()[0];
                Class clazzOfType = Class.forName(type.getTypeName());
                objects[i++] = getBeans(clazzOfType);
            } else {
                objects[i++] = getBean(parameter.getType());
            }
        }
        return objects;
    }

}
