package com.lo23.communication.Messages.Files_Client;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.Rating;
import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.common.user.User;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.communication.Messages.FileMessage;
import com.lo23.communication.Messages.Files_Server.sendUpdatedFileMsg;


public class rateFileMsg extends FileMessage {
	private static final long serialVersionUID = 61L;
	protected Rating rate;
	private User user;
	
	public rateFileMsg(Rating r, FileHandlerInfos fi, User usr){
		this.file = fi;
		this.rate = r;
		this.user = usr;
	}
	
	public void treatment(){

		CommunicationManagerServer cms = CommunicationManagerServer.getInstance();
		DataServerToComm dataInterface = cms.getDataInterface();
		
		dataInterface.updateFileWithNewRating(this.file, this.rate, this.user);
		/**Faire le broadcast du message de connection vers tout les utilisateurs connectés**/
		sendUpdatedFileMsg message = new sendUpdatedFileMsg(this.file, this.user);
		message.setPort(this.getPort());
		cms.broadcast(message);
	}

	public boolean isToServ(){return true;}
}
