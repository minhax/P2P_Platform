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
	void treatment(){
		//CommToDataServer.addComment( this.getFile(), this.getComment());
	}
}
