package com.lo23.communication.CommunicationManager.Server;

import com.lo23.common.exceptions.CommException;
import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.communication.APIs.CommToDataServerAPI;
import com.lo23.communication.CommunicationManager.CommunicationManager;
import com.lo23.communication.Messages.Message;
import com.lo23.communication.network.Client.Client;
import com.lo23.data.Const;

import java.rmi.server.UID;
import java.util.EmptyStackException;
// import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * CommunicationManagerServer qui herite de CommunicationManager
 */
public class CommunicationManagerServer extends CommunicationManager {

	/**
	 * dataInterface : une instance de la classe DataServerToComm
	 */
	private DataServerToComm dataInterface;
	/**
	 * commInterface : une instance de l'API CommToDataServerAPI
	 */
	private CommToDataServerAPI commInterface;
	/**
	 * clientIP_UID: implementation de la table de hashage
	 * hashmap stocke les adresses IP de tous les utilisateurs
	 */
	private HashMap<String, Integer>  clientIptoPort;
	/**
	 * clientIP_UID: implementation de la table de hashage
	 * UUID : objet qui permet d'attribuer id unique a chaque utilisateur
	 */
	private HashMap<UUID, String> clientIP_UID;
	
	/** Constructeur privé
	 * Récupère un objet interface de DataServer et CommServer
	 * Récupère l'adresse IP de la machine sur le réseau UTC
	 * Instancie la LinkedHashMap
	 * ...
	 * @return void
	 **/
	private CommunicationManagerServer()
		{

			/**
			 * Initialisation des variables privees du communicationManagerClient
			 */
			this.commInterface = CommToDataServerAPI.getInstance();
			try
			{
				this.ip = findIPadress();
			}
			catch (Exception ex)
			{
				/**
				 * Affichage d'un message d'erreur
				 */
				System.out.print("Erreur dans la recuperation de l'adresse IP");
			}

			clientIptoPort = new HashMap<>();
			clientIP_UID=new HashMap<>();
		}
	
	
	/** Getteur et Setteur **/
	/**
	 * l'accesseur (getter) de dataInterface
	 * @return objet de type DataServerToComm
	 */
	public DataServerToComm getDataInterface()
	{
		return dataInterface;
	}
	/**
	 * l'accesseur (getter) de commInterface
	 * @return objet de type CommToDataServerAPI
	 */
	public CommToDataServerAPI getCommInterface()
	{
		return commInterface;
	}

	/**
	 * l'accesseur (setter) de dataInterface
	 */
	public void setDataInterface(DataServerToComm ds)
	{
		this.dataInterface = ds;
	}

	/**
	 * l'accesseur (setter) de commInterface
	 */
	public void setCommInterface (CommToDataServerAPI cs)
	{
		this.commInterface = cs;
	}
	/**
	 * l'accesseur (getter) de l'adresse IP
	 * @return String
	 */
	public String getIP()
	{
		return this.ip;
	}

	/**
	 * Initialisation du singleton
	 */
	private static CommunicationManagerServer Instance = new CommunicationManagerServer();
	/**
	 * récupère le CommunicationManagerServer
	 * @return objet de type CommunicationManagerClient
	 */
	public static CommunicationManagerServer getInstance()
	{
		return Instance;
	}
	/*


	 * Ajoute une clé [IP Client et IP Serveur] dans une LinkedHashMap
	 *
	 * @param IP client et IP ServerClass
	 * ...
	 * @return void

	public void addEntryInClientAndServerIPArray(String client, String server)
	{
		//efface la valeur precedemment enregistre pour le client si elle existe
		//Penser a mettre une exception
		this.clientInfoAndServerIP.put(client, server);
	}


	public void removeUserFromTable(String userIPAddress)
			throws CommException
	{
		/// On vérifie que la valeur de la clé (adresse IP du client ) existe bien dans la table
		if (!(this.clientAndServerIP.containsKey(userIPAddress)))
				throw new CommException("L'adresse IP n'est pas presente dans la table", userIPAddress);
			else
			this.clientInfoAndServerIP.remove(client);
	}
	*/

	/**
	 * Ajouter l'adresse de client ainsi que le port au hashMap
	 * @param clientAddr l'adresse de client
	 * @param clientPort le port de client
	 * @return void
	 */
	public void addEntryMap_IPPort(String clientAddr, int clientPort)
    {
    	this.clientIptoPort.put(clientAddr, clientPort);
    }

	/**
	 * Ajouter l'adresse de client ainsi que le UUID de client
	 * @param clientIp l'adresse de client
	 * @param clientUUID le UUID de client
	 * @return void
	 */
	public void addEntryMap_IPUID(String clientIp, UUID clientUUID)
	{
		this.clientIP_UID.put(clientUUID, clientIp);
	}

	/**
	 * Supprime l'utilisateur en fonction de la clé qu'est userIpAddr
	 * @param userIpAddr : la clé
	 * @throws CommException
	 * @return void
	 */
    public void removeUserFromMap_IPPort(String userIpAddr) throws CommException
    {
        if (!(this.clientIptoPort.containsKey(userIpAddr)))
            throw new CommException("L'adresse IP n'est pas presente dans la table", userIpAddr);
        else
            this.clientIptoPort.remove(userIpAddr);
    }

	/**
	 * Supprime l'utilisateur en fonction de la clé qu'est clientIp
	 * @param clientIp : la clé
	 * @throws CommException
	 * @return void
	 */
    public void removeUserFromMap_IPUID(String clientIp) throws CommException
	{
		if (!(this.clientIptoPort.containsKey(clientIp)))
			throw new CommException("L'adresse IP n'est pas presente dans la table", clientIp);
		else
			this.clientIptoPort.remove(clientIp);

	}


	/**
	 * Permet de retrouver l'IP d'un utilisateur à partir de son identifiant
	 * @param user : l'utilisateur
	 * @return l'adresse IP
	 */
	public String findUserIp(UUID user)
	{
		return(this.clientIP_UID.get(user));
	}

	/**
	 * Envoie un message à toutes les machines connectées
	 * @param m : Parse la table et recupere chaque cle (IPUser)
	 * @throws EmptyStackException
	 * @return void
	 */
	public void broadcast(Message m) throws EmptyStackException
	{
		for(Map.Entry<String, Integer> entry : this.clientIptoPort.entrySet())
		{
			String addrClient = entry.getKey();
			int portClient = Const.CLIENT_DEFAULT_PORT;
			try
			{
				Client c = new Client(m, addrClient, portClient);
				c.start();
			}
			catch (Exception e)
			{
				e.printStackTrace();
				System.out.println("Erreur dans la creation du client");
			}
		}
	}
	
}
