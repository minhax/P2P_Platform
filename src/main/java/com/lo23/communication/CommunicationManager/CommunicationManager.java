package com.lo23.communication.CommunicationManager;

import com.lo23.common.interfaces.data.DataClientToComm;
import com.lo23.communication.Messages.Message;
public abstract class CommunicationManager {
	
	//protectedSocketHandlerSend socketHSend;
	//protected SocketHandlerReceive socketHReceive; //Vraiment utile?
	// protected SocketHandler socketH
	
	// envoi du message crée au préalable au socket handler
	public void sendMessageToSocket(Message M){
		// Le message est cree dans les methodes de l'interface puis transmis
		//this.socketHSend.socket.write(msg)
		//il faut retourner immediatement sans attendre le retour de la socket
	}
}