package org.com.endpoints;

import jakarta.websocket.OnMessage;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;

@ServerEndpoint("/echo")
public class MessageEndpoint {
    @OnMessage
    public void onMessage(Session session, String message) {
        System.out.println("Received message: " + message);
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            System.out.println(message);
        }
    }
}
