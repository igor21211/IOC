package org.example.app;

import org.example.container.ApplicationContext;
import org.example.container.utils.PackageScanner;

public class Main {
    public static void main(String[] args) {
//        ApplicationContext context = null;
//        XService service = context.getBean(XService.class);

        PackageScanner packageScanner = new PackageScanner();
        packageScanner.find("org.example").forEach(c-> System.out.println(c.getName()));
    }
}
