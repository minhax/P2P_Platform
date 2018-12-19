package com.lo23.communication.Messages.Files_Server;

import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.data.DataClientToComm;
import com.lo23.common.user.UserIdentity;
import com.lo23.communication.CommunicationManager.Client.CommunicationManagerClient;
import com.lo23.communication.Messages.FileMessage;

public class newFileInfoMsg extends FileMessage{
	
	protected UserIdentity user;
	private static final long serialVersionUID = 52L;
	public newFileInfoMsg(FileHandler fi, UserIdentity u){
		this.file = fi;
		this.user = u;
	}
	
	public void treatment(){
		CommunicationManagerClient cmc = CommunicationManagerClient.getInstance();
		DataClientToComm dataInterface = cmc.getDataInterface();
		
		/** Appel de la methode **/
		dataInterface.notifyNewSharedFileToAll(this.file, this.user);
	
	}

    public boolean isToServ(){return false;}
}
