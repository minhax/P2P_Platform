package com.lo23.communication.Messages.Files_Client;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.Rating;
import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.common.user.User;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.communication.Messages.FileMessage;
import com.lo23.communication.Messages.Files_Server.sendUpdatedFileMsg;


public class rateFileMsg extends FileMessage {
	
	protected Rating rate;
	private User user;
	private CommunicationManagerServer commManager;
	
	public rateFileMsg(FileHandlerInfos fi, Rating r, User usr, CommunicationManagerServer cms){
		this.file = fi;
		this.rate = r;
		this.user = usr;
		this.commManager=cms;
	}
	
	public void treatment(){

		DataServerToComm dataInterface = this.commManager.getDataInterface();

		this.file.addRating(this.rate);
		dataInterface.updateFileChanges(this.file);
		/**Faire le broadcast du message de connection vers tout les utilisateurs connectés**/
		sendUpdatedFileMsg message = new sendUpdatedFileMsg(this.file, this.user);
		message.setPort(this.getPort());
		this.commManager.broadcast(message);
	}

	public boolean isToServ(){return true;}
}
