package com.lo23.communication.Messages.Users_Server;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.data.DataClientToComm;
import com.lo23.common.user.UserIdentity;
import com.lo23.communication.CommunicationManager.Client.CommunicationManagerClient;
import com.lo23.communication.Messages.UserMessage;

import java.util.List;

public class removeDisconnectedUserMsg extends UserMessage{
	
	protected List<FileHandlerInfos> fhInfos;
	
	public removeDisconnectedUserMsg(UserIdentity ui){
		this.user = ui;
	}
	/**
	 * Traitement est applique du cote client
	 * Recupere le communicationManagerClient
	 * Appel la methode addNewConnectedUser pour lui transmettre son objet user Stats
	 * Appel la methode addNewUserFiles pour lui transmettre ses filesInfos
	 */
	void treatment(){
		CommunicationManagerClient cms = CommunicationManagerClient.getInstance();
		DataClientToComm dataInterface = cms.getDataInterface();
		
		/* [COM : Minh]Methode en suspens, j'attends la modification de l'interface data sur le parametre de la list en argument */
		//dataInterface.notifyOtherUserDisconnectedToAll(this.user,this.fhInfo);
	}
}
