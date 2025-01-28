package com.tcp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Base64;
import java.util.HashMap;

public class SUPERServer {

    HashMap<String, SUPEREndpoint> endPoints = new HashMap<>();

    public void open(int port) throws IOException {
        try (DatagramSocket socket = new DatagramSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                byte[] buffer = new byte[1024];  // Create a new buffer for each packet
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                new ServerThread(socket, packet).start();
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void addEndpoint(String endPointName, SUPEREndpoint handler) {
        this.endPoints.put(endPointName, handler);
    }

    class ServerThread extends Thread {
        private DatagramSocket socket;
        private DatagramPacket packet;

        public ServerThread(DatagramSocket socket, DatagramPacket packet) {
            this.socket = socket;
            this.packet = packet;
        }

        public void run() {
            try {
                InetAddress clientAddress = packet.getAddress();
                int clientPort = packet.getPort();

                String receivedText = new String(packet.getData(), 0, packet.getLength());
                SUPERRequest req = new SUPERRequest();
                if (!req.parse(receivedText)) {
                    String response = "3;Invalid request";
                    sendResponse(response, clientAddress, clientPort);
                    return;
                }

                SUPEREndpoint endpoint = endPoints.get(req.getEndpointName());
                if (endpoint == null) {
                    String response = "3;Endpoint not found";
                    sendResponse(response, clientAddress, clientPort);
                    return;
                }

                byte[] decodedBodyBytes = Base64.getDecoder().decode(req.getRequestBody());
                String decodedBody = new String(decodedBodyBytes);

                int requestType = req.getRequestType();
                String response;
                switch (requestType) {
                    case 0:
                        response = endpoint.get().raw();
                        break;
                    case 1:
                        response = endpoint.post(decodedBody).raw();
                        break;
                    default:
                        response = "3;Invalid request type (must be 0 or 1)";
                        break;
                }

                sendResponse(response, clientAddress, clientPort);
            } catch (Exception ex) {
                System.out.println("Server exception: " + ex.getMessage());
                ex.printStackTrace();
            }
        }

        private void sendResponse(String response, InetAddress clientAddress, int clientPort) throws IOException {
            byte[] responseBytes = response.getBytes();
            DatagramPacket responsePacket = new DatagramPacket(responseBytes, responseBytes.length, clientAddress, clientPort);
            socket.send(responsePacket);
        }
    }
}