package org.com.endpoints.config;

import jakarta.websocket.HandshakeResponse;
import jakarta.websocket.server.HandshakeRequest;
import jakarta.websocket.server.ServerEndpointConfig;
import org.com.auth.AuthenticationService;
import org.com.endpoints.MessageEndpoint;

public class WebsocketServerConfigurator extends ServerEndpointConfig.Configurator {

    private static AuthenticationService authenticationService;

    @Override
    public <T> T getEndpointInstance(Class<T> endpointClass) {
        return endpointClass.cast(new MessageEndpoint(authenticationService));
    }

    public static void setAuthenticationService(AuthenticationService authService) {
        authenticationService = authService;
    }

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {

        var username = request.getHeaders().get("Username");
        var authToken = request.getHeaders().get("AuthToken");
        if (username != null && !username.isEmpty()) {
            sec.getUserProperties().put("Username", username.getFirst());
        }
        if (authToken != null && !authToken.isEmpty()) {
            sec.getUserProperties().put("AuthToken", authToken.getFirst());
        }
        super.modifyHandshake(sec, request, response);
    }
}
