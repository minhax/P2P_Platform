package com.lo23.communication.Messages.Authentication_Client;

import com.lo23.common.user.UserStats;
import com.lo23.communication.Messages.Authentication;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.common.interfaces.data.DataServerToComm;

public class connectionMsg extends Authentication {
	
	public connectionMsg(UserStats us){
		this.userStats = us;
	}
	
	public void treatment(){
		
		/**
		 * Recupere le communication manager cote serveur.
		 * Recupere son interface de dataServeur
		 * Utilise la methode addNewConnectedUser pour lui transmettre son objet user Stats
		 */
		
		CommunicationManagerServer cms = CommunicationManagerServer.getInstance();
		DataServerToComm dataInterface = cms.getDataInterface();
		
		dataInterface.addNewConnectedUser(this.userStats);
		
	}
}

