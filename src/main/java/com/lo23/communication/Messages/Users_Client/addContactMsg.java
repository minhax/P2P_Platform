package com.lo23.communication.Messages.Users_Client;

import com.lo23.common.interfaces.comm.CommToDataServer;
import com.lo23.common.user.UserIdentity;
import com.lo23.communication.Messages.UserMessage;

public class addContactMsg extends UserMessage{

	public addContactMsg(UserIdentity ui){
		this.user = ui;
	}
	public void treatment(){
		//CommToDataServer.addContact(this.user()); // Il faut remplacer CommToDataServer avec l'interface que l'on donne en parametre
	}

	public boolean isToServ(){return true;}
}
