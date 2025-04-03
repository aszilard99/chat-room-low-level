package org.com.endpoints;

import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import org.com.AuthenticationService;
import org.com.endpoints.config.WebsocketServerConfigurator;

import java.io.IOException;

@ServerEndpoint(value = "/echo", configurator = WebsocketServerConfigurator.class)
public class MessageEndpoint {

    private final AuthenticationService authenticationService;

    public MessageEndpoint(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @OnOpen
    public void onOpen() {

    }

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
