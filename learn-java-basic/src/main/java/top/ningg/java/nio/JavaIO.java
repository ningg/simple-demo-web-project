package top.ningg.java.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class JavaIO {

	public static void main(String[] args) throws IOException {
		String fileName = "D:/test.log";
		InputStream inputStream = new FileInputStream(fileName);
		
		inputStream.read();
		
		Socket socket = new Socket();
		
	}
}
