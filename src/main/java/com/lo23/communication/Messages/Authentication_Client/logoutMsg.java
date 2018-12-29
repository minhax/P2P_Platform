package com.lo23.communication.Messages.Authentication_Client;

import com.lo23.common.exceptions.CommException;
import com.lo23.common.user.UserStats;
import com.lo23.communication.Messages.Authentication;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.communication.Messages.Users_Server.removeDisconnectedUserMsg;

/**
 * Message de déconnexion
 */
public class logoutMsg extends Authentication
{
	/**
	 * l'identifiant unique de la classe
	 */
	private static final long serialVersionUID = 10002L;
	/**
	 * UserIPAdress : l'adresse IP de l'utilisateur
	 */
	protected String UserIPAdress;

	/**
	 * Constructeur
	 * @param userStats : l'utilisateur
	 * @param ipAdress : l'adresse IP
	 */
	public logoutMsg(UserStats userStats, String ipAdress)
	{
		this.userStats= userStats;
		this.UserIPAdress =ipAdress;
	}

	/**
	 * crée le message de déconnexion
	 */
	public void treatment()
	{
		/**
		 * Récupération de communication manager coté serveur
		 */
		CommunicationManagerServer commManagerServer = CommunicationManagerServer.getInstance();
		/**
		 * Récupération de l'interface de dataServer
		 */
		DataServerToComm dataInterface = commManagerServer.getDataInterface();
		/**
		 * Récupèration et stockage de  l'adresse IP du serveur
		 */
		String ServerIpAdress = commManagerServer.getIP();

		try
		{
			/**
			 * Suppression de la paire IPPort-IPAdress
			 */
			commManagerServer.removeUserFromMap_IPPort(this.UserIPAdress);
			/**
			 * Suppression de la paire IPUID-IPAdress
			 */
			commManagerServer.removeUserFromMap_IPUID(this.UserIPAdress);
		}
		catch(CommException e)
		{
			System.out.println("Message: \t");
			System.out.println(e.getMessage());
			System.out.println("\t printStackTrace: \t");
			e.printStackTrace();
		}
		/**
		 * Transmettre la demande de déconnexion à Data
		 */
		dataInterface.removeDisconnectedUser(this.userStats);

		/**Faire le broadcast du message de connection vers tout les utilisateurs connectés**/

		/**
		 * Création du message de suppression d'un utilisateur déconnecté
		 */
		removeDisconnectedUserMsg message = new removeDisconnectedUserMsg(this.userStats);
		message.setPort(this.getPort());
		/**
		 * Faire le broadcast du message de déconnexion vers tous les utilisateurs
		 */
		commManagerServer.broadcast(message);
	}

	public boolean isToServ()
	{
		return true;
	}
}
