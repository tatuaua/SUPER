package com.tcp;

import java.io.IOException;
import java.util.Random;

public class Client {
    public static void main(String[] args) throws Exception {
        String hostname = "localhost";
        int port = 5002;

        SUPERClient client = new SUPERClient();

        client.connect(hostname, port);

        SUPERRequest req = new SUPERRequest();
        req.build("/", 1, "From cleint");

        SUPERResponse response = new SUPERResponse();

        try {
            response = client.makeRequest(req);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(response.getResponseBody());
    }


    public static char[] generateRandomChars(int numChars) {
        Random random = new Random();
        char[] allowedChars = getAllowedCharacters();
        char[] randomChars = new char[numChars];

        for (int i = 0; i < numChars; i++) {
            randomChars[i] = allowedChars[random.nextInt(allowedChars.length)];
        }

        return randomChars;
    }

    private static char[] getAllowedCharacters() {
        StringBuilder stringBuilder = new StringBuilder();
        for (char c = Character.MIN_VALUE; c < Character.MAX_VALUE; c++) {
            if (c != ';') {
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString().toCharArray();
    }
}
