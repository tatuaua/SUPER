package com.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

public class SUPERClient {

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

    public SUPERResponse makeRequest(SUPERRequest request) throws Exception{
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        writer.println(request.raw());
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String responseString = reader.readLine();
        socket.close();
        SUPERResponse response = new SUPERResponse(responseString);
        return response;
    }
}
