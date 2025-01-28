package com.tcp.example;

import com.tcp.SUPERServer;

public class Server {
    public static void main(String[] args) throws Exception {

        SUPERServer server = new SUPERServer();
        server.addEndpoint("/", new Endpoint());
        server.open(5002);
    }
}