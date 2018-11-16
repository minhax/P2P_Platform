package com.lo23.communication.Messages.Files_Client;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.communication.Messages.FileMessage;


public class updateFileInfoMsg extends FileMessage {
	
	protected String champ;
	
	public updateFileInfoMsg(FileHandlerInfos fi, String s){
		this.file = fi;
		this.champ = s;
	}
	
	public void treatment(){
	
	}
}
