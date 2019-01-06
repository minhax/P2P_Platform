package com.lo23.communication.network.Serveur;

import com.lo23.communication.Messages.Message;

import java.io.*;
import java.net.Socket;

/**
 * Classe qui lit le message
 */
public class ReadMessage extends Thread implements Serializable
{
	/**
	 * socket : Socket à utiliser
	 */
	private Socket socket;
	
	/**
	 * input : Flux D'entrée à lire
	 */
	private ObjectInputStream input;
	
	/**
	 * Constructeur
	 * Récupère l'input stream de la socket s
	 * @param s : objet de type socket
	 */
	public ReadMessage(Socket s)
	{
		socket = s;
		try
		{
			this.input = new ObjectInputStream(socket.getInputStream());
		}
		catch (IOException e)
		{
			System.out.println("Erreur de recuperation de l'input stream ");
			e.printStackTrace();
		}
	}
	
	/**
	 * Lit dans l'input stream, récupère le message et fait le traitement
	 * Ferme l'input et la socket
	 */
	@Override
	public void run()
	{
		try
		{
			System.out.println("Lecture du message");
			Object msg = this.input.readObject();
			Message msgCast = (Message) msg;
			System.out.println("Traitement du message");
			try
			{
				msgCast.treatment();
				System.out.println("Fin du traitement");
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			this.input.close();
			this.socket.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
