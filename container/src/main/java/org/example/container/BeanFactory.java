package org.example.container;

import java.util.List;

public interface BeanFactory {
    <T> List<T> getBeans(Class<T> tClass);
    <T> T getBean(Class<T> tClass);
    Object getBean(String beanId);
}
