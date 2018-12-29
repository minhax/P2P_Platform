package com.lo23.communication.Messages.Authentication_Server;

import com.lo23.communication.CommunicationManager.Client.CommunicationManagerClient;
import com.lo23.communication.Messages.Authentication;
import java.io.Serializable;


/**
 * Message de transmission de l'adresse IP ajoutee jusqu'aux clients
 */
public class addAdressIpMsg extends Authentication
{
	/**
	 * serialVersionUID : l'identifiant unique de la classe
	 */
	private static final long serialVersionUID = 87L;
	/**
	 * ip : l'adresse IP de serveur
	 */
	protected String ip;

	/**
	 * Constructeur
	 * @param ipAddress : l'adresse IP
	 */
	public addAdressIpMsg(String ipAddress)
	{
		this.ip = ipAddress;
	}
	
	/**
	 * transmet le message de l'adresse IP ajoutee jusqu'aux clients
	 */
	public void treatment()
	{
		System.out.println("IP" + this.ip);
		/**
		 * Récupération de communication manager coté client
		 */
		CommunicationManagerClient commManagerClient = CommunicationManagerClient.getInstance();
		/**
		 * Changement de l'adresse IP de serveur par ip
		 */
		commManagerClient.setAddressIpServer(this.ip);
	}

	/**
	 * l'accesseur (getter) de l'ip
	 * @return String
	 */
	public String getIp()
	{
		return ip;
	}

	public boolean isToServ()
	{
		return false;
	}
	
}
