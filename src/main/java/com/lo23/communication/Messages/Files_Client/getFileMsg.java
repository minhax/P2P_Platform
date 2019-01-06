package com.lo23.communication.Messages.Files_Client;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.data.DataClientToComm;
import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.common.user.User;
import com.lo23.common.user.UserIdentity;
import com.lo23.communication.CommunicationManager.Client.CommunicationManagerClient;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.communication.Messages.FileMessage;

/**
 * Message pour demander une partie de fichier de l'utilisateur
 */
public class getFileMsg extends FileMessage
{
	/**
	 * serialVersionUID : l'identifiant unique de la classe
	 */
	private static final long serialVersionUID = 82L;
	/**
	 * userAsking :  l'utilisateur qui possede le fichier voulu
	 */
	protected User userAsking;
	/**
	 * userSource : l'utilisateur source qui veut une partie de fichier a telecharger
	 */
	protected User userSource;
	/**
	 * part : la partie
	 */
	protected long part;

	/**
	 * Constructeur
	 * @param userAsking : l'utilisateur qui possede le fichier voulu
	 * @param userSource : l'utilisateur source qui veut une partie de fichier a telecharger
	 * @param file : le fichier a telecherger
	 * @param part : la partie de ficher dont on a besion
	 */
	public getFileMsg(User userAsking, User userSource, FileHandlerInfos file, long part)
	{
		this.file = file;
		this.userAsking = userAsking;
		this.userSource=userSource;
		this.part=part;
	}

	/**
	 * crée le message pour demander une partie de fichier de l'utilisateur
	 */
	public void treatment()
	{
		/**
		 * Récuperation de communication manager coté Client
		 */
		CommunicationManagerClient commManagerClient = CommunicationManagerClient.getInstance();
		/**
		 * Récupération de l'interface de dataClient
		 */
		DataClientToComm dataInterface = commManagerClient.getDataInterface();
		/**
		 * Appel de la methode de data getFilePart
		 */
		dataInterface.getFilePart(this.userAsking, this.userSource, this.file, this.part);
	}
	
	public boolean isToServ()
	{
		return true;
	}
}
