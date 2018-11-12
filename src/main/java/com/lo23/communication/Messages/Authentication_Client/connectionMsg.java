package com.lo23.communication.Messages.Authentication_Client;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.user.UserStats;
import com.lo23.communication.Messages.Authentication;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.common.interfaces.data.DataServerToComm;

import java.net.InetAddress;
import java.util.List;

public class connectionMsg extends Authentication {
	private String serverIp;
	private String myIp;
	private List<FileHandlerInfos> fileInfo;

	public connectionMsg(UserStats us){
		this.userStats = us;
	}

	public connectionMsg(UserStats us, List<FileHandlerInfos> files, String serverIp, String myIp ){
		this.userStats = us;
		this.serverIp = serverIp;
		this.fileInfo = files;
		this.myIp = myIp;
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
		/** appel des methodes de l'interface data
		 *
		 */
		dataInterface.addNewConnectedUser(this.userStats);
		dataInterface.addNewUserFiles(this.fileInfo, this.userStats);
		
		/** stockage des informations concernant l'adresse IP du server et du client dans le cms
		 */
		cms.addEntryInClientAndServerIPArray(this.myIp,this.serverIp);
		
	}
}

