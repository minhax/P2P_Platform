package com.lo23.communication.Messages.Authentication_Client;

import com.lo23.common.exceptions.CommException;
import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.user.UserIdentity;
import com.lo23.common.user.UserStats;
<<<<<<< HEAD
=======
import com.lo23.communication.CommunicationManager.Client.CommunicationManagerClient;
import com.lo23.communication.CommunicationManager.Server.ClientInfo;
>>>>>>> b3c8c952d5edd679f04bf216db7d24dd97e40c7c
import com.lo23.communication.Messages.Authentication;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.communication.Messages.Users_Server.*;

import java.util.List;

public class logoutMsg extends Authentication {
	
	private static final long serialVersionUID = 10002L;
	protected String UserIPAdress;
<<<<<<< HEAD
	
	public logoutMsg(UserStats us, String ipAdress){
=======
	private int UserPort;
	private CommunicationManagerServer commManagerServer;
	private CommunicationManagerClient commManagerClient;
	private List<FileHandlerInfos> userFiles;

	public logoutMsg(UserStats us, String ipAdress, List<FileHandlerInfos> fi, CommunicationManagerServer cms, CommunicationManagerClient cmc){
>>>>>>> b3c8c952d5edd679f04bf216db7d24dd97e40c7c
		this.userStats= us;
		this.UserIPAdress =ipAdress;
		this.userFiles=fi;
		this.commManagerServer=cms;
		this.commManagerClient=cmc;
	}
	/**
	 * Recupere le cms
	 * Recupere son objet dataServeur
	 * Utilise la methode removeDisconnectedUser pour transmettre la demande de deconnexion
	 * Supprime la paire IPUser - IPServer dans la table
	 */
	public void treatment(){
		DataServerToComm dataInterface = this.commManagerServer.getDataInterface();
		/** On récupère et stocke l'adresse IP du serveur
		 */
		String ServerIpAdress = commManagerServer.getIP();

		try {
			this.commManagerServer.removeUserFromMap(this.UserIPAdress);
		}catch(CommException e){
			System.out.println("Message: \t");
			System.out.println(e.getMessage());
			System.out.println("\t printStackTrace: \t");
			e.printStackTrace();
		}

		dataInterface.removeDisconnectedUser(this.userStats);

		/**Faire le broadcast du message de connection vers tout les utilisateurs connectés**/
		removeDisconnectedUserMsg message = new removeDisconnectedUserMsg((UserIdentity)this.userStats, this.userFiles, commManagerClient);
		message.setPort(this.getPort());
<<<<<<< HEAD
		cms.broadcast(message);
=======
		this.commManagerServer.broadcast(message);
>>>>>>> b3c8c952d5edd679f04bf216db7d24dd97e40c7c
	}

	public boolean isToServ(){return true;}
}
