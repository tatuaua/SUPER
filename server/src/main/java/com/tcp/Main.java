package com.tcp;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        SUPERServer server = new SUPERServer();
        server.addEndpoint("bananas", new BananaEndpoint());
        server.open(5002);
    }
}