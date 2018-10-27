package com.lo23.communication.Messages.Users_Client;

import com.lo23.common.user.UserIdentity;
import com.lo23.communication.Messages.UserMessage;

public class giveRightsToUserMsg extends UserMessage{
	
	protected String rights;
	
	public giveRightsToUserMsg(UserIdentity ui, String r){
		this.user = ui;
		this.rights = r;
	}
	void treatment(){
	
	}
}