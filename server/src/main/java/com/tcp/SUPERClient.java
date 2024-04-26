package com.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SUPERClient {

    Socket socket;

    public boolean connect(String address, int port){
        try {
            socket = new Socket(address, port);
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
        return true;
    }

    public SUPERResponse makeRequest(SUPERRequest request) throws IOException{
        OutputStream output = socket.getOutputStream();
        PrintWriter writer = new PrintWriter(output, true);

        writer.println(request.raw());

        InputStream input = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        String responseString = reader.readLine();

        socket.close();

        System.out.println("resp str: " + responseString);

        SUPERResponse response = new SUPERResponse(responseString);

        return response;
    }
    
}
