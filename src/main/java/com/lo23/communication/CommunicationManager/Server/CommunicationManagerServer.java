package com.lo23.communication.CommunicationManager.Server;

import com.lo23.common.interfaces.comm.CommToDataServer;
import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.communication.CommunicationManager.CommunicationManager;
import com.lo23.communication.Messages.Message;

public class CommunicationManagerServer extends CommunicationManager {
	
	private DataServerToComm dataInterface; //incorrect, attendre l'implémentation de l'interface ComClient et ComServeur
	private CommToDataServer commInterface;
	
	/* Constructeur privé pour implémentation du singleton */
	private CommunicationManagerServer(){
		dataInterface = null;
		commInterface = null;
	}
	/* Instance unique initialisée */
	private static CommunicationManagerServer Instance = new CommunicationManagerServer();
	
	/* Point d'accès à l'instance unique */
	public static CommunicationManagerServer getInstance()
	{
		return Instance;
	}
	public void sendMessageToData( Message M){
		// On applique le traitement au message, on stocke les informations dans un nouveau message
		M.treatment(); // Le probleme actuel c'est qu'il faut que l'on passe l'interface qu'on veut utiliser
	}
	public DataServerToComm getDataInterface()
	{
		return dataInterface;
	}
}
