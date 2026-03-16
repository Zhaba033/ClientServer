package com.mycompany.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;

public class Server {
    
    public static boolean debug(String s) {
        System.out.println("[" + LocalTime.now().toString().split("[.]")[0] + "] " + s);
        return true;
    }

    public static void main(String[] args) throws Exception {
        debug("Preparing to start...");
        int p = 11111;
        debug("Port: " + p);
        try (ServerSocket serverSocket = new ServerSocket(p)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                String clientAddress = clientSocket.getInetAddress().getHostAddress();
                debug("New connection with client: " + clientAddress);
                ConnectionProcessing.newConnection(clientSocket, clientAddress);
            }
        }
    }
    
}
