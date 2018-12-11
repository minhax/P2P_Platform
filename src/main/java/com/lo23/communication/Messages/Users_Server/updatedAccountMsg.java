package com.lo23.communication.Messages.Users_Server;

import com.lo23.common.interfaces.data.DataClientToComm;
import com.lo23.common.user.UserIdentity;
import com.lo23.common.user.UserStats;
import com.lo23.communication.CommunicationManager.Client.CommunicationManagerClient;
import com.lo23.communication.Messages.UserMessage;

public class updatedAccountMsg extends UserMessage{
	private static final long serialVersionUID = 43L;
	public updatedAccountMsg(UserIdentity ui){
		this.user = ui;
	}
	public void treatment()
	{
		/**
		 * Recupere le communication manager cote client
		 * Recupere son interface de dataClient
		 * Appel la methode notifyOtherUserUpdatedAccountToAll
		 */

		CommunicationManagerClient cmc = CommunicationManagerClient.getInstance();
		DataClientToComm dataInterfaceClient = cmc.getDataInterface();
		dataInterfaceClient.notifyOtherUserUpdatedAccountToAll(user);
	}

    public boolean isToServ(){return false;}
}
