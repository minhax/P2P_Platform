package com.lo23.communication.Messages.Files_Client;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.Comment;
import com.lo23.common.interfaces.comm.CommToDataServer;
import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.common.user.User;
import com.lo23.communication.CommunicationManager.Client.CommunicationManagerClient;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.communication.Messages.FileMessage;
import com.lo23.communication.Messages.Files_Server.sendUpdatedFileMsg;
import com.lo23.communication.Messages.Message;

public class addCommentMsg extends FileMessage {
	
	private Comment comment;
	private User user;
	private CommunicationManagerServer commManagerServer;
	private CommunicationManagerClient commManagerClient;
	public addCommentMsg(FileHandlerInfos fi, Comment c, User usr, CommunicationManagerServer cms, CommunicationManagerClient cmc){
		this.file = fi;
		this.comment = c;
		this.user = usr;
		this.commManagerServer=cms;
		this.commManagerClient=cmc;
	}
	
	public Comment getComment() {
		return comment;
	}
	public void treatment(){
		DataServerToComm dataInterface = this.commManagerServer.getDataInterface();
		file.addComment(this.getComment());
		dataInterface.updateFileWithNewComment(this.file, this.comment, this.user);
		/**Faire le broadcast du message de connection vers tout les utilisateurs connectés**/
		sendUpdatedFileMsg message = new sendUpdatedFileMsg(this.file, this.user, this.commManagerClient);
		message.setPort(this.getPort());
		this.commManagerServer.broadcast(message);
	}

    public boolean isToServ(){return true;}
}
