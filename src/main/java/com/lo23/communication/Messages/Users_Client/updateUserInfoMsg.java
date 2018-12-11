package com.lo23.communication.Messages.Users_Client;

import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.common.user.UserIdentity;
import com.lo23.common.user.UserStats;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.communication.Messages.UserMessage;
import com.lo23.communication.Messages.Users_Server.updatedAccountMsg;

public class updateUserInfoMsg extends UserMessage{
	private static final long serialVersionUID = 50L;
	public updateUserInfoMsg(UserIdentity ui)
    {
		this.user = ui;
	}
	public void treatment()
    {
        /**
         * Recupere le communication manager cote serveur
         * Recupere son interface de dataServer
         * Appel la methode updateUserChanges
         */
		CommunicationManagerServer cms = CommunicationManagerServer.getInstance();
		DataServerToComm dataInterface = cms.getDataInterface();
		/** On récupère et stocke l'adresse IP du serveur
		 */
		dataInterface.updateUserChanges(user);

	    /** Création du message pour le broadcast des informations**/
	    updatedAccountMsg message = new updatedAccountMsg(this.user);
		message.setPort(this.getPort());
	    cms.broadcast(message);
	}

    public boolean isToServ(){return true;}
}
