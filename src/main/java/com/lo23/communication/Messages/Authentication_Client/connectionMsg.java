package com.lo23.communication.Messages.Authentication_Client;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.user.User;
import com.lo23.common.user.UserStats;
import com.lo23.communication.CommunicationManager.Server.ClientInfo;
import com.lo23.communication.Messages.Authentication;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.communication.Messages.Users_Server.connectedUserMsg;

import java.util.List;

public class connectionMsg extends Authentication {
	private String UserIPAdress;
	private int UserPort; /** A initialiser !**/
	private List<FileHandlerInfos> fileInfo;

	public connectionMsg(UserStats us, List<FileHandlerInfos> files ){
		this.userStats = us;
		this.fileInfo = files;
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
		
		CommunicationManagerServer cms = CommunicationManagerServer.getInstance();
		DataServerToComm dataInterface = cms.getDataInterface();
		/** On récupère et stocke l'adresse IP du serveur
		 */
		String ServerIpAdress = cms.getIP();
		
		System.out.println("Mon ip = " + this.UserIPAdress);
		System.out.println("Addresse ip  du serveur = " + ServerIpAdress);
		
		dataInterface.addNewConnectedUser(this.userStats);
		dataInterface.addNewUserFiles(this.fileInfo, this.userStats);
		
		/** On crée l'objet client avec adresse IP et port du client **/
		ClientInfo client = new ClientInfo(this.UserIPAdress,this.UserPort);
		/** ajout dans la structure de données du serveur **/
		cms.addEntryInClientAndServerIPArray(client, ServerIpAdress);
		/**broadcast du message de connection vers tout les utilisateurs connectés**/
		connectedUserMsg message=new connectedUserMsg(this.userStats, this.fileInfo);
		cms.broadcast(message);
	}
	
	public List<FileHandlerInfos> getFileInfo() {
		return fileInfo;
	}
	
	
	public String getMyIp() {
		return UserIPAdress;
	}
}

