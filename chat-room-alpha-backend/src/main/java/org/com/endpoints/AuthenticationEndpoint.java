package org.com.endpoints;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.com.auth.AuthenticationService;

import java.io.*;
import java.net.InetSocketAddress;

public class AuthenticationEndpoint {
    private AuthenticationService authenticationService;

    public AuthenticationEndpoint(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
    
    public void start() {
        try {
            HttpServer httpServer = HttpServer.create(new InetSocketAddress("localhost", 8081), 0);
            httpServer.createContext("/login", new LoginHttpHandler());
            httpServer.start();

            System.out.println("Auth http server started.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private class LoginHttpHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange){

            //TODO beolvassa a username + jelszot, lemegy a db-be megnezi van-e ilyen,
            // ha igen general egy tokent, berakja a db-be es visszakuldi a kliensnek
            var headers = exchange.getRequestHeaders();
            String username = null;
            String password = null;
            if (headers.containsKey("Username") && headers.containsKey("Password")){
               username = headers.get("Username").getFirst();
               password = headers.get("Password").getFirst();
            }

            try(OutputStream os = exchange.getResponseBody()) {
                if  (!"szilard99".equals(username) || !"abc123".equals(password)) {
                    exchange.sendResponseHeaders(401, 0);
                } else {
                    String response = "authenticated";
                    exchange.sendResponseHeaders(200, response.getBytes().length);
                    os.write(response.getBytes());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
