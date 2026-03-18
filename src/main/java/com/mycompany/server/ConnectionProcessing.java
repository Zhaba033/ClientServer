package com.mycompany.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import com.mycompany.server.command.CommandHead;
import java.io.OutputStream;

public class ConnectionProcessing{
    
    public static void newConnection(Socket socket, String address) throws Exception {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); OutputStream os = socket.getOutputStream();) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                Server.debug("Request from " + address + ": " + inputLine);

                CommandHead ch = new CommandHead();
                byte[] responseBytes = ch.command(inputLine);
                //String response = new String(responseBytes);
                Server.debug("Response send");
                os.write(responseBytes);
                
            }
        }
    }
    
}
