package com.lo23.communication.Messages.Users_Client;

import com.lo23.common.interfaces.comm.CommToDataServer;
import com.lo23.common.user.UserIdentity;
import com.lo23.communication.Messages.UserMessage;

public class addContactMsg extends UserMessage{

	public addContactMsg(UserIdentity ui){
		this.user = ui;
	}
	public void treatment(){
	}
}
