package com.lo23.communication.Messages.Files_Client;

import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.data.DataClientToComm;
import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.common.user.User;
import com.lo23.common.user.UserIdentity;
import com.lo23.communication.CommunicationManager.Client.CommunicationManagerClient;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.communication.Messages.FileMessage;


public class uploadFileMsg extends FileMessage {
	
	protected User user;
	
	public uploadFileMsg(FileHandlerInfos fi, User u){
		this.file = fi;
		this.user = u;
	}
	
	public void treatment(){
		CommunicationManagerServer cms = CommunicationManagerServer.getInstance();
		DataServerToComm dataInterface = cms.getDataInterface();

		dataInterface.addNewFileToServer(file, user);
	
	}
}
