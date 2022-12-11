package org.example.app;

import org.example.app.data.Person;
import org.example.container.AnnotationBasedApplicationContext;
import org.example.container.ApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationBasedApplicationContext(Main.class);
        Menu menu = context.getBean(Menu.class);
        menu.start();
    }
}
