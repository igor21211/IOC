package org.example.app;

import org.example.container.annotations.Component;

@Component
public class XSender {
    void send(String email, String message){
        System.out.println("send message "+ message+ " to " + email);
    }
}
