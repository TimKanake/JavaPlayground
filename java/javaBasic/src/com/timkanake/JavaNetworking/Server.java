package com.timkanake.JavaNetworking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server extends Thread{
	@SuppressWarnings("resource")
	public void run() {
		ServerSocket serverSocket = null;
		Socket socket = null;
		try {
			serverSocket = new ServerSocket(5003);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		while(true) {
			try {
				socket = serverSocket.accept();
			}catch(IOException e) {
			}
			new EchoThread(socket).start();
		}
	}

}
