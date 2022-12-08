package org.example.app;

import java.util.List;

public class XService {

   private List<String> users;
    private XSender xSender;

   public void sendAll(String message){
       for(String user : users){
           xSender.send(user,message);
       }
   }
}
