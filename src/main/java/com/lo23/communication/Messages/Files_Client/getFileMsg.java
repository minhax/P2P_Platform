package com.lo23.communication.Messages.Files_Client;

import com.lo23.common.filehandler.*;
import com.lo23.common.interfaces.data.*;
import com.lo23.common.user.User;
import com.lo23.communication.CommunicationManager.Client.*;
import com.lo23.communication.Messages.FileMessage;

public class getFileMsg extends FileMessage {
	private static final long serialVersionUID = 82L;
	protected User userAsking;
	protected User userSource;
	protected long part;
	
	public getFileMsg(User userAsking, User userSource, FileHandler file, long part){
		this.file = file;
		this.userAsking = userAsking;
		this.userSource=userSource;
		this.part=part;
	}
	
	public void treatment(){
		CommunicationManagerClient cms = CommunicationManagerClient.getInstance();
		DataClientToComm dataInterface = cms.getDataInterface();
		dataInterface.getFilePart(this.userAsking, this.userSource, this.file, this.part);
	}
	
	public boolean isToServ(){return true;}
}
