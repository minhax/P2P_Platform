package com.lo23.communication.Messages.Files_Client;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.common.user.User;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.communication.Messages.FileMessage;
import com.lo23.communication.Messages.Files_Server.sendUpdatedFileMsg;
import com.lo23.communication.Messages.Users_Server.connectedUserMsg;

public class makeFileUnavailableMsg extends FileMessage {

	private User user;
	private CommunicationManagerServer commManager;
	
	public makeFileUnavailableMsg(FileHandlerInfos fi, User us, CommunicationManagerServer cms){
		this.file = fi;
		this.user=us;
		this.commManager=cms;
	}
	
	public void treatment(){
		DataServerToComm dataInterface = this.commManager.getDataInterface();
		/** On récupère et stocke l'adresse IP du serveur
		 */
		dataInterface.removeFileSource(this.file, this.user);

		/**Broadcast du message pour le fichier indisponible vers tout les utilisateurs connectés**/
		sendUpdatedFileMsg message= new sendUpdatedFileMsg(this.file, this.user);
		message.setPort(this.getPort());
		this.commManager.broadcast(message);
	}

	public boolean isToServ(){return true;}
}
