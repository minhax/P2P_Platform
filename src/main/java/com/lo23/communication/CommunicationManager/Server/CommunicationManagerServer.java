package com.lo23.communication.CommunicationManager.Server;

import com.lo23.common.interfaces.comm.CommToDataServer;
import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.communication.CommunicationManager.CommunicationManager;
import com.lo23.communication.Messages.Message;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class CommunicationManagerServer extends CommunicationManager {
	
	private DataServerToComm dataInterface; //incorrect, attendre l'implémentation de l'interface ComClient et ComServeur
	private CommToDataServer commInterface;
	
	/* Constructeur privé pour implémentation du singleton */
	private CommunicationManagerServer(){
		this.dataInterface = null;
		this.commInterface = null;
		/** Bloc try pour recuperer l'adresse IP de la machine sur le reseau (fonction a tester) **/
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex)
		{
			System.out.print("Error in getting IP Adress");
		}
	}
	/** Instance unique initialisée **/
	private static CommunicationManagerServer Instance = new CommunicationManagerServer();
	
	/** Point d'accès à l'instance unique **/
	public static CommunicationManagerServer getInstance()
	{
		return Instance;
	}
	/** Getteur et Setteur **/
	public DataServerToComm getDataInterface()
	{
		return dataInterface;
	}
	public CommToDataServer getCommInterface()
	{
		return commInterface;
	}
	public void setDataInterface(DataServerToComm ds)
	{
		this.dataInterface = ds;
	}
	public void sendServerIpAdress()
	{
	
	}
	public void setCommInterface (CommToDataServer cs)
	{
		this.commInterface = cs;
	}
}
