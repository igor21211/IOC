package org.example.container;

import org.example.container.annotations.Bean;

import java.util.List;

public class DefaultBeanFactory implements BeanFactory {



    @Override
    public <T> List<T> getBeans(Class<T> tClass) {
        return null;
    }

    @Override
    public <T> T getBean(Class<T> tClass) {
        return null;
    }

    @Override
    public Object getBean(String beanId) {
        return null;
    }
}
