package com.lo23.communication.Messages.Files_Client;

import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.common.user.User;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.communication.Messages.FileMessage;
import com.lo23.communication.Messages.Files_Server.sendUpdatedFileMsg;

public class makeFileUnavailableMsg extends FileMessage {
	private static final long serialVersionUID = 78L;
	User user;
	
	public makeFileUnavailableMsg(FileHandler fi, User us){
		this.file = fi;
		this.user=us;
	}
	
	public void treatment(){
		CommunicationManagerServer cms = CommunicationManagerServer.getInstance();
		DataServerToComm dataInterface = cms.getDataInterface();
		/** On récupère et stocke l'adresse IP du serveur
		 */
		dataInterface.removeFileSource(this.file, this.user);

		/**Broadcast du message pour le fichier indisponible vers tout les utilisateurs connectés**/
		sendUpdatedFileMsg message= new sendUpdatedFileMsg(this.file, this.user);
		message.setPort(this.getPort());
		cms.broadcast(message);

	}

	public boolean isToServ(){return true;}
}
