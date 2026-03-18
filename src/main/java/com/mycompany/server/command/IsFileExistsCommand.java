package com.mycompany.server.command;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class IsFileExistsCommand extends AbstractCommand {
    
    @Override
    protected void doAction(ByteArrayOutputStream bos) throws IOException {
        File f = new File(request);
        String localResponse;
        if (f.exists()) {
            localResponse = "File " + request + " exists";
        } else {
            localResponse = "Noooo, there is no such file as " + request;
        }
        
        StringResponse(bos, localResponse);

    }
    
}
