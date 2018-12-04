package com.lo23.communication.Messages.Files_Client;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.user.UserIdentity;
import com.lo23.communication.Messages.FileMessage;


public class giveRightsToFileMsg extends FileMessage {
	
	protected String rights;
	
	public giveRightsToFileMsg(FileHandlerInfos fi, String r){
		this.file = fi;
		this.rights = r;
	}
	
	public void treatment(){
	
	}

	public boolean isToServ(){return true;}
}
