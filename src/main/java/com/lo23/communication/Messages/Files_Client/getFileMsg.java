package com.lo23.communication.Messages.Files_Client;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.data.DataClientToComm;
import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.common.user.User;
import com.lo23.common.user.UserIdentity;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.communication.Messages.FileMessage;

public class getFileMsg extends FileMessage {
	private static final long serialVersionUID = 82L;
	protected User userAsking;
	protected User userSource;
	protected long part;
	
	public getFileMsg(User userAsking, User userSource, FileHandlerInfos file, long part){
		this.file = file;
		this.userAsking = userAsking;
		this.userSource=userSource;
		this.part=part;
	}
	
	public void treatment(){
		CommunicationManagerServer cms = CommunicationManagerServer.getInstance();
		DataServerToComm dataInterface = cms.getDataInterface();
		//dataInterface.getFilePart(this.userAsking, this.userSource, this.file, this.part);
	}
	
	public boolean isToServ(){return true;}
}
