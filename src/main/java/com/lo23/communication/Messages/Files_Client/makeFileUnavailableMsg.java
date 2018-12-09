package com.lo23.communication.Messages.Files_Client;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.common.user.User;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.communication.Messages.FileMessage;

public class makeFileUnavailableMsg extends FileMessage {

	User user;
	
	public makeFileUnavailableMsg(FileHandlerInfos fi, User us){
		this.file = fi;
		this.user=us;
	}
	
	public void treatment(){
		CommunicationManagerServer cms = CommunicationManagerServer.getInstance();
		DataServerToComm dataInterface = cms.getDataInterface();
		FileHandler fi=(FileHandler) file;
		dataInterface.removeFileSource(fi, user);
	
	}

	public boolean isToServ(){return true;}
}
