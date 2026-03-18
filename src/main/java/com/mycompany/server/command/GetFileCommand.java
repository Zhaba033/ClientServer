package com.mycompany.server.command;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;



public class GetFileCommand extends AbstractCommand{
    
    @Override
    protected void doAction(ByteArrayOutputStream bos) throws IOException {

        File f = new File(request);
        
        if (!f.exists() || f.isDirectory()) {
            StringResponse(bos, "Error: file does not exist or it is the directory");
        
        }
        DataOutputStream dos = new DataOutputStream(bos);
        
        dos.write((byte) 2);
        
        String fName = f.getName();
        short nameLength = (short) fName.length();
        byte[] fBytes = Files.readAllBytes(Paths.get(f.getAbsolutePath()));
        long fileLength = (long) fBytes.length;
        
        dos.writeShort(nameLength);
        dos.write(fName.getBytes());
        dos.writeLong(fileLength);
        dos.write(fBytes);
        
    }
    
}
