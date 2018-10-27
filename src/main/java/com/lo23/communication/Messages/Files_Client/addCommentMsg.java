package com.lo23.communication.Messages.Files_Client;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.Comment;
import com.lo23.communication.Messages.FileMessage;

public class addCommentMsg extends FileMessage {
	
	protected Comment comment;
	public addCommentMsg(FileHandlerInfos fi, Comment c){
		this.file = fi;
		this.comment = c;
	}
	
	void treatment(){
	
	}
}
