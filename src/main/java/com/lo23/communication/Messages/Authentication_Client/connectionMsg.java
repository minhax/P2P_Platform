package com.lo23.communication.Messages.Authentication_Client;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.user.UserStats;
import com.lo23.communication.Messages.Authentication;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.common.interfaces.data.DataServerToComm;
import java.util.List;

public class connectionMsg extends Authentication {
	private String myIp;
	private List<FileHandlerInfos> fileInfo;

	public connectionMsg(UserStats us){
		this.userStats = us;
	}

	public connectionMsg(UserStats us, List<FileHandlerInfos> files ){
		this.userStats = us;
		this.fileInfo = files;
		System.out.println("Creation du message");
	}
	
	public void treatment(){
		
		/**
		 * Recupere le communication manager cote serveur.
		 * Recupere son interface de dataServeur
		 * Appel la methode addNewConnectedUser pour lui transmettre son objet user Stats
		 * Appel la methode addNewUserFiles pour lui transmettre ses filesInfos
		 */
		
		CommunicationManagerServer cms = CommunicationManagerServer.getInstance();
		DataServerToComm dataInterface = cms.getDataInterface();
		/** appel des methodes de l'interface data
		 *
		
		 */
		System.out.println("Message treatment affichaged des infos inchallah" + this.getMyIp());
		dataInterface.addNewConnectedUser(this.userStats);
		dataInterface.addNewUserFiles(this.fileInfo, this.userStats);

		String IpAdress = null;
		try {
			 IpAdress = cms.getIp();
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		
		cms.addEntryInClientAndServerIPArray(this.myIp, IpAdress);
		
	}
	
	public List<FileHandlerInfos> getFileInfo() {
		return fileInfo;
	}
	
	
	public String getMyIp() {
		return myIp;
	}
}

