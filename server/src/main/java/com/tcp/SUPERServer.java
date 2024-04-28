package com.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class SUPERServer {

    HashMap<String, SUPEREndpoint> endPoints = new HashMap<>();

    public void open(int port) throws IOException{

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {           
                Socket socket = serverSocket.accept();
                new ServerThread(socket).start();
            }

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void addEndpoint(String endPointName, SUPEREndpoint handler){
        this.endPoints.put(endPointName, handler);
    }

    class ServerThread extends Thread {
        private Socket socket;
     
        public ServerThread(Socket socket) {
            this.socket = socket;
        }
     
        public void run() {
            try {
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            
                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);
            
                String text = reader.readLine();
                SUPERRequest req = new SUPERRequest();
                if(!req.parse(text)){
                    writer.println("3;Invalid request");
                }
            
                SUPEREndpoint endpoint = endPoints.get(req.getEndpointName());
                if(endpoint == null){
                    writer.println("3;Endpoint not found");
                }
                
                int requestType = req.getRequestType();
                switch (requestType){
                    case 0:
                        writer.println(endpoint.get().raw());
                        break;     
                    case 1:
                        writer.println(endpoint.post(req.getRequestBody()).raw());
                        break;
                    default:
                        writer.println("3;Invalid request type (must be 0 or 1)");
                        break;
                }
            } catch (IOException ex) {
                System.out.println("Server exception: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}