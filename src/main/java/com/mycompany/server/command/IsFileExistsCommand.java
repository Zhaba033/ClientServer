package com.mycompany.server.command;

import java.io.File;

public class IsFileExistsCommand extends AbstractCommand {
    
    @Override
    protected byte[] run() {
        
        File f = new File(request);
        String localResponse;
        if (f.exists()) {
            localResponse = "File " + request + " exists";
        } else {
            localResponse = "Noooo, there is no " + request;
        }
        
        return localResponse.getBytes();
    }
    
}
