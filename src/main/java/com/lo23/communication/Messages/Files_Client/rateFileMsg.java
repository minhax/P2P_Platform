package com.lo23.communication.Messages.Files_Client;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.Rating;
import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.common.user.User;
import com.lo23.communication.CommunicationManager.Client.CommunicationManagerClient;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.communication.Messages.FileMessage;
import com.lo23.communication.Messages.Files_Server.*;


public class rateFileMsg extends FileMessage {
	
	protected Rating rate;
	private User user;
	private CommunicationManagerServer commManagerServer;
	private CommunicationManagerClient commManagerClient;
	
	public rateFileMsg(FileHandlerInfos fi, Rating r, User usr, CommunicationManagerServer cms, CommunicationManagerClient cmc){
		this.file = fi;
		this.rate = r;
		this.user = usr;
		this.commManagerServer=cms;
		this.commManagerClient=cmc;
	}
	
	public void treatment(){

		DataServerToComm dataInterface = this.commManagerServer.getDataInterface();

		this.file.addRating(this.rate);
		dataInterface.updateFileWithNewRating(this.file, this.rate, this.user);
		/**Faire le broadcast du message de connection vers tout les utilisateurs connectés**/
		sendUpdatedFileMsg message = new sendUpdatedFileMsg(this.file, this.user, this.commManagerClient);
		message.setPort(this.getPort());
		this.commManagerServer.broadcast(message);
	}

	public boolean isToServ(){return true;}
}
