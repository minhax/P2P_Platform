package com.lo23.communication.CommunicationManager.Server;

import com.lo23.common.exceptions.CommException;
import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.communication.APIs.CommToDataServerAPI;
import com.lo23.communication.CommunicationManager.CommunicationManager;
import com.lo23.communication.Messages.Message;
import com.lo23.data.server.DataServerToCommAPI;
import com.lo23.communication.network.Client;

import java.util.EmptyStackException;
import java.util.LinkedHashMap;
import java.util.Map;

public class CommunicationManagerServer extends CommunicationManager {
	
	private DataServerToComm dataInterface;
	private CommToDataServerAPI commInterface;
	private LinkedHashMap<String,String> clientAndServerIP;
	
	/** Constructeur privé
	 * Récupère un objet interface de DataServer et CommServer
	 * Récupère l'adresse IP de la machine sur le réseau UTC
	 * Instancie la LinkedHashMap
	 * @param IP client et IP ServerClass
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
			clientAndServerIP = new LinkedHashMap<>();
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
	
	/**
	 * Ajoute une clé [IP Client et IP Serveur] dans une LinkedHashMap
	 *
	 * @param IP client et IP ServerClass
	 * ...
	 * @return void
	 **/
	public void addEntryInClientAndServerIPArray(String client, String server)
	{
		/** efface la valeur precedemment enregistre pour le client si elle existe**/
		//Penser a mettre une exception
		this.clientAndServerIP.put(client, server);
	}
	public void removeUserFromTable(String userIPAddress)
			throws CommException
	{
		/** On vérifie que la valeur de la clé (adresse IP du client ) existe bien dans la table**/
		if (!(this.clientAndServerIP.containsKey(userIPAddress)))
				throw new CommException("L'adresse IP n'est pas presente dans la table", userIPAddress);
			else
			this.clientAndServerIP.remove(userIPAddress);
	}
	
	/**
	 * Envoie un message à toutes les machines connectées
	 *
	 * @param Message m
	 * Parse la table et recupere chaque cle (IPUser)
	 * @return void
	 **/
	public void broadcast(Message m)
	throws EmptyStackException
	{
		for(Map.Entry<String,String> entry : this.clientAndServerIP.entrySet())
		{
			String IpAdress = entry.getKey();
			Client c = new Client(m, 1026, IpAdress); /*TODO FIX hardcode por */
		}
		//Exception a rajouter?
	}
	
}
