package com.lo23.communication.Messages.Authentication_Client;

import com.lo23.common.user.UserIdentity;
import com.lo23.communication.Messages.Authentication;

public class logoutMsg extends Authentication {
	
	public logoutMsg(UserIdentity us){
		this.userI= us;
	}
	
	void treatment(){
	
	}
}
