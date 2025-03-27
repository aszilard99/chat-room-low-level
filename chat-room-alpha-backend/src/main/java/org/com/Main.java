package org.com;

import jakarta.websocket.DeploymentException;
import org.com.endpoints.Message;
import org.glassfish.tyrus.server.Server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Server websocketServer = new Server("localhost", 8080, "/ws", null, Message.class);
        try {
            websocketServer.start();
            System.out.println("Server started.");
            System.in.read();
        } catch(DeploymentException e) {
            System.out.println("Deployment exception");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            websocketServer.stop();
            System.out.println("Server stopped");
        }
    }
}