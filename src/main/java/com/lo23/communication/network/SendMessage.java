package com.lo23.communication.network;

import com.lo23.communication.Messages.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SendMessage extends Thread {
	
	/**
	 * Socket pour envoyer le message
	 */
	private Socket socket;
	/**
	 * Flux de sortie à envoyer
	 */
	private ObjectOutputStream output;
	/**
	 * Message envoyé sur le réseau à une application distante
	 */
	private Message msg;
	/**
	 * Adresse Ip à laquelle envoyer le message
	 */
	private String address;
	/**
	 * Numéro de port sur lequel envoyer le message
	 */
	private int port;
	
	/**
	 * Port local
	 */
	private int localPort;
	
	/**
	 * booléen
	 */
	private boolean jobDone;
	/**
	 * Constructeur de la classe
	 *
	 * @param address   Socket pour l'envoi du message
	 * @param port      Port à utiliser
	 * @param msg       Message à transmettre
	 */
	public SendMessage(String address, Integer port, Message msg) {
		this.socket = null;
		this.msg = msg;
		this.address = address;
		this.port = port;
		this.output = null;
	}
	
	/**
	 * Ecriture et envoi du message
	 */
	@Override
	public void run() {
		try {
			this.socket = new Socket(this.address, this.port);
			output = new ObjectOutputStream(this.socket.getOutputStream());
			this.msg.setPort(this.localPort);
			output.writeObject(this.msg);
			output.flush(); // check si correct pendat test
			output.close();
			socket.close();
		} catch (IOException exc) {
			exc.printStackTrace();
		}
	}
	public int getLocalPort()
	{
		return this.localPort;
	}
}
