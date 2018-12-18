package com.lo23.communication.Messages.Users_Client;

import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.common.user.UserIdentity;
import com.lo23.common.user.UserStats;
import com.lo23.communication.CommunicationManager.Client.CommunicationManagerClient;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.communication.Messages.UserMessage;
import com.lo23.communication.Messages.Users_Server.updatedAccountMsg;

public class updateUserInfoMsg extends UserMessage{

	private CommunicationManagerServer commManagerServer;
	private CommunicationManagerClient commManagerClient;
	
	public updateUserInfoMsg(UserIdentity ui, CommunicationManagerServer cms, CommunicationManagerClient cmc)
    {
		this.user = ui;
		this.commManagerServer=cms;
		this.commManagerClient=cmc;
	}
	public void treatment()
    {
        /**
         * Recupere le communication manager cote serveur
         * Recupere son interface de dataServer
         * Appel la methode updateUserChanges
         */

		DataServerToComm dataInterface = this.commManagerServer.getDataInterface();
		/** On récupère et stocke l'adresse IP du serveur
		 */
		dataInterface.updateUserChanges(user);

	    /** Création du message pour le broadcast des informations**/
	    updatedAccountMsg message = new updatedAccountMsg(this.user, this.commManagerClient);
		message.setPort(this.getPort());
	    this.commManagerServer.broadcast(message);
	}

    public boolean isToServ(){return true;}
}
