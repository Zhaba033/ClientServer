package com.mycompany.server.command;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;


/* first byte:

0 - global error
1 - string response
2 - file response

*/

public abstract class AbstractCommand {
    
    String request;
    byte[] response;
    
    protected byte[] GlobalErrorResponse() {
        byte[] type = {(byte) 0};
        return type;
    }
    
    protected void StringResponse(ByteArrayOutputStream bos, String text) throws IOException {
        
        byte[] type = {(byte) 1};
        int messageLen = text.length();
        
        
        DataOutputStream dos = new DataOutputStream(bos);
        dos.write(type);
        dos.writeInt(messageLen);
        dos.write(text.getBytes());
        
    }
    
    public byte[] run(String line) {
        request = line;
        
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            doAction(bos);
            response = bos.toByteArray();
        } catch (IOException e) {
            return GlobalErrorResponse();
        }
        
        return response;
    }
    
    protected void doAction(ByteArrayOutputStream bos) throws IOException {}   
    
}
