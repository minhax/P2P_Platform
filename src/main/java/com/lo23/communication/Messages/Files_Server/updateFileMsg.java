package com.lo23.communication.Messages.Files_Server;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.communication.Messages.FileMessage;

public class updateFileMsg extends FileMessage{
	
	public updateFileMsg(FileHandlerInfos fi){
		this.file = fi;
	}
	
	public void treatment(){
	
	}

    public boolean isToServ(){return false;}
}
