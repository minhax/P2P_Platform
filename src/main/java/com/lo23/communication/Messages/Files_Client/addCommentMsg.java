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
	
	private Comment comment;
	private User user;
	private CommunicationManagerServer commManager;
	public addCommentMsg(FileHandlerInfos fi, Comment c, User , CommunicationManagerServer cms){
		this.file = fi;
		this.comment = c;
		this.user = usr;
		this.commManager=cms;
	}
	
	public Comment getComment() {
		return comment;
	}
	public void treatment(){
		DataServerToComm dataInterface = this.commManager.getDataInterface();
		file.addComment(this.getComment());
		dataInterface.updateFileChanges(this.file);
		/**Faire le broadcast du message de connection vers tout les utilisateurs connectés**/
		sendUpdatedFileMsg message = new sendUpdatedFileMsg(this.file, this.user);
		message.setPort(this.getPort());
		this.commManager.broadcast(message);
	}

    public boolean isToServ(){return true;}
}
