package com.lo23.communication.Server;

import com.lo23.common.interfaces.comm.CommToDataServer;
import com.lo23.common.interfaces.data.DataServerToComm;

public class CommunicationManagerServer {
	
	public DataServerToComm dataInterface;
	public CommToDataServer commInterface;
	
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
}
