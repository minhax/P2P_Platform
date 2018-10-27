package com.lo23.communication.Messages.Authentication_Client;

import com.lo23.common.user.UserIdentity;
import com.lo23.communication.Messages.Authentication;

public class connectionMsg extends Authentication {
	
	public connectionMsg(UserIdentity us){
		this.userI = us;
	}
	
	public void treatment(){
		//login(this.userI.getAge(),)
	}
}
