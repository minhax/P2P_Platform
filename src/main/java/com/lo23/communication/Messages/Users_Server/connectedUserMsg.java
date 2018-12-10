package com.lo23.communication.Messages.Users_Server;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.data.DataClientToComm;
import com.lo23.common.user.UserIdentity;
import com.lo23.common.user.UserStats;
import com.lo23.communication.CommunicationManager.Client.CommunicationManagerClient;
import com.lo23.communication.Messages.UserMessage;

import java.util.List;

public class connectedUserMsg extends UserMessage{
	
	protected List<FileHandlerInfos> fhInfo;
	private UserStats us;
	
	public connectedUserMsg(UserStats ui, List<FileHandlerInfos> fi){
		this.us = ui;
		this.fhInfo = fi;
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
		
		dataInterface.notifyOtherUserConnectedToAll(this.us,this.fhInfo);
	}

    public boolean isToServ(){return false;}
}
