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
    public void onOpen(Session session) {
        var userProperties = session.getUserProperties();
        String username = (String) userProperties.get("Username");
        String authToken = (String) userProperties.get("AuthToken");
        boolean authenticated = authenticationService.validateAuthToken(authToken, username);

        if (!authenticated) {
            try {
                System.out.println("Could not authenticate user, closing websocket connection.");
                session.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error: " + e.getMessage());
            }
        }
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
