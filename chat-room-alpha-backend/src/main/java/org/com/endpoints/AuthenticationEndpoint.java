package org.com.endpoints;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.com.IAuthenticationService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;

public class AuthenticationEndpoint {
    private IAuthenticationService authenticationService;

    public AuthenticationEndpoint(IAuthenticationService authenticationService) {
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

            var headers = exchange.getRequestHeaders();
            if (headers.containsKey("Username") && headers.containsKey("Password")){
                String username = headers.get("Username").getFirst();
                String password = headers.get("Password").getFirst();
            }

            InputStreamReader inputStreamReeader = new InputStreamReader(exchange.getRequestBody());
            try(BufferedReader bufferedReader = new BufferedReader(inputStreamReeader)) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            exchange.close();
        }
    }
}
