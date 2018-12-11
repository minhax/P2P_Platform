package com.lo23.communication.Messages.Users_Server;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.data.DataClientToComm;
import com.lo23.common.user.UserIdentity;
import com.lo23.common.user.UserStats;
import com.lo23.communication.CommunicationManager.Client.CommunicationManagerClient;
import com.lo23.communication.Messages.UserMessage;

import java.util.List;

public class connectedUserMsg extends UserMessage{
	private static final long serialVersionUID = 46L;
	protected List<FileHandlerInfos> fhInfo;
	private UserStats us;
	private CommunicationManagerClient commManager;
	
	public connectedUserMsg(UserStats ui, List<FileHandlerInfos> fi, CommunicationManagerClient cmc){
		this.us = ui;
		this.fhInfo = fi;
		this.commManager=cmc;
	}
	/**
	 * Traitement est applique du cote client
	 * Recupere le communicationManagerClient
	 * Appel la methode addNewConnectedUser pour lui transmettre son objet user Stats
	 * Appel la methode addNewUserFiles pour lui transmettre ses filesInfos
	 */
	public void treatment(){
		DataClientToComm dataInterface = this.commManager.getDataInterface();
		
		dataInterface.notifyOtherUserConnectedToAll(this.us,this.fhInfo);
	}

    public boolean isToServ(){return false;}
}
