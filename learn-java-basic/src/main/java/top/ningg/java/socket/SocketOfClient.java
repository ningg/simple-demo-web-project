package top.ningg.java.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketOfClient {

	public SocketOfClient() {
		try {
			Socket socket = new Socket("localhost", 7777);

			System.out.println("Established a connection...");

			BufferedReader sysin = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			String singleLine = null;
			String singleLineFromServer = null;
			singleLine = sysin.readLine();
			System.out.println("[Client]: " + singleLine);
			
			while(!"bye".equals(singleLineFromServer)){
				out.println(singleLine);
				out.flush();

				singleLineFromServer = in.readLine();
				System.out.println("[Server]: " + singleLineFromServer);

				singleLine = sysin.readLine();
				System.out.println("[Client]: " + singleLine);
			}
			
			out.close();
			in.close();
			socket.close();
			
			sysin.close();
			

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new SocketOfClient();
	}
}
