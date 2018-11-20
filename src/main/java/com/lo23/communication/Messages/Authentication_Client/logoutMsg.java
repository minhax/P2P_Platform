package com.lo23.communication.Messages.Authentication_Client;

import com.lo23.common.exceptions.CommException;
import com.lo23.common.user.UserStats;
import com.lo23.communication.Messages.Authentication;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.common.interfaces.data.DataServerToComm;
public class logoutMsg extends Authentication {
	
	protected String UserIPAdress;
	public logoutMsg(UserStats us, String ipAdress){
		this.userStats= us;
		this.UserIPAdress =ipAdress;
	}
	/**
	 * Recupere le cms
	 * Recupere son objet dataServeur
	 * Utilise la methode removeDisconnectedUser pour transmettre la demande de deconnexion
	 * Supprime la paire IPUser - IPServer dans la table
	 * @param UserStats utilisateur statistiques
	 */
	public void treatment(){
		
		CommunicationManagerServer cms = CommunicationManagerServer.getInstance();
		DataServerToComm dataInterface = cms.getDataInterface();
		
		dataInterface.removeDisconnectedUser(this.userStats);
		try {
			cms.removeUserFromTable(this.UserIPAdress);
		}catch(CommException e){
			System.out.println("Message: \t");
			System.out.println(e.getMessage());
			System.out.println("\t printStackTrace: \t");
			e.printStackTrace();
		}
		finally{
			System.out.print("\t Non arret du logiciel");
		}
	}
}
