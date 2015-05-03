package top.ningg.java.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerHandler extends Thread{

	private Socket socket;
	
	public ServerHandler(Socket socket){
		this.socket = socket;
	}
	
	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			BufferedReader sysin = new BufferedReader(new InputStreamReader(System.in));
			String singleLine = null;
			
			System.out.println("[Client]: " + in.readLine());
			singleLine = sysin.readLine();
			
			while (!"bye".equals(singleLine)) {
				out.println(singleLine);
				out.flush();
				
				System.out.println("[Server]: " + singleLine);
				System.out.println("[Client]: " + in.readLine());

				singleLine = sysin.readLine();
			}
			
			out.close();
			in.close();
			socket.close();
			
			sysin.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
	
}
