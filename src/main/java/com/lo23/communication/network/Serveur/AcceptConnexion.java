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
	
	public AcceptConnexion(ServerSocket ss)
	{
		this.socketserver = ss;
	}
	
	@Override
	public void run(){
	
			try{
				while(true){
					
					socket = socketserver.accept();
					System.out.println("Socket ouverte sur le port: " + socket.getLocalPort());
					System.out.println("Connexion demandée par un client");
					
					ReadMessage read = new ReadMessage(socket);
					read.start();
				}
			}catch(IOException e)
			{
				System.out.println("Erreur serveur");
			}
			
	}
}
