package org.example.container.beans.registry;

import org.example.container.beans.BeanDefinition;

import java.util.List;
import java.util.Set;

public interface BeanDefinitionRegistry {
    void registerBeanDefinition(BeanDefinition definition);
    List<BeanDefinition> find(Class clazz);
    BeanDefinition find(String id);
    Set<String> getBeansIds();
}
