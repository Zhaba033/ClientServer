package com.mycompany.server.command;


public abstract class AbstractCommand {
    
    String request;
    byte[] response;
    
    public byte[] input(String line) {
        request = line;
        return run();
    }
    
    protected byte[] run() {return response;}
    
    
}
