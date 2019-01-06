package com.lo23.communication.Messages.Files_Server;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.data.DataClientToComm;
import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.common.user.User;
import com.lo23.communication.CommunicationManager.Client.CommunicationManagerClient;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.communication.Messages.FileMessage;

/**
 * Message pour envoyer la partie dont l'utilisateur q besoin
 */
public class sendFileMsg extends FileMessage
{
	/**
	 * userAsking : l'utilisateur qui veut une partie de fichier à télécharger
	 */
	private User userAsking;
	/**
	 * userSource : l'utilisateur qui possède le fichier voulu
	 */
	private User userSource;
	/**
	 * part : la partie voulue de fichier
	 */
	private long part;
	/**
	 * content : le contenu voulu de fichier
	 */
	private byte[] content;

	/**
	 * envoie la partie dont l'utilisateur a besoin
	 * @param userAsking : l'utilisateur qui veut une partie de fichier à télécharger
	 * @param userSource : l'utilisateur qui possède le fichier voulu
	 * @param file : le fichier à télécharger
	 * @param part : la partie voulue de fichier
	 * @param content : le contenu voulu de fichier
	 */
	public sendFileMsg(User userAsking, User userSource, FileHandlerInfos file, long part, byte[] content)
	{
		this.file = file;
		this.userAsking=userAsking;
		this.userSource=userSource;
		this.content=content;
		this.part=part;
	}

	/**
	 * crée le message pour envoyer la partie dont l'utilisateur a besoin
	 */
	public void treatment()
	{
		/**
		 * Récupération de communication manager coté serveur
		 */
		CommunicationManagerClient commManagerClient = CommunicationManagerClient.getInstance();
		/**
		 * Récupération de l'interface de dataClient
		 */
		DataClientToComm dataInterface = commManagerClient.getDataInterface();
		/**
		 * Appel de la méthode de data receiveFilePart
		 */
		dataInterface.receiveFilePart(this.file, this.part, this.content);
	}

	public boolean isToServ()
	{
		return true;
	}
}
