package com.lo23.communication.Messages.Files_Client;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.common.user.UserIdentity;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.communication.Messages.FileMessage;

/**
 * Message pour demander l'emplacement du fichier
 */
public class requestFileLocationMsg extends FileMessage
{
	/**
	 * serialVersionUID : l'identifiant unique de la classe
	 */
	private static final long serialVersionUID = 60L;
	/**
	 * user : l'identity de l'utilisateur
	 */
	protected UserIdentity user;

	/**
	 * Constructeur
	 * @param fi : le fichier qu'on demande son emplacement
	 * @param u : l'urtisateur qui cherche l'emplacement de fichier
	 */
	public requestFileLocationMsg(FileHandlerInfos fi, UserIdentity u)
	{
		this.file = fi;
		this.user = u;
	}

	/**
	 * cree le message de demander l'emplacement de fichier
	 */
	public void treatment()
	{
		/**
		 * Récupération de communication manager coté Serveur
		 */
		CommunicationManagerServer commManagerServer = CommunicationManagerServer.getInstance();
		/**
		 * Récupération de l'interface de data
		 */
		DataServerToComm dataInterface = commManagerServer.getDataInterface();
		/**
		 * Appel de la methode de data requestFileLocationServer qui permet de demander au serveur l'emplacement de fichier
		 */
		dataInterface.requestFileLocationServer(this.file);
	}

	public boolean isToServ()
	{
		return true;
	}
}
