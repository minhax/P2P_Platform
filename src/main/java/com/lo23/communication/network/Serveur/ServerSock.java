package com.lo23.communication.network.Serveur;

import com.lo23.data.Const;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Class serveur
 * Cree un Serveur Socket sur le port 8000 + nombre d'utilisateurs connectes.
 * Lance un thread pour recuperer les messages entrants.
 */
public class ServerSock extends  Thread{
	
	/**
	 * Nombre d'utilisateurs connectes
	 */
	public static int usersConnected = 0;
	/**
	 * Port de depart du serveur
	 */
	private int firstPort;
    
    /**
     * Serveur de socket
     */
    private ServerSocket serverSocket;
	
	/**
	 * Indique si un utilisateur s'est connecté
	 *
	 */
	public static boolean createNewServer = true;
    
    public ServerSock(){
        this.firstPort = Const.SERVER_DEFAULT_PORT;
    }
    
    /**
     * Ouverture d'un serveurSocket sur le port par default + le nombre d'utilisateurs connectes.
     */
    @Override
    public void run() {
	    //isListening = true;
        try {
	        this.serverSocket = new ServerSocket(this.firstPort + this.usersConnected);
	        System.out.println("Creation d'un serveur socket sur le port " + this.serverSocket.getLocalPort());
        }catch (Exception e)
        {
        	e.printStackTrace();
        	int port = this.firstPort + this.usersConnected;
	        System.out.println("Erreur de creation de Server Socket sur le port = " + port);
        }
        try{
            System.out.println("Initialisation de la connexion");
	        AcceptConnexion connexion = new AcceptConnexion(this, this.serverSocket);
	        connexion.start();
        }catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Echec de creation du nouveau serversocket");
        }
    }
        public static void setCreateNewServer(boolean value)
        {
        	 createNewServer = value;

        }
        public boolean getCrateNewServer(){
    	return this.createNewServer;
        }
}
