package com.lo23.communication.Client;


import com.lo23.common.interfaces.data.DataClientToComm;

public class CommunicationManager {

	public  DataClientToComm dataInterface;
	
	/* Constructeur privé pour implémentation du singleton */
	private CommunicationManager(){
	dataInterface = null;
	}
	/* Instance unique initialisée */
	private static CommunicationManager Instance = new CommunicationManager();
	
	/* Point d'accès à l'instance unique */
	public static CommunicationManager getInstance()
	{
		return Instance;
	}

}
