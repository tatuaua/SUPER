package com.tcp.example;

import com.tcp.SUPERClient;
import com.tcp.SUPERRequest;
import com.tcp.SUPERResponse;

public class Client {
    public static void main(String[] args) throws Exception {

        SUPERClient client = new SUPERClient();
        client.connect("localhost", 5002);
        SUPERRequest req = new SUPERRequest();
        req.build("/", 0, null);
        SUPERResponse response = client.makeRequest(req);

        System.out.println("Response: ");
        System.out.println(response.getResponseBody());
    }
}
