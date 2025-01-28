package com.tcp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Base64;

public class SUPERClient {

    private DatagramSocket socket;
    private InetAddress serverAddress;
    private int serverPort;

    public boolean connect(String address, int port) {
        try {
            socket = new DatagramSocket();
            serverAddress = InetAddress.getByName(address);
            serverPort = port;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public SUPERResponse makeRequest(SUPERRequest request) throws Exception {
        try {
            // Prepare the request message
            String encodedString = Base64.getEncoder().encodeToString(request.getRequestBody().getBytes());
            String requestString = request.getEndpointName() + ";" + request.getRequestType() + ";" + encodedString;
            byte[] requestBytes = requestString.getBytes();

            if(requestBytes.length > 1024) {
                throw new Exception("Request is too large");
            }

            // Send the request to the server
            DatagramPacket requestPacket = new DatagramPacket(requestBytes, requestBytes.length, serverAddress, serverPort);
            socket.send(requestPacket);

            // Prepare to receive the response
            byte[] buffer = new byte[1024];
            DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(responsePacket);

            // Decode the response
            String responseString = new String(responsePacket.getData(), 0, responsePacket.getLength());
            return new SUPERResponse(responseString);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Error making request", ex);
        } finally {
            socket.close();
        }
    }
}
