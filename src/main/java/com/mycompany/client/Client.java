package com.mycompany.client;

import java.io.OutputStream;
import java.net.Socket;

public class Client {
    
    public static void main(String[] args) throws Exception {
        
        String request = "isExists onefile.txt";
        
        String host = "localhost";
        int p = 11111;
        try (Socket socket = new Socket(host, p)) {
            System.out.println("Connected to server \"" + host + "\" on port " + p);

            try (OutputStream out = socket.getOutputStream()) {
                out.write(request.getBytes());
            }
            
        }
        
    }
}
