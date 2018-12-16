package com.lo23.communication.network.Serveur;

import com.lo23.communication.Messages.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
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
	
	public AcceptConnexion(ServerSock server, ServerSocket ss) {
		this.socketserver = ss;
		this.server = server;
	}
	
	@Override
	public void run() {
		
		try {
			while (true) {
				System.out.println("En attente de connexion d'un client");
				socket = socketserver.accept();
				System.out.println("Socket ouverte sur le port: " + socket.getLocalPort());
				//ReadMessage read = new ReadMessage(socket);
				//read.start();
				
				ObjectInputStream os = new ObjectInputStream(socket.getInputStream());
				try {
					System.out.println("Lecture du message");
					Object msg = os.readObject();
					Message msgCast = (Message) msg;
					System.out.println("Traitement du message");
					try {
						msgCast.treatment();
						System.out.println("Fin du traitement");
					} catch (Exception e) {
						e.printStackTrace();
					}
					os.close();
					this.socket.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
