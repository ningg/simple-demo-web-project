package top.ningg.java.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketOfServer {

	public SocketOfServer() throws IOException {

		int clientNum = 0;
		
		ServerSocket server = null;
		server = new ServerSocket(7777);
		System.out.println("Server started.");

		while (true) {
			clientNum++;
			
			Socket socket = server.accept();
			new ServerHandler(socket).start();
			
			System.out.println("Client Num is: " + clientNum);
		}

	}
	
	public static void main(String[] args) throws IOException {
		new SocketOfServer();
	}
}
