package com.lo23.communication.Messages.Authentication_Client;

import java.net.InetAddress;

public class AdressIpMsg {
	protected InetAddress ip;
	public AdressIpMsg(InetAddress ipAddress)
	{
		this.ip = ipAddress;
	}
}
