package com.lo23.communication.Messages.Files_Client;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.Rating;
import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.common.user.User;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.communication.Messages.FileMessage;
import com.lo23.communication.Messages.Files_Server.sendUpdatedFileMsg;

/**
 * Message de transmission de note ajoutee jusqu'aux clients
 */
public class rateFileMsg extends FileMessage
{
	/**
	 * serialVersionUID : l'identifiant unique de la classe
	 */
	private static final long serialVersionUID = 61L;
	/**
	 * rate : la note q ajouter
	 */
	protected Rating rate;
	/**
	 * user : l'utilisateur qui ajoute la note
	 */
	private User user;

	/**
	 * Constructeur
	 * @param r : la note a ajouter
	 * @param fi : le fichier dans lequel on ajoute la note
	 * @param usr : l'utilisateur qui va ajouter la note
	 */
	public rateFileMsg(Rating r, FileHandlerInfos fi, User usr)
	{
		this.file = fi;
		this.rate = r;
		this.user = usr;
	}

	/**
	 * transmet le message d'une note ajoutee jusqu'aux clients
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
		 * Appel de la methode de data updateFileWithNewRating qui permet de mettre a jour les fichiers avec la nouvelle note
		 */
		dataInterface.updateFileWithNewRating(this.file, this.rate, this.user);

		/**
		 * Création de message d'envoyer les mises a jour de fichier
		 */
		sendUpdatedFileMsg message = new sendUpdatedFileMsg(this.file, this.user);
		message.setPort(this.getPort());
		/**
		 * Faire le broadcast de message  d'ajouter un commentaire aux utilisateurs connectés
		 */
		commManagerServer.broadcast(message);
	}

	public boolean isToServ()
	{
		return true;
	}
}
