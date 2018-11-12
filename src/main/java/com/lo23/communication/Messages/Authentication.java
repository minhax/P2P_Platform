package com.lo23.communication.Messages;

import com.lo23.common.user.UserStats;

public abstract class Authentication extends Message {
	protected UserStats userStats;
	public abstract void treatment();
}
