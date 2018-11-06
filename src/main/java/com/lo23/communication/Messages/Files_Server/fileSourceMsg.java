package com.lo23.communication.Messages.Files_Server;


import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.user.UserIdentity;
import com.lo23.communication.Messages.FileMessage;

public class fileSourceMsg extends FileMessage{
	
	protected UserIdentity user;
	
	public fileSourceMsg(FileHandlerInfos fi, UserIdentity u){
		this.file = fi;
		this.user = u;
	}
	
	void treatment(){
	
	}
}
