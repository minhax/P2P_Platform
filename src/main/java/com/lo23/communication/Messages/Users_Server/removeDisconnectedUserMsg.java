package com.lo23.communication.Messages.Users_Server;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.data.DataClientToComm;
import com.lo23.common.user.User;
import com.lo23.common.user.UserIdentity;
import com.lo23.common.user.UserStats;
import com.lo23.communication.CommunicationManager.Client.CommunicationManagerClient;
import com.lo23.communication.Messages.UserMessage;

import java.util.List;

/**
 * Message pour supprimer l'utilisateur deconnecte
 */
public class removeDisconnectedUserMsg extends UserMessage
{
	/**
	 * serialVersionUID : l'identifiant unique de la classe
	 */
	private static final long serialVersionUID = 44L;
	/**
	 * usr : l'identite de l'utilisateur
	 */
	private UserStats usr;

	/**
	 * Constructeur
	 * @param ui : l'utilisateur deconnecte
	 */
	public removeDisconnectedUserMsg(UserStats ui)
	{
		this.usr = ui;
	}
	/**
	 * Traitement est applique du cote client
	 * Recupere le communicationManagerClient
	 * Appel la methode addNewConnectedUser pour lui transmettre son objet user Stats
	 * Appel la methode addNewUserFiles pour lui transmettre ses filesInfos
	 */
	/**
	 * cree le message pour supprimer l'utilisateur deconnecte
	 */
	public void treatment()
	{
		/**
		 * Recuperation de Communication Manager cote client
		 */
		CommunicationManagerClient cms = CommunicationManagerClient.getInstance();
		/**
		 * Récupération de l'interface de dataClient
		 */
		DataClientToComm dataInterface = cms.getDataInterface();
		/**
		 * Appel de la methode de data notifyOtherUserDisconnectedToAll qui permet de notifier l'utilisateur déconnecté à tous
		 */
		dataInterface.notifyOtherUserDisconnectedToAll(this.usr);
	}

    public boolean isToServ()
	{
		return false;
	}
}
