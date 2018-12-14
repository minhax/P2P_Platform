package com.lo23.communication.network.Serveur;

import com.lo23.data.Const;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import java.io.IOException;
import java.net.*;

/**
 * Server class for
 */
public class ServerSock extends  Thread{
    
    private int currentPort; // Server port
    
    /**
     * Serveur de socket
     */
    private ServerSocket serverSocket;
    /**
     * booléan pour l'écoute du serversocket
     */
    private Boolean isListening;
    /**
     * Méthode de fermeture du serverSocket
     */
    public void closeServerSocket(){
        isListening = false;
        try {
            serverSocket.close();
        }catch (IOException err){
            err.printStackTrace();
        }
    }
    
    /**
     * Constructeur de la classe
     * @param manager Référence vers la classe principale du module
     */
    public ServerSock(){
        this.currentPort = Const.SERVER_DEFAULT_PORT;
    }
    
    /**
     * Récéption d'un message sur le réseau
     */
    @Override
    public void run() {
        try {
            this.serverSocket = new ServerSocket(this.currentPort);
            isListening = true;
            while (isListening) {
                Socket socket = this.serverSocket.accept();
                /** TODO COde pour lecture des messages avec un nouveau thread
                ReadMessage readMessageService = new ReadMessage(this.manager, socket);
                readMessageService.start();
                 **/
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }
    
}
