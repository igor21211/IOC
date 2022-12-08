package org.example.container;

import lombok.RequiredArgsConstructor;
import org.example.container.utils.PackageScanner;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AnnotationBeanDefinitionReader implements BeanDefinitionReader {

    private final String packageName;
    private final PackageScanner packageScanner;

    @Override
    public List<BeanDefinition> read() {
       return  packageScanner.find(packageName).stream()
                .filter(c->c.isAnnotationPresent(Component.class))
                .map(this::toBeanDefinition)
                .collect(Collectors.toList());
    }

    private BeanDefinition toBeanDefinition(Class aClass) {
        return new BeanDefinition(
                aClass.getSimpleName(),
                aClass,
                null
        );
    }
}
