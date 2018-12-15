package com.lo23.communication.Messages.Authentication_Client;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.user.UserStats;
import com.lo23.communication.CommunicationManager.Client.CommunicationManagerClient;
import com.lo23.communication.Messages.Authentication;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.communication.Messages.Users_Server.connectedUserMsg;

import java.util.List;

public class connectionMsg extends Authentication {
	private String UserIPAdress;
	private int UserPort; /** A initialiser !**/
	private List<FileHandlerInfos> fileInfo;
	private CommunicationManagerServer commManagerServer;
	private CommunicationManagerClient commManagerClient;

	public connectionMsg(UserStats us, List<FileHandlerInfos> files, CommunicationManagerServer cms, CommunicationManagerClient cmc){
		this.userStats = us;
		this.fileInfo = files;
		this.commManagerServer=cms;
		this.commManagerClient=cmc;
		try {
			this.UserIPAdress = CommunicationManagerServer.findIPadress();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("Creation du message");
	}
	
	public void treatment(){
		
		/**
		 * Recupere le communication manager cote serveur.
		 * Recupere son interface de dataServeur
		 * Appel la methode addNewConnectedUser pour lui transmettre son objet user Stats
		 * Appel la methode addNewUserFiles pour lui transmettre ses filesInfos
		 */

		DataServerToComm dataInterface = this.commManagerServer.getDataInterface();
		/** On récupère et stocke l'adresse IP du serveur
		 */
		String ServerIpAdress = this.commManagerServer.getIP();
		
		System.out.println("Mon ip = " + this.UserIPAdress);
		System.out.println("Addresse ip  du serveur = " + ServerIpAdress);
		
		dataInterface.addNewConnectedUser(this.userStats);
		dataInterface.addNewUserFiles(this.fileInfo, this.userStats);

		this.commManagerServer.addEntryMap(this.UserIPAdress, this.getPort());
		/**Faire le broadcast du message de connection vers tout les utilisateurs connectés**/
		connectedUserMsg message = new connectedUserMsg(this.userStats, this.fileInfo, this.commManagerClient);
		message.setPort(this.getPort());
		this.commManagerServer.broadcast(message);
	}
	
	public List<FileHandlerInfos> getFileInfo() {

		return fileInfo;
	}
	
	
	public String getMyIp()
	{
		return UserIPAdress;
	}

	public boolean isToServ(){
		return true;
	}
}

