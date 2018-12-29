package com.lo23.communication.Messages.Users_Server;

import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.data.DataClientToComm;
import com.lo23.common.user.UserIdentity;
import com.lo23.common.user.UserStats;
import com.lo23.communication.CommunicationManager.Client.CommunicationManagerClient;
import com.lo23.communication.Messages.UserMessage;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

/**
 * Message pour recuperer l'utilisateur connecte
 */
public class connectedUserMsg extends UserMessage
{
	/**
	 * serialVersionUID : l'identifiant de la classe
	 */
	private static final long serialVersionUID = 46L;
	/**
	 * usersInfoAndFiles : une table qui contient la paire utilisateur-vecteur des informations des fichiers
	 */
	private HashMap<UserIdentity, Vector<FileHandlerInfos>>  usersInfoAndFiles;

	/**
	 * Constructeur
	 * @param usersInfoAndFiles : table de tous les utilisateurs avec le vecteur des informations des fichiers
	 */
	public connectedUserMsg(HashMap<UserIdentity,Vector<FileHandlerInfos>> usersInfoAndFiles)
	{
		this.usersInfoAndFiles = usersInfoAndFiles;
	}
	/**
	 * Traitement est applique du cote client
	 * Recupere le communicationManagerClient
	 * Appel la methode addNewConnectedUser pour lui transmettre son objet user Stats
	 * Appel la methode addNewUserFiles pour lui transmettre ses filesInfos
	 */
	/**
	 * cree le message pour recuperer l'utilisateur connecte
	 */
	public void treatment()
	{
		/**
		 * Recuperation de Communication Manager cote client
		 */
		CommunicationManagerClient cmc = CommunicationManagerClient.getInstance();
		/**
		 * Récupération de l'interface de dataClient
		 */
		DataClientToComm dataInterface = cmc.getDataInterface();
		/**
		 * Appel de la methode de data notifyOtherUserConnectedToAll qui permet de notifier l'utilisateur connecté à tous
		 */
		dataInterface.notifyOtherUserConnectedToAll(this.usersInfoAndFiles);
	}

    public boolean isToServ()
	{
		return false;
	}
}
