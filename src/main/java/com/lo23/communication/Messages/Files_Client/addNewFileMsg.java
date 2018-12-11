package com.lo23.communication.Messages.Files_Client;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.user.UserIdentity;
import com.lo23.communication.Messages.FileMessage;


public class addNewFileMsg extends FileMessage {
	
	protected UserIdentity user;
	
	public addNewFileMsg(FileHandlerInfos fi, UserIdentity u){
		this.file = fi;
		this.user = u;
	}
	
	public void treatment(){
	
	}

	public boolean isToServ(){return true;}
}
