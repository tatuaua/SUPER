package com.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SUPERServer {

    SUPERHandler handler;

    public void open(int port) throws IOException{

        try (ServerSocket serverSocket = new ServerSocket(port)) {
 
            System.out.println("Server is listening on port " + port);
 
            while (true) {
                Socket socket = serverSocket.accept();
 
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
 
                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);
 
                String text = reader.readLine();

                SUPERRequest req = new SUPERRequest(text);

                if(req.getRequestType() == 0){
                    writer.println(handler.get(req.getRequestBody()));
                } else if (req.getRequestType() == 1){
                    writer.println(handler.post(req.getRequestBody()));
                }

                socket.close();
            }
 
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void addHandler(SUPERHandler handler){
        this.handler = handler;
    }
}
