package com.lo23.communication.CommunicationManager;

public abstract class CommunicationManager {
	
	//protectedSocketHandlerSend socketHSend;
	//protected SocketHandlerReceive socketHReceive; //Vraiment utile?
	// protected SocketHandler socketH
     protected String ip;
	// envoi du message crée au préalable au socket handler
	public String getIp(){
		return this.ip;
	}
	/*
	public void sendMessageToSocket(Message M){
		// Le message est cree dans les methodes de l'interface puis transmis
		//this.socketHSend.socket.write(msg)
		//il faut retourner immediatement sans attendre le retour de la socket
	*/
	}