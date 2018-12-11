package com.lo23.communication.Messages.Files_Client;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.common.user.UserIdentity;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.communication.Messages.FileMessage;
import com.lo23.communication.Messages.Files_Server.sendUpdatedFileMsg;


public class updateFileInfoMsg extends FileMessage {
	private static final long serialVersionUID = 57L;
	private UserIdentity us;
	public updateFileInfoMsg(FileHandlerInfos fi, UserIdentity user){
		this.us = user;
		this.file= fi;
	}

	public void treatment(){
		CommunicationManagerServer cms = CommunicationManagerServer.getInstance();
		DataServerToComm dataInterface = cms.getDataInterface();
		/** TODO Rajouter l'appel  a l'interface data pour l'update des infos classiques
		//dataInterface.(this.file,this.us);
		/**Faire le broadcast du message de connection vers tout les utilisateurs connectés**/
//		sendUpdatedFileMsg message = new sendUpdatedFileMsg(this.file, this.user);
//		message.setPort(this.getPort());
//		cms.broadcast(message);
	}

	public boolean isToServ(){return true;}
}
