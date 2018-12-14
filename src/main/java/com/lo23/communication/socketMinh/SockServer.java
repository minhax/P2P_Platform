package com.lo23.communication.socketMinh;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SockServer {
	
	public static void main(String[] args) throws Exception
	{
		System.out.println("Server is started");
		ServerSocket ss = new ServerSocket(9999);
		System.out.println("server waiting for client request");
		Socket s = ss.accept();
		
		System.out.println("CLient connected");
		BufferedReader br = new BufferedReader( new InputStreamReader(s.getInputStream()));
		String str = br.readLine();
		
		System.out.println("str = " + str);

	}
}
