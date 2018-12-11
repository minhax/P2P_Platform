package com.lo23.communication.Messages.Files_Server;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.data.DataClientToComm;
import com.lo23.common.user.UserIdentity;
import com.lo23.communication.CommunicationManager.Client.CommunicationManagerClient;
import com.lo23.communication.Messages.FileMessage;

import java.util.List;

public class fileLocationMsg extends FileMessage{
	
	protected List<UserIdentity> user;
	private static final long serialVersionUID = 54L;
	
	public fileLocationMsg(FileHandlerInfos fi, List<UserIdentity> u){
		this.file = fi;
		this.user = u;
	}
	
	public void treatment(){
		CommunicationManagerClient cmc = CommunicationManagerClient.getInstance();
		DataClientToComm dataInterface = cmc.getDataInterface();
		
		dataInterface.receiveFileLocations(this.user);
	}

    public boolean isToServ(){return false;}
}
