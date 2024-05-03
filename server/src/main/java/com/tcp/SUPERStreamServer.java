package com.tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class SUPERStreamServer {

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

    class ServerThread extends Thread {
        private Socket socket;
     
        public ServerThread(Socket socket) {
            this.socket = socket;
        }
     
        public void run() {
            try {
                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);
                
                long start = System.nanoTime();
                long interval = 1000000000L / 60;
                
                while (!socket.isClosed()) {
                    long curr = System.nanoTime();
                    if (curr - start > interval) {
                        String toSend = getRandomString();
                        writer.println(toSend);
                        start = curr;
                    }
                }
            } catch (Exception ex) {
                System.out.println("Server exception: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    public String getRandomString(){
        char[] chars = new char[20];
        Random rand = new Random();
        int randomNum = rand.nextInt(20);
        for(int i = 0; i < chars.length; i++){
            if(i == randomNum){
                chars[i] = 'O';
            } else {
                chars[i] = 'X';
            }
        }
        return new String(chars);
    }
}

class Main2{
    public static void main(String[] args) throws IOException {
        SUPERStreamServer server = new SUPERStreamServer();
        server.open(5003);
    }
}