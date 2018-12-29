package com.lo23.communication.Messages.Files_Client;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.common.user.User;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.communication.Messages.FileMessage;
import com.lo23.communication.Messages.Files_Server.sendUpdatedFileMsg;
import com.lo23.communication.Messages.Users_Server.connectedUserMsg;

/**
 * Message pour rendre le fichier indisponible
 */
public class makeFileUnavailableMsg extends FileMessage
{
	/**
	 * serialVersionUID : l'identifiant unique de la classe
	 */
	private static final long serialVersionUID = 78L;
	/**
	 * user : l'identite de l'utilisateur
	 */
	User user;

	/**
	 * Constructeur
	 * @param fi : le fichier a rendre indisponible
	 * @param us : l'utilisateur
	 */
	public makeFileUnavailableMsg(FileHandlerInfos fi, User us)
	{
		this.file = fi;
		this.user=us;
	}

	/**
	 * cree le message pour rendre le fichier indisponible
	 */
	public void treatment()
	{
		/**
		 * Récuperation de communication manager coté serveur
		 */
		CommunicationManagerServer commManagerServer = CommunicationManagerServer.getInstance();
		/**
		 * Récupération de l'interface de dataServer
		 */
		DataServerToComm dataInterface = commManagerServer.getDataInterface();
		/**
		 * Appel de la methode de data removeFileSource qui permet de supprimer la source de fichier
		 */
		dataInterface.removeFileSource(this.file, this.user);

		/**
		 * Création de message d'envoyer les mises a jour de fichier
		 */
		sendUpdatedFileMsg message= new sendUpdatedFileMsg(this.file, this.user);
		message.setPort(this.getPort());
		/**
		 * Faire le broadcast du message d'insponibilite d'un fichier vers tous les utilisateurs
		 */
		commManagerServer.broadcast(message);
	}

	public boolean isToServ()
	{
		return true;
	}
}
