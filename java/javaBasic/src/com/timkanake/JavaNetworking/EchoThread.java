package com.timkanake.JavaNetworking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class EchoThread extends Thread{
	
	protected Socket socket;
	CommandsQueue cQ = CommandsQueue.getInstance();
	
	public EchoThread(Socket clientSocket) {
		socket = clientSocket;
	}
	
	public void run() {
		InputStream inp = null;
        BufferedReader brinp = null;
        try {
            inp = socket.getInputStream();
            brinp = new BufferedReader(new InputStreamReader(inp));
        } catch (IOException e) {
            return;
        }
        
        String line;
        while (true) {
            try {
                line = brinp.readLine();
                if ((line == null) || line.equalsIgnoreCase("QUIT")) {
                    socket.close();
                    return;
                } else {
                	CommandsQueue.getInstance().commands.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }

}
