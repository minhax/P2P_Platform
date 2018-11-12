package com.lo23.communication.CommunicationManager.Client;


import com.lo23.common.interfaces.comm.CommToDataClient;
import com.lo23.common.interfaces.data.DataClientToComm;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import com.lo23.data.client.DataClientToCommApi;
import com.lo23.communication.CommunicationManager.CommunicationManager;

public class CommunicationManagerClient extends CommunicationManager{

	protected DataClientToCommApi dataInterface;
	protected CommToDataClient commInterface; // Changer avec API
	protected ArrayList<String> addressIpServer;
	
	/* Constructeur privé pour implémentation du singleton */
	private CommunicationManagerClient()
	{
		/** Initialisation des variables privees du CMC **/
		this.dataInterface = new DataClientToCommApi();
		this.commInterface = null;
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
	public DataClientToComm getDataInterface()
	{
		return dataInterface;
	}
	public CommToDataClient getCommInterface()
	{
		return commInterface;
	}
	public void setAddressIpServer(String s) {this.addressIpServer = s;}
	public void setDataInterface(DataClientToComm di)
	{
		this.dataInterface = di;
	}
	public void setCommInterface (CommToDataClient ci)
	{
		this.commInterface = ci;
	}
}
