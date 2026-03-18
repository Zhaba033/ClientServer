package com.mycompany.client;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    
    private static String responseProcessing(InputStream responseIS) throws IOException {
        String strResponse = "";

        try (DataInputStream ris = new DataInputStream(responseIS)) {
            
            int typeOfResponse = (int) ris.readByte();
            
            switch (typeOfResponse) {
                case 0:
                    strResponse = "Null response: server error";
                    break;
                case 1:
                    int messageLen = ris.readInt();
                    strResponse = new String(ris.readNBytes(messageLen));
                    break;
                case 2:
                    int nameLength = ris.readShort();
                    String name = new String(ris.readNBytes(nameLength));
                    File f = new File(name);
                    String[] nameSep = name.split("[.]");
                    int i = 1;
                    while (f.exists()) {
                        f = new File(nameSep[0] + Integer.toString(i) + "." + nameSep[1]);
                        i += 1;
                    }
                    long FileLength = ris.readLong();
                    int maxInt = Integer.MAX_VALUE;

                    try (FileOutputStream fos = new FileOutputStream(f)) {
                        while (FileLength > maxInt) {
                            fos.write(ris.readNBytes(maxInt));
                            FileLength -= maxInt;
                        }
                        fos.write(ris.readNBytes((int) FileLength));
                        strResponse = "File succesfully created";
                    }
            }

        }

        return strResponse;
    }

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        String host;
        int p;

        System.out.println("\"connect {server} {port}\" to connect, \"exit\" to exit.");
        String line = scanner.nextLine();
        
        String[] request = line.split("[ ]");
        
        if (request.length > 2 && request[0].equals("connect")) {
            host = request[1];
            p = Integer.parseInt(request[2]);

            try (Socket socket = new Socket(host, p)) {
                System.out.println("Connected to server \"" + host + "\" on port " + p);
                
                try (OutputStream out = socket.getOutputStream(); InputStream in = socket.getInputStream()) {

                    while (scanner.hasNextLine()) {

                        if (line.equalsIgnoreCase("exit")) {
                            break;
                        }

                        line = scanner.nextLine() + "\r\n";
                        
                        out.write(line.getBytes());

                        System.out.println(responseProcessing(in));
                    }
                    System.out.println("Connection closing");
                }
            }
        } else {
            System.out.println("Too few data");
        }

    }
}
