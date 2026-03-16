package com.mycompany.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import com.mycompany.server.command.CommandHead;

public class ConnectionProcessing{
    
    public static void newConnection(Socket socket, String address) throws Exception {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                Server.debug("Request from " + address + ": " + inputLine);
                CommandHead ch = new CommandHead();
                String response = new String(ch.command(inputLine));
                Server.debug("Response: " + response);
            }
        }
    }
    
}
