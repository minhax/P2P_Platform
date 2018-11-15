package com.lo23.communication.Messages.Users_Server;

import com.lo23.common.user.UserIdentity;
import com.lo23.communication.Messages.UserMessage;

public class fileLocalisationMsg extends UserMessage{
	//protected  Vector<UserIdentity> users;
	public fileLocalisationMsg(UserIdentity ui){
		this.user = ui;
	}
	public void treatment(){
	
	}
}