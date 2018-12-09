package com.lo23.communication.Messages.Files_Client;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.Rating;
import com.lo23.communication.Messages.FileMessage;


public class rateFileMsg extends FileMessage {
	
	protected Rating rate;
	
	public rateFileMsg(FileHandlerInfos fi, Rating r){
		this.file = fi;
		this.rate = r;
	}
	
	public void treatment(){
	
	}

	public boolean isToServ(){return true;}
}
