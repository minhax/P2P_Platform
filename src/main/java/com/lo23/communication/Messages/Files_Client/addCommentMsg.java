package com.lo23.communication.Messages.Files_Client;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.Comment;
import com.lo23.common.interfaces.comm.CommToDataServer;
import com.lo23.communication.Messages.FileMessage;
import com.lo23.communication.Messages.Message;

public class addCommentMsg extends FileMessage {
	
	protected Comment comment;
	public addCommentMsg(FileHandlerInfos fi, Comment c){
		this.file = fi;
		this.comment = c;
	}
	
	public Comment getComment() {
		return comment;
	}
	public void treatment(){

		CommunicationManagerServer cms = CommunicationManagerServer.getInstance();
		DataServerToComm dataInterface = cms.getDataInterface();
		/** On récupère et stocke l'adresse IP du serveur
		 */
		String ServerIpAdress = cms.getIP();

		System.out.println("Mon ip = " + this.UserIPAdress);
		System.out.println("Addresse ip  du serveur = " + ServerIpAdress);

		dataInterface.addNewConnectedUser(this.userStats);
		dataInterface.addNewUserFiles(this.fileInfo, this.userStats);

		cms.addEntryMap(this.UserIPAdress, this.getPort());
		/**Faire le broadcast du message de connection vers tout les utilisateurs connectés**/
		connectedUserMsg message = new connectedUserMsg(this.userStats, this.fileInfo);
		message.setPort(this.getPort());
		cms.broadcast(message);
	}

    public boolean isToServ(){return true;}
}
