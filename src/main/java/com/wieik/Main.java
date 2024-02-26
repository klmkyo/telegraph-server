package com.wieik;

import org.glassfish.tyrus.server.Server;

import javax.websocket.DeploymentException;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Server server = new Server("localhost", 8080, "/", null, Socket.class);

        try {
            server.start();
            System.out.println("WebSocket server started.");
            Thread.sleep(Long.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            server.stop();
        }
    }
}