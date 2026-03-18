package com.mycompany.server.command;

import java.util.HashMap;
import java.util.Map;

public class CommandHead {
    IsFileExistsCommand ifec = new IsFileExistsCommand();
    GetFileCommand gfc = new GetFileCommand();
    
    
    Map<String, AbstractCommand> commands = new HashMap<>(Map.of(
            "isExists", ifec,
            "getFile", gfc
    ));
    
    
    public byte[] command(String request) {
        byte[] response = "Nothing".getBytes();
        
        String[] requestList = request.split("[ ]");
        
        if (requestList.length > 1) {
            response = commands.get(requestList[0]).run(requestList[1]);
        }
        
        return response;
    }
    
}
