package com.lo23.communication.Messages.Users_Client;

import com.lo23.common.user.UserIdentity;
import com.lo23.communication.Messages.UserMessage;

public class updateUserInfoMsg extends UserMessage{
	
	public updateUserInfoMsg(UserIdentity ui){
		this.user = ui;
	}
	public void treatment(){
	
	}
}
