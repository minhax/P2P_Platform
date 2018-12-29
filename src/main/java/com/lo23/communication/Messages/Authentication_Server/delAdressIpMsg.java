package com.lo23.communication.Messages.Authentication_Server;

import com.lo23.communication.CommunicationManager.Client.CommunicationManagerClient;
import com.lo23.communication.Messages.Authentication;

/**
 * Message de transmission de l'adresse IP supprimee jusqu'aux clients
 */
public class delAdressIpMsg extends Authentication
{
	/**
	 * serialVersionUID : l'identifiant de la classe
	 */
	private static final long serialVersionUID = 88L;

	/**
	 * Constructeur
	 */
	public delAdressIpMsg()
	{

	}

	/**
	 * transmet le message de l'adresse IP ajoutee jusqu'aux clients
	 */
	public void treatment()
	{
        /**
         * Récupération de communication manager coté client
         */
		CommunicationManagerClient commManagerClient = CommunicationManagerClient.getInstance();
        /**
         * supprimer l'adresse IP de serveur = setIP(null)
         */
		commManagerClient.setAddressIpServer(null);
	}
	public boolean isToServ()
	{
		return false;
	}
}
