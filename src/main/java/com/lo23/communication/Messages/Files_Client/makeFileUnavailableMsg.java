package com.lo23.communication.Messages.Files_Client;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.common.user.User;
import com.lo23.communication.CommunicationManager.Client.CommunicationManagerClient;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.communication.Messages.FileMessage;
import com.lo23.communication.Messages.Files_Server.sendUpdatedFileMsg;
import com.lo23.communication.Messages.Users_Server.connectedUserMsg;

public class makeFileUnavailableMsg extends FileMessage {

	private User user;
	private CommunicationManagerServer commManagerServer;
	private CommunicationManagerClient commManagerClient;
	
	public makeFileUnavailableMsg(FileHandlerInfos fi, User us, CommunicationManagerServer cms, CommunicationManagerClient cmc){
		this.file = fi;
		this.user=us;
		this.commManagerServer=cms;
		this.commManagerClient=cmc;
	}
	
	public void treatment(){
		DataServerToComm dataInterface = this.commManagerServer.getDataInterface();
		/** On récupère et stocke l'adresse IP du serveur
		 */
		dataInterface.removeFileSource(this.file, this.user);

		/**Broadcast du message pour le fichier indisponible vers tout les utilisateurs connectés**/
		sendUpdatedFileMsg message= new sendUpdatedFileMsg(this.file, this.user, this.commManagerClient);
		message.setPort(this.getPort());
		this.commManagerServer.broadcast(message);
	}

	public boolean isToServ(){return true;}
}
