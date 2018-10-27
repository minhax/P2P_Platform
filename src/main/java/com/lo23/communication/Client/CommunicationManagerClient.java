package com.lo23.communication.Client;


import com.lo23.common.interfaces.comm.CommToDataClient;
import com.lo23.common.interfaces.data.DataClientToComm;

public class CommunicationManagerClient {

	public DataClientToComm dataInterface;
	public CommToDataClient commInterface;
	
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

}
