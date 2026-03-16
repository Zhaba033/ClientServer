package com.mycompany.server.command;

import java.util.HashMap;
import java.util.Map;

public class CommandHead {
    IsFileExistsCommand ists = new IsFileExistsCommand();
    
    
    Map<String, AbstractCommand> commands = new HashMap<>(Map.of(
            "isExists", ists
    ));
    
    
    public byte[] command(String request) {
        byte[] response = "Nothing".getBytes();
        
        String[] requestList = request.split("[ ]");
        
        if (requestList.length > 1) {
            response = commands.get(requestList[0]).input(requestList[1]);
        }
        
        return response;
    }
    
}
