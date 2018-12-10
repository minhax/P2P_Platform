package com.lo23.communication.Messages.Files_Client;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.common.user.UserIdentity;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.communication.Messages.FileMessage;
import com.lo23.communication.Messages.Files_Server.sendUpdatedFileMsg;


public class requestFileLocationMsg extends FileMessage {
	
	protected UserIdentity user;
	
	public requestFileLocationMsg(FileHandlerInfos fi, UserIdentity u){
		this.file = fi;
		this.user = u;
	}
	
	public void treatment(){
		CommunicationManagerServer cms = CommunicationManagerServer.getInstance();
		DataServerToComm dataInterface = cms.getDataInterface();

		dataInterface.requestFileLocationServer(this.file);
	}

	public boolean isToServ(){return true;}
}
