package com.tcp.HTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Random;

public class MyHTTPClient {

    public static void main(String[] args) throws IOException, URISyntaxException {
        String url = "http://localhost:8000/post";
        String postData = new String(generateRandomChars(25000));

        long start = System.nanoTime();

        URL obj = new URI(url).toURL();
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("POST");
        con.setDoOutput(true);
        try (OutputStream os = con.getOutputStream()) {
            byte[] postDataBytes = postData.getBytes();
            os.write(postDataBytes);
            os.flush();
        }

        int responseCode = con.getResponseCode();
        System.out.println("Response Code : " + responseCode);

        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            long end = System.nanoTime();

            System.out.println(response.toString());
            System.out.println("Time spent: " + (end - start));
        }
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
