package com.lo23.communication.Messages;

import com.lo23.common.interfaces.data.DataClientToComm;
import java.io.Serializable;

public abstract class Message implements Serializable {
	public abstract void treatment();
}
