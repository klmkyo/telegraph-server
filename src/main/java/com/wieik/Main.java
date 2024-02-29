package com.wieik;

import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        String host = "localhost";
        int port = 8080;

        WebSocketServer server = new SimpleServer(new InetSocketAddress(host, port));
        server.run();
    }
}