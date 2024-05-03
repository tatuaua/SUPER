package com.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

public class SUPERStreamClient {
    Socket socket;

    public boolean connect(String address, int port){
        try {
            socket = new Socket();
            InetAddress addr = InetAddress.getByName(address);
            SocketAddress sockaddr = new InetSocketAddress(addr, port);
            socket.connect(sockaddr);
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
            return false;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public void receive() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        long startTime = System.currentTimeMillis();
        int updatesReceived = 0;
        String streamed = "";
    
        while (System.currentTimeMillis() - startTime < 10000) {
            System.out.println(streamed);
            streamed = reader.readLine();
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            updatesReceived++;
        }
    
        double updatesPerSecond = (double) updatesReceived / 10;
        System.out.println("Updates per second: " + updatesPerSecond);
    }
}

class Main3{
    public static void main(String[] args) throws Exception {
        SUPERStreamClient client = new SUPERStreamClient();
        client.connect("localhost", 5003);
        client.receive();
    }
}
