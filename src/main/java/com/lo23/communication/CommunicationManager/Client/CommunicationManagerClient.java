package com.lo23.communication.CommunicationManager.Client;


import java.net.InetAddress;
import java.net.UnknownHostException;

import com.lo23.communication.APIs.CommToDataClientAPI;
import com.lo23.data.client.DataClientToCommApi;
import com.lo23.communication.CommunicationManager.CommunicationManager;

public class CommunicationManagerClient extends CommunicationManager{

	protected DataClientToCommApi dataInterface;
	protected CommToDataClientAPI commInterface;
	protected String addressIpServer;
	
	/* Constructeur privé pour implémentation du singleton */
	private CommunicationManagerClient()
	{
		/** Initialisation des variables privees du CMC **/
		//this.dataInterface = new DataClientToCommApi();
		this.commInterface = CommToDataClientAPI.getInstance();
		/** Initialisation de la List
		 *
		 */
		this.addressIpServer = null;
		/** Bloc try pour recuperer l'adresse IP de la machine sur le reseau (fonction a tester) **/
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex)
		{
			System.out.print("Error in getting IP Adress");
		}
	}
	/** Implementation du singleton **/
	private static CommunicationManagerClient Instance = new CommunicationManagerClient();
	
	/** Point d'accès à l'instance unique **/
	public static CommunicationManagerClient getInstance()
	{
		return Instance;
	}
	/** Getteur et setteur d'interfaces **/
	public String getAddressIpServer()
	{
		return addressIpServer;
	}
	public DataClientToCommApi getDataInterface()
	{
		return dataInterface;
	}
	public CommToDataClientAPI getCommInterface()
	{
		return commInterface;
	}
	public void setAddressIpServer(String s) {this.addressIpServer = s;}
	public void setDataInterface(DataClientToCommApi di)
	{
		this.dataInterface = di;
	}
	public void setCommInterface (CommToDataClientAPI ci)
	{
		this.commInterface = ci;
	}
}
