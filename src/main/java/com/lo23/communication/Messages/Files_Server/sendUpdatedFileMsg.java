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
	private User usr;
	private static final long serialVersionUID = 51L;
	public sendUpdatedFileMsg(FileHandlerInfos fi, User user){
		this.file = fi;
		this.usr = user;
	}
	
	public void treatment(){
        /**
         * Recupere le communication manager cote client
         * Recupere son interface de dataClient
         * Appel la methode notifyOtherUserUpdatedAccountToAll
         */

        CommunicationManagerClient cmc = CommunicationManagerClient.getInstance();
        DataClientToComm dataInterfaceClient = cmc.getDataInterface();
        dataInterfaceClient.notifyUpdatedSharedFileToAll(this.file,this.usr);
	
	}

    public boolean isToServ(){return false;}
}
