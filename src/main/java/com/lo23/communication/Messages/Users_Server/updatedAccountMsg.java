package com.lo23.communication.Messages.Users_Server;

import com.lo23.common.interfaces.data.DataClientToComm;
import com.lo23.common.user.UserIdentity;
import com.lo23.common.user.UserStats;
import com.lo23.communication.CommunicationManager.Client.CommunicationManagerClient;
import com.lo23.communication.Messages.UserMessage;

/**
 * Message pour mettre a jour le compte d'un utilisateur
 */
public class updatedAccountMsg extends UserMessage
{
	/**
	 * serialVersionUID : l'identifiant unique de la classe
	 */
	private static final long serialVersionUID = 43L;

	/**
	 * Constructeur
	 * @param ui : l'utilisateur qui va mettre a jour son compte
	 */
	public updatedAccountMsg(UserIdentity ui)
	{
		this.user = ui;
	}

	/**
	 * cree le message pour mettre a jour le compte d'un utilisateur
	 */
	public void treatment()
	{

		/**
		 * Recuperation de Communication Manager cote client
		 */
		CommunicationManagerClient commManagerClient = CommunicationManagerClient.getInstance();
		/**
		 * Récupération de l'interface de dataClient
		 */
		DataClientToComm dataInterfaceClient = commManagerClient.getDataInterface();
		/**
		 * Appel de la methode de data notifyOtherUserUpdatedAccountToAll qui permet de notifier les mises à jour de compte à tous
		 */
		dataInterfaceClient.notifyOtherUserUpdatedAccountToAll(user);
	}

    public boolean isToServ()
	{
		return false;
	}
}
