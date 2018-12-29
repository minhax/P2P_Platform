package com.lo23.communication.Messages.Files_Client;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.common.user.User;
import com.lo23.common.user.UserIdentity;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.communication.Messages.FileMessage;
import com.lo23.communication.Messages.Files_Server.newFileInfoMsg;

/**
 * Message pour telecharger un fichier
 */
public class uploadFileMsg extends FileMessage
{
	/**
	 * serialVersionUID : l'identifiant unique de la classe
	 */
	private static final long serialVersionUID = 55L;
	/**
	 * user : l'identite de l'utilisateur
	 */
	protected UserIdentity user;

	/**
	 * Constructeur
	 * @param fi : le fichier a telecharger
	 * @param u : l'utilisateur qui veut telecharger le fichier
	 */
	public uploadFileMsg(FileHandlerInfos fi, UserIdentity u)
	{
		this.file = fi;
		this.user = u;
	}

	/**
	 * cree le message pour telecharger un fichier
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
		 * Récupèra et stocke l'adresse IP du serveur
		 */
		System.out.println("[COM]Stockage du fichier" + this.file.getHash());
		/** Envoi des données à data **/
		/**
		 * Appel de la methode de data addNewFileToServer qui permet d'ajouter les donnees au serveur
		 */
		dataInterface.addNewFileToServer(this.file, this.user);
		/** Création du message pour le broadcast des informations**/
		/**
		 * Création de message d'avoir les nouvelles informations de fichier
		 */
		newFileInfoMsg message = new newFileInfoMsg(this.file,this.user);
		/**
		 * Faire le broadcast de message
		 */
		commManagerServer.broadcast(message);
	}

	public boolean isToServ()
	{
		return true;
	}
}
