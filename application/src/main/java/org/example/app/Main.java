package org.example.app;

import org.example.container.AnnotationBasedApplicationContext;
import org.example.container.ApplicationContext;
import org.example.container.utils.PackageScanner;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationBasedApplicationContext(Main.class);
        XService service = context.getBean(XService.class);


    }
}
