package com.lo23.communication.Messages.Authentication_Client;

import com.lo23.common.exceptions.CommException;
import com.lo23.common.user.UserStats;
import com.lo23.communication.Messages.Authentication;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.common.user.User;
public class logoutMsg extends Authentication {
	
	protected String IPAdress;
	public logoutMsg(UserStats us, String ipAdress){
		this.userStats= us;
	}
	
	void treatment(){
		
		/**
		 * Recupere le communication manager cote serveur.
		 * Recupere son interface de dataServeur
		 * Utilise la methode removeDisconnectedUser pour transmettre la demande de deconnexion
		 * @param UserStats utilisateur statistiques
		 */
		
		CommunicationManagerServer cms = CommunicationManagerServer.getInstance();
		DataServerToComm dataInterface = cms.getDataInterface();
		
		dataInterface.removeDisconnectedUser(this.userStats);
		/** Suppression de l'entree dans la table de hachage chainee **/
		try {
			cms.removeUserFromTable(this.IPAdress);
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
