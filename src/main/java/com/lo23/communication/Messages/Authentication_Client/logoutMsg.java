package com.lo23.communication.Messages.Authentication_Client;

import com.lo23.common.user.UserStats;
import com.lo23.communication.Messages.Authentication;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.common.user.User;
public class logoutMsg extends Authentication {
	
	public logoutMsg(UserStats us){
		this.userStats= us;
	}
	
	public void treatment(){
		
		/**
		 * Recupere le communication manager cote serveur.
		 * Recupere son interface de dataServeur
		 * Utilise la methode removeDisconnectedUser pour transmettre la demande de deconnexion
		 * @param UserStats utilisateur statistiques
		 */
		
		CommunicationManagerServer cms = CommunicationManagerServer.getInstance();
		DataServerToComm dataInterface = cms.getDataInterface();
		
		dataInterface.removeDisconnectedUser(this.userStats);
	}
}
