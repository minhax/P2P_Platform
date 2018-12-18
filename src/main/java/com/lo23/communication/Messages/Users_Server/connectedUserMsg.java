package com.lo23.communication.Messages.Users_Server;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.data.DataClientToComm;
import com.lo23.common.user.UserIdentity;
import com.lo23.communication.CommunicationManager.Client.CommunicationManagerClient;
import com.lo23.communication.Messages.UserMessage;

import java.util.HashMap;
import java.util.Vector;

public class connectedUserMsg extends UserMessage{
	
	private static final long serialVersionUID = 46L;
	private HashMap<UserIdentity, Vector<FileHandlerInfos>>  usersInfoAndFiles;
	
	public connectedUserMsg(HashMap<UserIdentity,Vector<FileHandlerInfos>> usersInfoAndFiles){
		this.usersInfoAndFiles = usersInfoAndFiles;
	}
	/**
	 * Traitement est applique du cote client
	 * Recupere le communicationManagerClient
	 * Appel la methode addNewConnectedUser pour lui transmettre son objet user Stats
	 * Appel la methode addNewUserFiles pour lui transmettre ses filesInfos
	 */
	public void treatment(){
		CommunicationManagerClient cmc = CommunicationManagerClient.getInstance();
		DataClientToComm dataInterface = cmc.getDataInterface();
		
		dataInterface.notifyOtherUserConnectedToAll(this.usersInfoAndFiles);
	}

    public boolean isToServ(){return false;}
}
