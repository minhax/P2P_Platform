package com.lo23.communication.Messages.Authentication_Client;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.user.User;
import com.lo23.common.user.UserStats;
import com.lo23.communication.Messages.Authentication;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.communication.Messages.Users_Server.connectedUserMsg;

import java.util.List;

public class connectionMsg extends Authentication {
	private String myIp;
	private List<FileHandlerInfos> fileInfo;

	public connectionMsg(UserStats us, List<FileHandlerInfos> files ){
		this.userStats = us;
		this.fileInfo = files;
		try {
			this.myIp = CommunicationManagerServer.findIPadress();
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
		
		System.out.println("Mon ip = " + this.myIp);
		System.out.println("Addresse ip  du serveur = " + ServerIpAdress);
		
		dataInterface.addNewConnectedUser(this.userStats);
		dataInterface.addNewUserFiles(this.fileInfo, this.userStats);

		cms.addEntryInClientAndServerIPArray(this.myIp, ServerIpAdress);
		/**Faire le broadcast du message de connection vers tout les utilisateurs connectés**/
		connectedUserMsg message=new connectedUserMsg(this.userStats, this.fileInfo);
		message.setPort(this.getPort());
		cms.broadcast(message);
	}
	
	public List<FileHandlerInfos> getFileInfo() {
		return fileInfo;
	}
	
	
	public String getMyIp() {
		return myIp;
	}

	public boolean isToServ(){return true;}
}

