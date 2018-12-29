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
 * ....
 */
public class getFileMsg extends FileMessage
{
	/**
	 * serialVersionUID : l'identifiant unique de la classe
	 */
	private static final long serialVersionUID = 82L;
	/**
	 * userAsking : l'utilisateur ...
	 */
	protected User userAsking;
	/**
	 * userSource : l'utilisateur ...
	 */
	protected User userSource;
	/**
	 * part : la partie
	 */
	protected long part;

	/**
	 * Constructeur
	 * @param userAsking : ...
	 * @param userSource : ....
	 * @param file : ....
	 * @param part : ....
	 */
	public getFileMsg(User userAsking, User userSource, FileHandlerInfos file, long part)
	{
		this.file = file;
		this.userAsking = userAsking;
		this.userSource=userSource;
		this.part=part;
	}

	/**
	 * ..
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
