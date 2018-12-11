package com.lo23.communication.Messages.Files_Client;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.communication.Messages.FileMessage;

public class searchFileMsg extends FileMessage {
	
	public searchFileMsg(FileHandlerInfos fi){
		this.file = fi;
	}
	
	public void treatment(){
	
	}

	public boolean isToServ(){return true;}
}
