package org.com;

import jakarta.websocket.DeploymentException;
import org.com.endpoints.MessageEndpoint;
import org.glassfish.tyrus.server.Server;

public class WebsocketServer implements Runnable {
    @Override
    public void run() {
        Server websocketServer = new Server("localhost", 8080, "/ws", null, MessageEndpoint.class);
        try {
            websocketServer.start();
            System.out.println("Server started.");
            while(true) {
                //TODO jobb varakozasi mod?
            }
        } catch (DeploymentException e) {
            System.out.println("Deployment exception");
        } finally {
            websocketServer.stop();
            System.out.println("Server stopped");
        }
    }

    public void startOnNewThread() {
        Thread thread = new Thread(this);
        thread.start();
    }
}
