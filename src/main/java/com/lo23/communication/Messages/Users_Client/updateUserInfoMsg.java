package com.lo23.communication.Messages.Users_Client;

import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.common.user.UserIdentity;
import com.lo23.common.user.UserStats;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.communication.Messages.UserMessage;
import com.lo23.communication.Messages.Users_Server.updatedAccountMsg;

/**
 * Message pour mettre a jour les informations d'un utilisateur
 */
public class updateUserInfoMsg extends UserMessage
{
	/**
	 * serialVersionUID : l'identifiant unique de la classe
	 */
	private static final long serialVersionUID = 50L;

	/**
	 * Constructeur
	 * @param ui : l'utilisateur qui subit les mises a jour de ses informations
	 */
	public updateUserInfoMsg(UserIdentity ui)
    {
		this.user = ui;
	}

	/**
	 * cree le message pour mettre a jour les informations d'un utilisateur
	 */
	public void treatment()
    {
        /**
         * Recupere le communication manager cote serveur
         * Recupere son interface de dataServer
         * Appel la methode updateUserChanges
         */
		/**
		 * Récuperation de communication manager coté serveur
		 */
		CommunicationManagerServer commManagerServer = CommunicationManagerServer.getInstance();
		/**
		 * Récupération de l'interface de data
		 */
		DataServerToComm dataInterface = commManagerServer.getDataInterface();
		/** On récupère et stocke l'adresse IP du serveur
		 */
		/**
		 * Appel de la methode de data updateUserChanges qui permet de mettre à jour les modifications de l'utilisateur
		 */
		dataInterface.updateUserChanges(user);
	    /** Création du message pour le broadcast des informations**/
		/**
		 * Création de message de mettre a jour le compte de l'utilisateur
		 */
		updatedAccountMsg message = new updatedAccountMsg(this.user);
		message.setPort(this.getPort());
		/**
		 * Faire le broadcast du message de connexion vers tous les utilisateurs
		 */
	    commManagerServer.broadcast(message);
	}

    public boolean isToServ()
	{
		return true;
	}
}
