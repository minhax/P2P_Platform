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
 * Message pour récupérer l'utilisateur connecte
 */
public class connectedUserMsg extends UserMessage
{
	/**
	 * serialVersionUID : l'identifiant unique de la classe
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
	 * Traitement est applique du coté client
	 * Récupère le communicationManagerClient
	 * Appel la méthode addNewConnectedUser pour lui transmettre son objet user Stats
	 * Appel la méthode addNewUserFiles pour lui transmettre ses filesInfos
	 */
	/**
	 * crée le message pour récupérer l'utilisateur connecté
	 */
	public void treatment()
	{
		/**
		 * Récupération de Communication Manager coté client
		 */
		CommunicationManagerClient cmc = CommunicationManagerClient.getInstance();
		/**
		 * Récupération de l'interface de dataClient
		 */
		DataClientToComm dataInterface = cmc.getDataInterface();
		/**
		 * Appel de la méthode de data notifyOtherUserConnectedToAll qui permet de notifier l'utilisateur connecté à tous
		 */
		dataInterface.notifyOtherUserConnectedToAll(this.usersInfoAndFiles);
	}

    public boolean isToServ()
	{
		return false;
	}
}
