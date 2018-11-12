package com.lo23.communication.CommunicationManager.Server;

import com.lo23.common.exceptions.CommException;
import com.lo23.common.interfaces.comm.CommToDataServer;
import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.communication.CommunicationManager.CommunicationManager;
import com.lo23.communication.Messages.Authentication_Server.addAdressIpMsg;
import com.lo23.data.server.DataServerToCommAPI;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedHashMap;

public class CommunicationManagerServer extends CommunicationManager {
	
	private DataServerToCommAPI dataInterface; //incorrect, attendre l'implémentation de l'interface ComClient et ComServeur
	private CommToDataServer commInterface;
	private LinkedHashMap<String,String> clientAndServerIP;
	/* Constructeur privé pour implémentation du singleton */
	private CommunicationManagerServer()
		{
			
			this.dataInterface = null;
			this.commInterface = null;
			
			/** Bloc try pour recuperer l'adresse IP de la machine sur le reseau (fonction a tester) **/
			try {
				ip = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException ex) {
				System.out.print("Erreur dans la recuperation de l'adresse IP");
			}
			/** Instanciation de la linkedHashMap **/
			clientAndServerIP = new LinkedHashMap<>();//met bien string string?
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
	public void setDataInterface(DataServerToCommAPI ds) {
		this.dataInterface = ds;
	}
	public void setCommInterface (CommToDataServer cs)
	{
		this.commInterface = cs;
	}
	public void addEntryInClientAndServerIPArray(String client, String server)
	{
		/** efface la valeur precedemment enregistre pour le client si elle existe**/
		//Penser a mettre une exception
		this.clientAndServerIP.put(client, server);
	}
	public void sendServerIpAdress(String ip)
	{
		addAdressIpMsg msg = new addAdressIpMsg(ip);
		//send it through socket
	}
	
	public void removeUserFromTable(String userIPAddress)
			throws CommException
	{
		/** On vérifie que la valeur de la clé (adresse IP du client ) existe bien dans la table**/
		if (!(this.clientAndServerIP.containsValue(userIPAddress)))
				throw new CommException("L'adresse IP n'est pas presente dans la table", userIPAddress);
			else
			this.clientAndServerIP.remove(userIPAddress);
	}
	public void broadcast()
	{
		// prendre les sockets et creer autant de sockets sends qu'il n'y a d'entree dans la table
	}
}
