package com.lo23.communication.Messages.Files_Client;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.communication.Messages.FileMessage;

public class addSourceMsg extends FileMessage {
	
	public addSourceMsg(FileHandlerInfos fi){
		this.file = fi;
	}
	void treatment(){
	
	}
}
