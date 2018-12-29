package com.lo23.communication.Messages.Files_Server;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.data.DataClientToComm;
import com.lo23.common.user.UserIdentity;
import com.lo23.communication.CommunicationManager.Client.CommunicationManagerClient;
import com.lo23.communication.Messages.FileMessage;

/**
 * Message pour avoir les nouvelles informations de fichier
 */
public class newFileInfoMsg extends FileMessage
{
	/**
	 * user : l'identite d'un utilisateur
	 */
	protected UserIdentity user;
	/**
	 * serialVersionUID : l'identifiant unique de la classe
	 */
	private static final long serialVersionUID = 52L;

	/**
	 * Constructeur
	 * @param fi : le fichier ...
	 * @param u : l'utilisateur ...
	 */
	public newFileInfoMsg(FileHandlerInfos fi, UserIdentity u)
	{
		this.file = fi;
		this.user = u;
	}

	/**
	 * cree le message pour avoir les nouvelles informations de fichier
	 */
	public void treatment()
	{
		/**
		 * Récupération de Communication Manager coté Client
		 */
		CommunicationManagerClient commManagerClient = CommunicationManagerClient.getInstance();
		/**
		 * Récupération de l'interface de dataClient
		 */
		DataClientToComm dataInterface = commManagerClient.getDataInterface();
		/**
		 * Appel de la methode de data notifyNewSharedFileToAll qui permet de notifier le nouveau fichier partagé à tous les utilisateurs
		 */
		dataInterface.notifyNewSharedFileToAll(this.file, this.user);
	}

    public boolean isToServ()
	{
		return false;
	}
}
