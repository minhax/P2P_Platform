package com.lo23.communication;

import com.lo23.common.interfaces.data.DataClientToComm;

public abstract class CommunicationManager {
	
	protected DataClientToComm dataInterface;
	
	// envoi du message crée au préalable au socket handler
	public void sendMessageToSocket()
	{
	
	
	}
	// envoi du message récupéré dans la socket à l'interface CommToDataClient
	public void sendMessageToData()
	{
	
	}
	
}
