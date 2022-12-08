package org.example.container;

import lombok.Value;

import java.lang.reflect.Method;
@Value
public class BeanDefinition {

    String id;
    Class clazz;
    Method factoryMethod;

}
