package com.lo23.communication.Messages.Users_Server;

import com.lo23.common.interfaces.data.DataClientToComm;
import com.lo23.common.user.UserStats;
import com.lo23.communication.CommunicationManager.Client.CommunicationManagerClient;
import com.lo23.communication.Messages.UserMessage;

public class removeDisconnectedUserMsg extends UserMessage{
	private static final long serialVersionUID = 44L;
	private UserStats usr;
	
	public removeDisconnectedUserMsg(UserStats ui){
		this.usr = ui;
	}
	/**
	 * Traitement est applique du cote client
	 * Recupere le communicationManagerClient
	 * Appel la methode addNewConnectedUser pour lui transmettre son objet user Stats
	 * Appel la methode addNewUserFiles pour lui transmettre ses filesInfos
	 */
	public void treatment(){
		CommunicationManagerClient cms = CommunicationManagerClient.getInstance();
		DataClientToComm dataInterface = cms.getDataInterface();
		
		dataInterface.notifyOtherUserDisconnectedToAll(this.usr);
	}

    public boolean isToServ(){return false;}
}
