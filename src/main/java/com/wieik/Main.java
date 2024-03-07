package com.wieik;

import org.java_websocket.server.WebSocketServer;
import java.sql.*;
import java.net.InetSocketAddress;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws InterruptedException, SQLException {

        String url = "jdbc:postgresql://localhost:5432/test?user=fred&password=secret&ssl=true";
        Connection conn = DriverManager.getConnection(url);


        String host = "localhost";
        int port = 8080;

        WebSocketServer server = new SimpleServer(new InetSocketAddress(host, port));
        server.run();
    }
}