package com.lo23.communication.Messages.Users_Server;

import com.lo23.common.user.UserIdentity;
import com.lo23.communication.Messages.UserMessage;

public class removeDisconnectedUserMsg extends UserMessage{
	//protected  Vector<FileHandlerInfos> fhInfos;
	public removeDisconnectedUserMsg(UserIdentity ui){
		this.user = ui;
	}
	void treatment(){
	
	}
}
