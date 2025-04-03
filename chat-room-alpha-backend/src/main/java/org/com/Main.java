package org.com;

import org.com.endpoints.AuthenticationEndpoint;

public class Main {
    public static void main(String[] args) {
        AuthenticationService authenticationService = new MockAuthenticationService();

        WebsocketServer websocketServer = new WebsocketServer(authenticationService);
        websocketServer.startOnNewThread();

        var authHttpServer = new AuthenticationEndpoint(authenticationService);
        authHttpServer.start();
    }
}