package com.lo23.communication.Messages.Files_Server;

import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.interfaces.data.*;
import com.lo23.common.user.User;
import com.lo23.communication.CommunicationManager.Client.*;
import com.lo23.communication.CommunicationManager.Server.*;
import com.lo23.communication.Messages.FileMessage;


public class sendFileMsg extends FileMessage {
	private User userAsking;
	private User userSource;
	private long part;
	private byte[] content;

	public sendFileMsg(User userAsking, User userSource, FileHandler file, long part, byte[] content){
		this.file = file;
		this.userAsking=userAsking;
		this.userSource=userSource;
		this.content=content;
		this.part=part;
	}

	public void treatment(){
		CommunicationManagerClient cmc = CommunicationManagerClient.getInstance();
		DataClientToComm dataInterface = cmc.getDataInterface();
		dataInterface.receiveFilePart(this.file, this.part, this.content);
	}


	public boolean isToServ(){return true;}
}
