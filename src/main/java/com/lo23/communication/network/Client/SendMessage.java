package com.lo23.communication.network.Client;

import com.lo23.communication.Messages.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SendMessage extends Thread
{
	
	/**
	 * socket : Socket pour envoyer le message
	 */
	private Socket socket;
	/**
	 * output : Flux de sortie à envoyer
	 */
	private ObjectOutputStream output;
	/**
	 * msg : Message envoyé sur le réseau à une application distante
	 */
	private Message msg;
	/**
	 * address : Adresse Ip à laquelle envoyer le message
	 */
	private String address;
	/**
	 * destinationPort : Numéro de port sur lequel envoyer le message
	 */
	private int destinationPort;
	
	/**
	 * localPort : Port local
	 */
	private int localPort;
	
	/**
	 * Constructeur de la classe
	 *
	 * @param address   Socket pour l'envoi du message
	 * @param port      Port à utiliser
	 * @param msg       Message à transmettre
	 */
	public SendMessage(Socket s, String address, Integer port, Message msg)
	{
		this.socket = s;
		this.msg = msg;
		this.address = address;
		this.destinationPort = port;
	}
	
	/**
	 * Ecriture et envoi du message
	 */
	@Override
	public void run()
	{
		try
		{
			/**
			 * Recuperation de l'output stream
			 * Envoi du message
			 */
			try
			{
				this.output = new ObjectOutputStream(this.socket.getOutputStream());
			}
			catch(IOException e)
			{
				e.printStackTrace();
				System.out.println(" Impossible de recuperer l'output stream");
			}
			this.msg.setPort(this.socket.getLocalPort());
			output.writeObject(this.msg);
			output.flush(); // check si correct pendat test
			System.out.println("Message envoyé");
			output.close();
			System.out.println("Fermeture de l'output stream");
			socket.close();
			System.out.println("Fermeture de la socket");
		}
		catch (IOException exc)
		{
			exc.printStackTrace();
			System.out.println("Erreur lors de l'envoi du msg = " + msg + " a destination de =" + address + " sur le port = " + this.localPort);
		}
	}
}
