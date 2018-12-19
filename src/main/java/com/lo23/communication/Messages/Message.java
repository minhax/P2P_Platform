package com.lo23.communication.Messages;

import java.io.Serializable;

public abstract class Message implements Serializable {
	private int port;
	public abstract void treatment();
	public abstract boolean isToServ();
	public void setPort(int port)
	{
		this.port = port;
	}
	public int getPort(){return this.port;}
}
