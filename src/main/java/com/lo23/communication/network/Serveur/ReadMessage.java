package com.lo23.communication.network.Serveur;

import com.lo23.communication.Messages.Message;

import java.io.*;
import java.net.Socket;

public class ReadMessage extends Thread implements Serializable {
	/**
	 * Socket à utiliser
	 */
	private Socket socket;
	
	/**
	 * Flux D'entrée à lire
	 */
	private ObjectInputStream input;
	
	/**
	 * Recupere l'input stream de la socket s
	 * @param s
	 */
	public ReadMessage(Socket s){
		socket = s;
		try {
			this.input = new ObjectInputStream(socket.getInputStream());
		}catch (IOException e)
		{
			System.out.println("Erreur de recuperation de l'input stream ");
			e.printStackTrace();
		}
	}
	
	/**
	 * Lis dans l'input stream, recupere le message et fais le traitement
	 * Ferme l'input et la socket
	 */
	@Override
	public void run() {
		try {
			Object msg = this.input.readObject();
			Message msgCast = (Message) msg;
			System.out.println("Message treatment begins");
			try {
				msgCast.treatment();
				System.out.println("End of treatment");
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.input.close();
			this.socket.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
