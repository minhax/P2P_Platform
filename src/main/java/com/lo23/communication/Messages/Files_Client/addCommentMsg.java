package com.lo23.communication.Messages.Files_Client;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.Comment;
import com.lo23.common.interfaces.comm.CommToDataServer;
import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.common.user.User;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.communication.Messages.FileMessage;
import com.lo23.communication.Messages.Files_Server.sendUpdatedFileMsg;
import com.lo23.communication.Messages.Message;

/**
 * Message de transmission de commentaire ajoute jusqu'aux clients
 */
public class addCommentMsg extends FileMessage
{
	/**
	 * serialVersionUID : l'identifiant de la classe
	 */
	private static final long serialVersionUID = 85L;
	/**
	 * comment : commentaire
	 */
	private Comment comment;
	/**
	 * user : l'utilisateur
	 */
	private User user;

	/**
	 * Constructeur
	 * @param fi : le fichier dans lequel on veut ajouter le commentaire
	 * @param c : le commentaire a ajouter
	 * @param usr : l'utilisateur qui ajoute le commentaire
	 */
	public addCommentMsg(FileHandlerInfos fi, Comment c, User usr)
	{
		this.file = fi;
		this.comment = c;
		this.user = usr;
	}

	/**
	 * l'accesseur (getter) de comment
	 * @return un objet de tyoe Comment
	 */
	public Comment getComment()
	{
		return comment;
	}

	/**
	 * transmet le message d'un commentaire ajoute jusqu'aux clients
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
		 * Appel de la methode de data updateFileWithNewComment qui permet de mettre a jour les fichiers avec le nouveau commentaire
		 */
		dataInterface.updateFileWithNewComment(this.file, this.comment, this.user);

		/**
		 * Création de message d'envoyer les mises a jour de fichier
		 */
		sendUpdatedFileMsg message = new sendUpdatedFileMsg(this.file, this.user);
		message.setPort(this.getPort());
		/**
		 * Faire le broadcast de message  d'ajouter un commentaire aux aux utilisateurs connectés
		 */
		commManagerServer.broadcast(message);
	}

    public boolean isToServ()
	{
		return true;
	}
}
