package com.lo23.communication.network.Serveur;

import com.lo23.communication.Messages.Message;
import com.lo23.data.Const;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

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
	

	private Socket socket = null;
	
	public static boolean createNewServer = true;
    
    public ServerSock(){
        this.firstPort = Const.SERVER_DEFAULT_PORT;
    }
	public ServerSock(int port){
		this.firstPort = port;
	}
    
    /**
     * Ouverture d'un serveurSocket sur le port par default + le nombre d'utilisateurs connectes.
     */
    @Override
    public void run() {
	    //isListening = true;
     
	    try {
		    this.serverSocket = new ServerSocket(this.firstPort);
		    while (true) {
		    
				System.out.println("Creation d'un serveur socket sur le port " + this.serverSocket.getLocalPort());
			    System.out.println("En attente de connexion d'un client");
			    
			    socket = this.serverSocket.accept();
			    System.out.println("Socket ouverte sur le port: " + socket.getLocalPort());
			    //ReadMessage read = new ReadMessage(socket);
			    //read.start();
			
			    ObjectInputStream os = new ObjectInputStream(socket.getInputStream());
			    try {
				    System.out.println("Lecture du message");
				    Object msg = os.readObject();
				    Message msgCast = (Message) msg;
				    System.out.println("Traitement du message");
				    try {
					    msgCast.treatment();
					    System.out.println("Fin du traitement");
				    } catch (Exception e) {
					    e.printStackTrace();
				    }
				    os.close();
				    this.socket.close();
				    System.out.println("[Server] fermeture de la socket serveur");
			    } catch (Exception e) {
				    e.printStackTrace();
			    }
		    }
	    } catch (IOException e) {
		    e.printStackTrace();
	    }
        /**try{
            System.out.println("Initialisation de la connexion");
	        //AcceptConnexion connexion = new AcceptConnexion(this, this.serverSocket);
	        //connexion.start();
        }catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Echec de creation du nouveau serversocket");
        }**/
    }
}
