package com.lo23.communication.Messages;

import com.lo23.common.user.UserIdentity;

public abstract class UserMessage {
	protected UserIdentity user;
	
	public UserIdentity getUser() {
		return user;
	}
}
