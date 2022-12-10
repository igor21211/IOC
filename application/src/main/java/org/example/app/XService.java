package org.example.app;

import lombok.AllArgsConstructor;
import org.example.container.annotations.Configuration;

import java.util.List;
@AllArgsConstructor
public class XService {

   private List<String> users;
    private XSender xSender;

   public void sendAll(String message){
       for(String user : users){
           xSender.send(user,message);
       }
   }
}
