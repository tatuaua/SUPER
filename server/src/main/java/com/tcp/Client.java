package com.tcp;

import java.io.IOException;

public class Client {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 5002;

        SUPERClient client = new SUPERClient();

        client.connect(hostname, port);

        SUPERRequest req = new SUPERRequest();
        req.build("/", 0, null);

        SUPERResponse response = new SUPERResponse();

        try {
            response = client.makeRequest(req);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
