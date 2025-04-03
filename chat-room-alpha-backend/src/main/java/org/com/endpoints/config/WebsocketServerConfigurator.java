package org.com.endpoints.config;

import jakarta.websocket.server.ServerEndpointConfig;
import org.com.AuthenticationService;
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
}
