package com.lo23.communication.Messages.Files_Server;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.data.DataClientToComm;
import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.common.user.User;
import com.lo23.common.user.UserIdentity;
import com.lo23.communication.CommunicationManager.Client.CommunicationManagerClient;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.communication.Messages.FileMessage;
import com.lo23.communication.Messages.Users_Server.updatedAccountMsg;

/**
 * Message pour envoyer les mises a jour d'un fichier
 */
public class sendUpdatedFileMsg extends FileMessage
{
	/**
	 * user : l'utilisateur
	 */
	private User usr;
	/**
	 * serialVersionUID : l'identifiant unique de la classe
	 */
	private static final long serialVersionUID = 51L;

	/**
	 * Constructeur
	 * @param fi : le fichier qui subit les mises a jour
	 * @param user : l'utilisateur qui fait les mises a jour
	 */
	public sendUpdatedFileMsg(FileHandlerInfos fi, User user)
	{
		this.file = fi;
		this.usr = user;
	}

	/**
	 * cree le message d'envoyer les mises a jour d'un fichier
	 */
	public void treatment()
	{
		/**
		 * Recuperation le communication manager cote client
		 */
        CommunicationManagerClient cmc = CommunicationManagerClient.getInstance();
		/**
		 * Recuperation de l'interface de dataClient
		 */
		DataClientToComm dataInterfaceClient = cmc.getDataInterface();
		/**
		 * Appel de la methode de data notifier à tout utilisateur les mises à jour
		 */
        dataInterfaceClient.notifyUpdatedSharedFileToAll(this.file,this.usr);
	}

    public boolean isToServ()
	{
		return false;
	}
}
