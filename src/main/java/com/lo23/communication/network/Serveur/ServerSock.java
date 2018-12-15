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
	private static int usersConnected = 0;
	/**
	 * Port de depart du serveur
	 */
	private int firstPort;
    
    /**
     * Serveur de socket
     */
    private ServerSocket serverSocket;
    
    public ServerSock(){
        this.firstPort = Const.SERVER_DEFAULT_PORT;
    }
    
    /**
     * Ouverture d'un serveurSocket sur le port par default
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
		        AcceptConnexion connexion = new AcceptConnexion(this.serverSocket);
		        connexion.start();
	        /** On arrive ici, quand la connexion a réussi avec un autre, ce qui implique qu'il faut ouvrir un autre server Socket sur un autre port pour continuer a ecouter
	         *
	         */
	            this.usersConnected++;
            }catch(Exception e)
            {
            	e.printStackTrace();
	            System.out.println("Echec du lancement de la connexion et recuperation de message serveur");
            }
        }
}
