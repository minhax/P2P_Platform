package com.lo23.communication.Messages.Files_Server;


import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.data.DataClientToComm;
import com.lo23.common.user.User;
import com.lo23.common.user.UserIdentity;
import com.lo23.communication.CommunicationManager.Client.CommunicationManagerClient;
import com.lo23.communication.Messages.FileMessage;

/**
 * Message pour recuperer la source de fichier
 */
public class fileSourceMsg extends FileMessage
{
	/**
	 * serialVersionUID : l'identifiant de la classe
	 */
	private static final long serialVersionUID = 53L;
	/**
	 * user : l'identite de l'utilisateur
	 */
	protected UserIdentity user;
	//TODO: est-ce qu'un user ne suffirait pas ? -> dans ce cas, il faut aussi modifier la méthode dans l'API de data et dans CommToDataServer

	/**
	 * Constructeur
	 */
	public fileSourceMsg(FileHandlerInfos fi, UserIdentity u)
	{
		this.file = fi;
		this.user = u;
	}

	/**
	 * crée le message de recuperer la source de fichier
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
		 * Instanciation d'un fichier de type FileHandler
		 */
		FileHandler fi=(FileHandler) file;
		/**
		 * Appel de la methode de data notifyNewSourceToAll qui permet de notifier la nouvelle source à tous les utilisateurs
		 */
		dataInterface.notifyNewSourceToAll(fi, user);
		//dataInterface.notifyNewSharedFileToAll();
	}

    public boolean isToServ()
	{
		return false;
	}
}
