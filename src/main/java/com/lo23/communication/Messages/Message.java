package com.lo23.communication.Messages;

import com.lo23.common.interfaces.data.DataClientToComm;
import java.io.Serializable;

public abstract class Message implements Serializable {
    private static final long serialVersionUID = 42L;
	private int port;
	public abstract void treatment();
	public abstract boolean isToServ();
	public void setPort(int port)
	{
		this.port = port;
	}
	public int getPort(){return this.port;};
}
