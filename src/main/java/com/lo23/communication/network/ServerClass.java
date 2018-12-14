package com.lo23.communication.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.lo23.communication.network.ServerHandler;
import java.io.Serializable;

public class ServerClass
{
	private ServerSocket serverSocket = null;
	private Socket socket = null;
	private int peerId = 0;

	@SuppressWarnings("resource")
	public ServerClass() throws NumberFormatException
	{

		try
		{
			serverSocket = new ServerSocket(1026);
			++peerId;
			System.out.println("===============");
			System.out.println("ServerClass central");
			System.out.println("===============\n");
		}

		catch(IOException e)
		{
			e.printStackTrace();
		}

		while(true)
			try
			{
				socket = serverSocket.accept();

				++peerId;
				System.out.println("server established connection with peer " + peerId);

				new ServerHandler(socket, peerId).start(); // creation de nouveau thread pour le server
			}

			catch (IOException e)
			{
				System.out.println("error server");
			}
	}
}