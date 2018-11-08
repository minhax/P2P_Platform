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
	public DataServerToComm getDataInterface()
	{
		return dataInterface;
	}
	public CommToDataServer getCommInterface()
	{
		return commInterface;
	}
}
