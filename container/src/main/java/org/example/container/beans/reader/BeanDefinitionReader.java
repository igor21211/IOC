package org.example.container.beans.reader;

import org.example.container.beans.BeanDefinition;

import java.util.List;

public interface BeanDefinitionReader {
    public List<BeanDefinition> read();
}
