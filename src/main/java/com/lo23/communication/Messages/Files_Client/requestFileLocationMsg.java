package com.lo23.communication.Messages.Files_Client;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.common.user.UserIdentity;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.communication.Messages.FileMessage;
import com.lo23.communication.Messages.Files_Server.fileLocationMsg;
import com.lo23.communication.Messages.Files_Server.sendUpdatedFileMsg;

import java.util.List;


public class requestFileLocationMsg extends FileMessage {
	private static final long serialVersionUID = 60L;
	protected UserIdentity user;
	private List<UserIdentity> fileList;
	public requestFileLocationMsg(FileHandlerInfos fi, UserIdentity u){
		this.file = fi;
		this.user = u;
		try {
			this.setIpAdress(CommunicationManagerServer.findIPadress());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void treatment(){
		CommunicationManagerServer cms = CommunicationManagerServer.getInstance();
		DataServerToComm dataInterface = cms.getDataInterface();
		
		this.fileList = dataInterface.requestFileLocationServer(this.file);
		
		/** TODO : Créer le message de retour (file locationMsg) et le renvoyer au bon client! **/
		fileLocationMsg message = new fileLocationMsg(this.file,this.fileList);
		message.setIpAdress(this.getIpAdress());
		cms.sendMessage(message);
	}

	public boolean isToServ(){return true;}
}
