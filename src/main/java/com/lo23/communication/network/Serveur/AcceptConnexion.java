package com.lo23.communication.network.Serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class AcceptConnexion extends Thread {
	
	/**
	 * Socket serveur, attends les requetes dans le reseau
	 */
	private ServerSocket socketserver = null;
	/**
	 * Socket de communication
	 */
	private Socket socket = null;
	
	private ServerSock server = null;
	
	public AcceptConnexion(ServerSock server, ServerSocket ss)
	{
		this.socketserver = ss;
		this.server = server;
	}
	
	@Override
	public void run(){
	
			try{
				while(true){
					System.out.println("En attente de connexion d'un client");
					socket = socketserver.accept();
					System.out.println("Socket ouverte sur le port: " + socket.getLocalPort());
					this.server.setCreateNewServer(true);
					ServerSock.usersConnected++;
					ReadMessage read = new ReadMessage(socket);
					read.start();
				}
			}catch(IOException e)
			{
				System.out.println("Erreur serveur");
			}
			
	}
}
