package com.lo23.communication.CommunicationManager.Client;


import com.lo23.common.interfaces.comm.CommToDataClient;
import com.lo23.common.interfaces.data.DataClientToComm;
import com.lo23.communication.Messages.Message;

public class CommunicationManagerClient {

	protected DataClientToComm dataInterface;
	protected CommToDataClient commInterface;
	
	/* Constructeur privé pour implémentation du singleton */
	private CommunicationManagerClient()
	{
		dataInterface = null;
		commInterface = null;
	}
	/* Instance unique initialisée */
	private static CommunicationManagerClient Instance = new CommunicationManagerClient();
	
	/* Point d'accès à l'instance unique */
	public static CommunicationManagerClient getInstance()
	
	{
		return Instance;
	}
	
	public void sendMsgToData( Message M){
		// On applique le traitement au message, on stocke les informations dans un nouveau message
		M.treatment(); // Le probleme actuel c'est qu'il faut que l'on passe l'interface qu'on veut utiliser
	}
}
