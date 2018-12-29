package com.lo23.communication.Messages;

import com.lo23.common.user.UserIdentity;

public abstract class UserMessage extends Message
{
	protected UserIdentity user;
	
	public UserIdentity getUser()
	{
		return user;
	}
	public abstract void treatment();
}
