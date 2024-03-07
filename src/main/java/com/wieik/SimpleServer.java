package com.wieik;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

public class SimpleServer extends WebSocketServer {

    public SimpleServer(InetSocketAddress address) {
        super(address);
        addShutdownHook();
    }

    private void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down server and disconnecting all clients...");

            // Inform all clients about the shutdown and disconnect them
            this.getConnections().forEach(connection -> {
                connection.send("Server is shutting down. Disconnecting...");
                connection.close(); // This will disconnect the client
            });
        }));
    }


    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        conn.send("Welcome to the server!"); //This method sends a message to the new client
        broadcast( "new connection: " + handshake.getResourceDescriptor() ); //This method sends a message to all clients connected
        System.out.println("new connection to " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println("closed " + conn.getRemoteSocketAddress() + " with exit code " + code + " additional info: " + reason);
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        // Convert the received message to uppercase
        String response = message.toUpperCase();

        // Send the uppercase message back to the client
        conn.send(response);

        // Log the received message and the sent response for debugging purposes
        System.out.println("received message from " + conn.getRemoteSocketAddress() + ": " + message);
        System.out.println("sent response to " + conn.getRemoteSocketAddress() + ": " + response);
    }

    @Override
    public void onMessage( WebSocket conn, ByteBuffer message ) {
        System.out.println("received ByteBuffer from "	+ conn.getRemoteSocketAddress());
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        if(conn == null) {
            System.err.println("an error occurred on connection " + ex);
        } else {
            System.err.println("an error occurred on connection " + conn.getRemoteSocketAddress()  + ":" + ex);
        }
    }

    @Override
    public void onStart() {
        System.out.println("server started successfully");
    }
}