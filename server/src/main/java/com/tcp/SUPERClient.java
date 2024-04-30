package com.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.security.PublicKey;

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
        System.out.println("Getting key");

        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        
        PublicKey publicKey = (PublicKey) in.readObject();
        
        System.out.println("Public key received");
        
        SUPEREncryption encryption = new SUPEREncryption(publicKey);
        byte[] encryptedMessage = encryption.encrypt(request.raw());
        
        out.writeObject(encryptedMessage);
        
        System.out.println("Encrypted sent");
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        SUPERResponse response = new SUPERResponse(reader.readLine());
        
        System.out.println("Response read");
        
        socket.close();
        return response;
    }
}
