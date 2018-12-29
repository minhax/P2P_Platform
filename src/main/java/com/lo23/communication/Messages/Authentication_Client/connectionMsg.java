package com.lo23.communication.Messages.Authentication_Client;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.user.UserIdentity;
import com.lo23.common.user.UserStats;
import com.lo23.communication.Messages.Authentication;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.communication.Messages.Users_Server.connectedUserMsg;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

/**
 * Message pour la connexion
 */
public class connectionMsg extends Authentication
{
	/**
	 * userIPAdress : l'adresse IP de l'utilisateur
	 */
	private String userIPAdress;
	/**
	 * serialVersionUID : l'identifiant unique de la classe
	 */
	private static final long serialVersionUID = 100521L;
	/**
	 * userPort : le port de l'utilisateur
	 */
	private int userPort; /** A initialiser !**/
	/**
	 * fileInfo : la liste de FileHandlerInfos
	 */
	private List<FileHandlerInfos> fileInfo;

	/**
	 * Constructeur
	 * @param us : l'utilisateur
	 * @param files : la liste des fichiers disponibles
	 */
	public connectionMsg(UserStats us, List<FileHandlerInfos> files )
	{
		this.userStats = us;
		this.fileInfo = files;
		try
		{
			this.userIPAdress = CommunicationManagerServer.findIPadress();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("Creation du message");
	}

	/**
	 * crée le message de connexion
	 */
	public void treatment()
	{
		/**
		 * Récuperation de communication manager coté serveur
		 */
		CommunicationManagerServer commManagerServer = CommunicationManagerServer.getInstance();
		/**
		 *  Récupération de l'interface de dataServer
		 */
		DataServerToComm dataInterface = commManagerServer.getDataInterface();
		/**
		 * Récuperation et stockage de l'adresse IP du serveur
		 */
		String ServerIpAdress = commManagerServer.getIP();
		
		System.out.println("Mon ip = " + this.userIPAdress);
		System.out.println("Addresse ip  du serveur = " + ServerIpAdress);
		/**
		 * Appel la méthode addNewConnectedUser pour lui transmettre son objet user Stats
		 * Appel la méthode addNewUserFiles pour lui transmettre ses filesInfos
		 */
		dataInterface.addNewConnectedUser(this.userStats);
		dataInterface.addNewUserFiles(this.fileInfo, this.userStats);
		/**
		 * Ajout de la paire IPPort-IPAdress
		 */
		commManagerServer.addEntryMap_IPPort(this.userIPAdress, this.getPort());
		/**
		 * Ajout de la paire IPUID-IPAdress
		 */
		commManagerServer.addEntryMap_IPUID(this.userIPAdress, this.userStats.getId());
		/**
		 * Récupération de la liste des fichiers des utilisateurs connectés
		 */
		HashMap<UserIdentity, Vector<FileHandlerInfos>> listeUsersandFiles = dataInterface.requestUserFiles(this.userStats);
		System.out.println("nb de fichier de la personne connectée : " + listeUsersandFiles.values());
		/**
		 * Création de message de connexion d'un utilisateur
		 */
		connectedUserMsg message = new connectedUserMsg(listeUsersandFiles);
		message.setPort(this.getPort());
		/**
		 * Faire le broadcast du message de connexion vers tous les utilisateurs
		 */
		commManagerServer.broadcast(message);
	}
	
	public boolean isToServ()
	{
		return true;
	}
}

