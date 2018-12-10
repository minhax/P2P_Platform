package com.lo23.communication.Messages.Files_Server;


import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.data.DataClientToComm;
import com.lo23.common.user.User;
import com.lo23.common.user.UserIdentity;
import com.lo23.communication.CommunicationManager.Client.CommunicationManagerClient;
import com.lo23.communication.Messages.FileMessage;

public class fileSourceMsg extends FileMessage{
	
	protected UserIdentity user;
	//TODO: est-ce qu'un user ne suffirait pas ? -> dans ce cas, il faut aussi modifier la méthode dans l'API de data et dans CommToDataServer
	public fileSourceMsg(FileHandlerInfos fi, UserIdentity u){
		this.file = fi;
		this.user = u;
	}
	
	public void treatment()
	{
		CommunicationManagerClient cms = CommunicationManagerClient.getInstance();
		DataClientToComm dataInterface = cms.getDataInterface();
		FileHandler fi=(FileHandler) file;
		dataInterface.notifyNewSourceToAll(fi, user);
		//dataInterface.notifyNewSharedFileToAll();
	}
}
