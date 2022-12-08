package org.example.app;

import org.example.container.ApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = null;
        XService service = context.getBean(XService.class);
    }
}
