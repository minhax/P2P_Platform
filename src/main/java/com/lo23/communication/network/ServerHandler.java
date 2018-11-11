package com.lo23.comm.communication.network;


import java.net.Socket;

public class ServerHandler extends Thread
{
	protected Socket socket;
	
	public ServerHandler(Socket peerSocket)
	{
		this.socket = peerSocket;
	}	
	
}
