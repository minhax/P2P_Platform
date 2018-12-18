package com.lo23.communication.Messages.Files_Server;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.data.DataClientToComm;
import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.common.user.User;
import com.lo23.common.user.UserIdentity;
import com.lo23.communication.CommunicationManager.Client.CommunicationManagerClient;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.communication.Messages.FileMessage;
import com.lo23.communication.Messages.Users_Server.updatedAccountMsg;

public class sendUpdatedFileMsg extends FileMessage{
	private User user;
	private CommunicationManagerClient commManager;

	public sendUpdatedFileMsg(FileHandlerInfos fi, User user, CommunicationManagerClient cmc){
		this.file = fi;
		this.user = user;
		this.commManager=cmc;
	}
	
	public void treatment(){
        /**
         * Recupere le communication manager cote client
         * Recupere son interface de dataClient
         * Appel la methode notifyOtherUserUpdatedAccountToAll
         */
        DataClientToComm dataInterfaceClient = this.commManager.getDataInterface();
        dataInterfaceClient.notifyUpdatedSharedFileToAll(this.file,this.user);
	
	}

    public boolean isToServ(){return false;}
}
