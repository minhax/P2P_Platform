package com.lo23.communication.Messages.Files_Client;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.Comment;
import com.lo23.common.interfaces.comm.CommToDataServer;
import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.common.user.User;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.communication.Messages.FileMessage;
import com.lo23.communication.Messages.Files_Server.sendUpdatedFileMsg;
import com.lo23.communication.Messages.Message;

public class addCommentMsg extends FileMessage {
	private static final long serialVersionUID = 85L;
	private Comment comment;
	private User user;
	public addCommentMsg(FileHandlerInfos fi, Comment c, User usr){
		this.file = fi;
		this.comment = c;
		this.user = usr;
	}
	
	public Comment getComment() {
		return comment;
	}
	public void treatment(){

		CommunicationManagerServer cms = CommunicationManagerServer.getInstance();
		DataServerToComm dataInterface = cms.getDataInterface();
		
		dataInterface.updateFileWithNewComment(this.file, this.comment, this.user);
		/**Faire le broadcast du message de connection vers tout les utilisateurs connectés**/
		sendUpdatedFileMsg message = new sendUpdatedFileMsg(this.file, this.user);
		message.setPort(this.getPort());
		cms.broadcast(message);
	}

    public boolean isToServ(){return true;}
}
