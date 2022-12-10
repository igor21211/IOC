package org.example.container;

import org.example.container.beans.reader.AnnotationBeanDefinitionReader;
import org.example.container.beans.reader.AnnotationConfigurationBeanDefinitionReader;
import org.example.container.beans.reader.BeanDefinitionReader;
import org.example.container.beans.reader.CompositeBeanDefinitionReader;
import org.example.container.beans.registry.BeanDefinitionRegistry;
import org.example.container.beans.registry.DefaultBeanDefinitionRegistry;
import org.example.container.utils.PackageScanner;

import java.util.List;

public class AnnotationBasedApplicationContext implements ApplicationContext{

    private String packageName;
    private final BeanDefinitionRegistry beanDefinitionRegistry;
    public AnnotationBasedApplicationContext(Class baseClass){
        packageName = baseClass.getPackageName();
        beanDefinitionRegistry = new DefaultBeanDefinitionRegistry();
        init();
    }

   private void init(){
        BeanDefinitionReader beanDefinitionReader = createBeanDefinitionReader();
        beanDefinitionReader.read().forEach(beanDefinitionRegistry::registerBeanDefinition);
   }

    private BeanDefinitionReader createBeanDefinitionReader() {
        PackageScanner scanner = new PackageScanner();
        BeanDefinitionReader classReader = new AnnotationBeanDefinitionReader(packageName,scanner);
        BeanDefinitionReader configReader = new AnnotationConfigurationBeanDefinitionReader(packageName,scanner);
        return new CompositeBeanDefinitionReader(classReader,configReader);
    }

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
