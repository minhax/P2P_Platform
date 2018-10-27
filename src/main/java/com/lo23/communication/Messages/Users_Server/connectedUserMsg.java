package com.lo23.communication.Messages.Users_Server;

import com.lo23.common.user.UserIdentity;
import com.lo23.communication.Messages.UserMessage;

public class connectedUserMsg extends UserMessage{
	//protected Vector<FileHandlerInfos> fhInfo;
	public connectedUserMsg(UserIdentity ui){
		this.user = ui;
	}
	void treatment(){
	
	}
}
