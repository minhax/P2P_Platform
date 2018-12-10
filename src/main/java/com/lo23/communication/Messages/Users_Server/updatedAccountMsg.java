package com.lo23.communication.Messages.Users_Server;

import com.lo23.common.interfaces.data.DataClientToComm;
import com.lo23.common.user.UserIdentity;
import com.lo23.common.user.UserStats;
import com.lo23.communication.CommunicationManager.Client.CommunicationManagerClient;
import com.lo23.communication.Messages.UserMessage;

public class updatedAccountMsg extends UserMessage{
	protected UserStats us;
	public updatedAccountMsg(UserStats ui){
		this.us = ui;
	}
	public void treatment()
	{
		/**
		 * Recupere le communication manager cote client
		 * Recupere son interface de dataClient
		 * Appel la methode notifyOtherUserUpdatedAccountToAll
		 */

		CommunicationManagerClient cms = CommunicationManagerClient.getInstance();
		DataClientToComm dataInterfaceClient = cms.getDataInterface();
		dataInterfaceClient.notifyOtherUserUpdatedAccountToAll(us);
	}
}
