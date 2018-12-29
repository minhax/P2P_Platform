package com.lo23.communication.Messages.Files_Server;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.data.DataClientToComm;
import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.common.user.User;
import com.lo23.communication.CommunicationManager.Client.CommunicationManagerClient;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.communication.Messages.FileMessage;


public class sendFileMsg extends FileMessage
{
	/**
	 * userAsking : l'utilisateur
	 */
	private User userAsking;
	/**
	 * userSource : l'utilisateur
	 */
	private User userSource;
	/**
	 * part :
	 */
	private long part;
	/**
	 * content : le contenu de fichier
	 */
	private byte[] content;

	/**
	 * Constructeur
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
	 *
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
		 *
		 */
		dataInterface.receiveFilePart(this.file, this.part, this.content);
	}

	public boolean isToServ()
	{
		return true;
	}
}
