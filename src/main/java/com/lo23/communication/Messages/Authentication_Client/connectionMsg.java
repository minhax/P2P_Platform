package com.lo23.communication.Messages.Authentication_Client;

import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.user.UserIdentity;
import com.lo23.common.user.UserStats;
import com.lo23.communication.Messages.Authentication;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.communication.Messages.Users_Server.connectedUserMsg;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class connectionMsg extends Authentication {
	private String UserIPAdress;
	private static final long serialVersionUID = 100521L;
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

		cms.addEntryMap_IPPort(this.UserIPAdress, this.getPort());
		cms.addEntryMap_IPUID(this.UserIPAdress, this.userStats.getId());
		
		/**
		 * Recuperation de la liste des utilisateurs connectés
		 */
		HashMap<UserIdentity, Vector<FileHandlerInfos>> listeUsersandFiles = dataInterface.requestUserFiles(this.userStats);
		System.out.println("nb de fichier de la personne connectée : " + listeUsersandFiles.values());
		connectedUserMsg message = new connectedUserMsg(listeUsersandFiles);
		message.setPort(this.getPort());
		
		cms.broadcast(message);
	}
	
	public boolean isToServ(){return true;}
}

