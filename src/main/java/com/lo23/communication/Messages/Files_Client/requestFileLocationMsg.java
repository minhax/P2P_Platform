package com.lo23.communication.Messages.Files_Client;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.common.user.UserIdentity;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.communication.Messages.FileMessage;
import com.lo23.communication.Messages.Files_Server.sendUpdatedFileMsg;


public class requestFileLocationMsg extends FileMessage {
	private static final long serialVersionUID = 60L;
	protected UserIdentity user;
	private CommunicationManagerServer commManager;
	
	public requestFileLocationMsg(FileHandlerInfos fi, UserIdentity u, CommunicationManagerServer cms){
		this.file = fi;
		this.user = u;
		this.commManager=cms;
	}
	
	public void treatment(){
		DataServerToComm dataInterface = this.commManager.getDataInterface();

		dataInterface.requestFileLocationServer(this.file);
	}

	public boolean isToServ(){return true;}
}
