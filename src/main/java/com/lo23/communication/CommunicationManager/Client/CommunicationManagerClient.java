package com.lo23.communication.CommunicationManager.Client;


import java.net.InetAddress;
import java.net.UnknownHostException;

import com.lo23.common.interfaces.data.DataClientToComm;
import com.lo23.communication.APIs.CommToDataClientAPI;
import com.lo23.communication.CommunicationManager.CommunicationManager;

public class CommunicationManagerClient extends CommunicationManager{

	protected DataClientToComm dataInterface;
	protected CommToDataClientAPI commInterface;
	protected String addressIpServer;
	
	/** Constructeur privé
	 * Récupère interfaces de dataClient et commClient
	 * Récupère l'adresse IP de la machine sur le réseau UTC
	 * @param
	 **/
	private CommunicationManagerClient()
	{
		/** Initialisation des variables privees du CMC **/
		this.commInterface = CommToDataClientAPI.getInstance();
		this.addressIpServer = null;
		
		try {
			this.ip = findIPadress();
		} catch (Exception ex) {
			System.out.print("Erreur dans la recuperation de l'adresse IP");
		}
	}
	
	/** Singleton **/
	private static CommunicationManagerClient Instance = new CommunicationManagerClient();
	public static CommunicationManagerClient getInstance()
	{
		return Instance;
	}
	/** Getteur et setteur d'interfaces **/
	public String getAddressIpServer()
	{
		return addressIpServer;
	}
	public DataClientToComm getDataInterface()
	{
		return dataInterface;
	}
	public CommToDataClientAPI getCommInterface()
	{
		return commInterface;
	}
	public void setAddressIpServer(String s) {
		this.addressIpServer = s;}
	public void setDataInterface(DataClientToComm di)
	{
		this.dataInterface = di;
	}
	public void setCommInterface (CommToDataClientAPI ci)
	{
		this.commInterface = ci;
	}
}
