package com.lo23.communication.Messages.Files_Client;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.common.user.User;
import com.lo23.common.user.UserIdentity;
import com.lo23.communication.CommunicationManager.Client.CommunicationManagerClient;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.communication.Messages.FileMessage;
import com.lo23.communication.Messages.Files_Server.newFileInfoMsg;


public class uploadFileMsg extends FileMessage {
	private static final long serialVersionUID = 55L;
	protected UserIdentity user;
	private CommunicationManagerServer commManagerServer;
	private CommunicationManagerClient commManagerClient;
	
	public uploadFileMsg(FileHandlerInfos fi, UserIdentity u, CommunicationManagerServer cms, CommunicationManagerClient cmc){
		this.file = fi;
		this.user = u;
		this.commManagerServer=cms;
		this.commManagerClient=cmc;
	}
	
	public void treatment(){
		DataServerToComm dataInterface = this.commManagerServer.getDataInterface();
		/** On récupère et stocke l'adresse IP du serveur
		 */
		System.out.println("[COM]Stockage du fichier" + this.file.getHash());
		/** Envoi des données à data **/
		dataInterface.addNewFileToServer(this.file, this.user);
		/** Création du message pour le broadcast des informations**/
		newFileInfoMsg message = new newFileInfoMsg(this.file,this.user, this.commManagerClient);
		this.commManagerServer.broadcast(message);
	}

	public boolean isToServ(){return true;}
}
