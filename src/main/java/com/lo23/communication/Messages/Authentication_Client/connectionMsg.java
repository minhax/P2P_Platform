package com.lo23.communication.Messages.Authentication_Client;

import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.filehandler.FileHandlerInfos;
<<<<<<< HEAD
import com.lo23.common.user.UserIdentity;
import com.lo23.common.user.UserStats;
=======
import com.lo23.common.user.UserStats;
import com.lo23.communication.CommunicationManager.Client.CommunicationManagerClient;
>>>>>>> b3c8c952d5edd679f04bf216db7d24dd97e40c7c
import com.lo23.communication.Messages.Authentication;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.communication.Messages.Users_Server.connectedUserMsg;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

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

<<<<<<< HEAD
		cms.addEntryMap(this.UserIPAdress, this.getPort());
		
		/**
		 * Recuperation de la liste des utilisateurs connectés
		 */
		HashMap<UserIdentity, Vector<FileHandlerInfos>> listeUsersandFiles = dataInterface.requestUserFiles();
		
		connectedUserMsg message = new connectedUserMsg(listeUsersandFiles);
		message.setPort(this.getPort());
		
		cms.broadcast(message);
	}
	
	public boolean isToServ(){return true;}
=======
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
>>>>>>> b3c8c952d5edd679f04bf216db7d24dd97e40c7c
}

