package com.lo23.communication.Messages.Files_Client;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.communication.Messages.FileMessage;

public class makeFileUnavailableMsg extends FileMessage {
	
	public makeFileUnavailableMsg(FileHandlerInfos fi){
		this.file = fi;
	}
	
	public void treatment(){
	
	}
}
