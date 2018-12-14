package com.lo23.communication.CommunicationManager.Server;

import com.lo23.common.exceptions.CommException;
import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.communication.APIs.CommToDataServerAPI;
import com.lo23.communication.CommunicationManager.CommunicationManager;
import com.lo23.communication.Messages.Message;
import com.lo23.data.server.DataServerToCommAPI;
import com.lo23.communication.network.Client;

import java.util.EmptyStackException;
// import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.Map;

public class CommunicationManagerServer extends CommunicationManager {
	
	private DataServerToComm dataInterface;
	private CommToDataServerAPI commInterface;
	private HashMap<String, Integer>  clientIptoPort;
	
	/** Constructeur privé
	 * Récupère un objet interface de DataServer et CommServer
	 * Récupère l'adresse IP de la machine sur le réseau UTC
	 * Instancie la LinkedHashMap
	 * ...
	 * @return void
	 **/
	private CommunicationManagerServer()
		{
			this.commInterface = CommToDataServerAPI.getInstance();
			
			try {
				this.ip = findIPadress();
			} catch (Exception ex) {
				System.out.print("Erreur dans la recuperation de l'adresse IP");
			}
			clientIptoPort = new HashMap<>();
		}
	
	
	/** Getteur et Setteur **/
	public DataServerToComm getDataInterface()
	{
		return dataInterface;
	}
	public CommToDataServerAPI getCommInterface()
	{
		return commInterface;
	}
	public void setDataInterface(DataServerToComm ds) {
		this.dataInterface = ds;
	}
	public void setCommInterface (CommToDataServerAPI cs)
	{
		this.commInterface = cs;
	}
	public String getIP()
	{
		return this.ip;
	}
	/** Singleton **/

	private static CommunicationManagerServer Instance = new CommunicationManagerServer();
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

	public void addEntryMap(String clientAddr, int clientPort)
    {
        this.clientIptoPort.put(clientAddr, clientPort);
    }

    public void removeUserFromMap(String userIpAddr) throws CommException
    {
        if (!(this.clientIptoPort.containsKey(userIpAddr)))
            throw new CommException("L'adresse IP n'est pas presente dans la table", userIpAddr);
        else
            this.clientIptoPort.remove(userIpAddr);
    }

	/**
	 * Envoie un message à toutes les machines connectées
	 *
	 * @param Message m
	 * Parse la table et recupere chaque cle (IPUser)
	 * @return void
	 **/
	public void broadcast(Message m) throws EmptyStackException
	{
		for(Map.Entry<String, Integer> entry : this.clientIptoPort.entrySet())
		{
			String addrClient = entry.getKey();
			int portClient = this.clientIptoPort.get(addrClient);
			Client c = new Client(m, addrClient);/*TODO FIX hardcode por */
		}
		//Exception a rajouter?
	}
	
}
