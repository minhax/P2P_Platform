package com.lo23.communication.CommunicationManager.Client;


import com.lo23.common.interfaces.data.DataClientToComm;
import com.lo23.communication.APIs.CommToDataClientAPI;
import com.lo23.communication.CommunicationManager.CommunicationManager;

/**
 * CommunicationManagerClient qui herite de CommunicationManager
 */
public class CommunicationManagerClient extends CommunicationManager
{

	/**
	 * dataInterface : une instance de l'interface DataClientToComm
	 */
	protected DataClientToComm dataInterface;
	/**
	 * commToDataClientAPI : une instance de l'API CommToDataClientAPI
	 */
	protected CommToDataClientAPI commInterface;
	/**
	 * addIpServer : l'adresse IP de serveur
	 */
	protected String addressIpServer;
	
	/** Constructeur privé
	 * Récupère interfaces de dataClient et commClient
	 * Récupère l'adresse IP de la machine sur le réseau UTC
	 **/
	private CommunicationManagerClient()
	{
		/**
		 * Initialisation des variables privees du communicationManagerClient
		 */
		this.commInterface = CommToDataClientAPI.getInstance();
		this.addressIpServer = null;
		try
		{
			this.ip = findIPadress();
		}
		catch (Exception ex)
		{
			/**
			 * Affichage d'un message d'erreur
			 */
			System.out.print("Erreur dans la récuperation de l'adresse IP");
		}
	}
	
	/**
	 * Initialisation du singleton
	 */
	private static CommunicationManagerClient Instance = new CommunicationManagerClient();

	/**
	 * Récupère le CommunicationManagerClient
	 * @return objet de type CommunicationManagerClient
	 */
	public static CommunicationManagerClient getInstance()
	{
		return Instance;
	}

	/** Getteur et setteur d'interfaces **/

	/**
	 * l'accesseur (getter) de addressIpServer
	 * @return l'adresse IP du serveur qu'est de type String
	 */
	public String getAddressIpServer()
	{
		return addressIpServer;
	}

	/**
	 * l'accesseur (getter) de dataInterface
	 * @return objet de type DataClientToComm
	 */
	public DataClientToComm getDataInterface()
	{
		return dataInterface;
	}

	/**
	 * l'accesseur (getter) de commInterface
	 * @return objet de type CommToDataClientAPI
	 */
	public CommToDataClientAPI getCommInterface()
	{
		return commInterface;
	}

    /**
     * l'accesseur (setter) de addressIpServer
     * @param s : String
     */
	public void setAddressIpServer(String s)
	{
		this.addressIpServer = s;
	}


    /**
     * l'accesseur (setter) de dataInterface
     * @param di : objet de type CommToDataClient
     */
	public void setDataInterface(DataClientToComm di)
	{
		this.dataInterface = di;
	}


    /**
     * l'accesseur (setter) de commInterface
     * @param ci : objet de type CommToDataClientAPI
     */
	public void setCommInterface (CommToDataClientAPI ci)
	{
		this.commInterface = ci;
	}
}
