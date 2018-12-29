package com.lo23.communication.Messages.Users_Server;

import com.lo23.common.user.UserIdentity;
import com.lo23.communication.Messages.UserMessage;

public class fileLocalisationMsg extends UserMessage
{
	//protected  Vector<UserIdentity> users;
	private static final long serialVersionUID = 45L;
	public fileLocalisationMsg(UserIdentity ui){
		this.user = ui;
	}
	public void treatment()
	{

	}
    public boolean isToServ()
	{
		return false;
	}
}