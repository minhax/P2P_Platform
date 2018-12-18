package com.lo23.communication.Messages.Users_Server;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.data.DataClientToComm;
import com.lo23.common.user.UserIdentity;
import com.lo23.common.user.UserStats;
import com.lo23.communication.CommunicationManager.Client.CommunicationManagerClient;
import com.lo23.communication.Messages.UserMessage;
import com.lo23.common.user.User;

import java.util.List;

public class removeDisconnectedUserMsg extends UserMessage{
<<<<<<< HEAD
	private static final long serialVersionUID = 44L;
	private UserStats usr;
	
	public removeDisconnectedUserMsg(UserStats ui){
		this.usr = ui;
=======

	protected List<FileHandlerInfos> fhInfos;
	private CommunicationManagerClient commManager;
	
	public removeDisconnectedUserMsg(UserIdentity ui, List<FileHandlerInfos> fi, CommunicationManagerClient cmc){
		this.user = ui;
		this.fhInfos = fi;
		this.commManager=cmc;
>>>>>>> b3c8c952d5edd679f04bf216db7d24dd97e40c7c
	}
	/**
	 * Traitement est applique du cote client
	 * Recupere le communicationManagerClient
	 * Appel la methode addNewConnectedUser pour lui transmettre son objet user Stats
	 * Appel la methode addNewUserFiles pour lui transmettre ses filesInfos
	 */
	public void treatment(){
<<<<<<< HEAD
		CommunicationManagerClient cms = CommunicationManagerClient.getInstance();
		DataClientToComm dataInterface = cms.getDataInterface();
		
		dataInterface.notifyOtherUserDisconnectedToAll(this.usr);
=======
		DataClientToComm dataInterface = this.commManager.getDataInterface();
		User user = (User)this.user;
		dataInterface.notifyOtherUserDisconnectedToAll(user);
>>>>>>> b3c8c952d5edd679f04bf216db7d24dd97e40c7c
	}

    public boolean isToServ(){return false;}
}
