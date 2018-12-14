package com.lo23.communication.network.Client;

import java.io.*;
import java.net.*;

import com.lo23.communication.Messages.Message;
import com.lo23.communication.network.SendMessage;
import com.lo23.data.Const;

import java.io.Serializable;

/**
 * Client for socket communication
 * Careful, the Central Server can act as a Client if he's sending messages to Peers
 */
public class Client implements Serializable{

    private Message msg; // Message qu'on transfert sur le reseau
    //private int portServ; // Le port du server central (hardcoder a 1026) /** In constant file
    private int destinationPort; // Le port destination
    private String destinationAdress; // L'adresse du client
    private boolean jobDone;
    public Client(Message msg,
                  String destinationAdress, int destinationPort)
    {
        //this.portServ = portServ;
        //this.addrServ = addrServ;
        this.msg = msg;
        this.destinationAdress = destinationAdress;
        this.destinationPort = destinationPort;
        this.jobDone = false;

        this.start(this.msg, this.destinationAdress,this.destinationPort);
    }
    
    /**
     * Create socket between a client (initialize connection) and a server
     * @param serveradress
     * @param clientPort
     * Increment involvedPort if occupied on server, to try another socket connection
     */
    public void start(Message msg ,String destinationAdress, int destinationPort)
    {
        /**
         *  Création de la socket
         *  Récupération du port local
         *  Envoi du message
         *  Fermeture de la socket
         * **/
        while (!this.jobDone) {
            try {
                /**
                 * SendMessageSocket implemente Thread
                 * Ouvre une socket, en cas d'echec, retourne une exception qui sera catch dans ce bloc
                 * quitte la boucle des que le message est envoye
                 */
                SendMessage sendMessageSocket = new SendMessage(destinationAdress, destinationPort, msg);
                sendMessageSocket.start();
                this.jobDone = true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                /**
                 * Morceau de code execute meme si une erreur survient
                 * incremente le port de destination pour pouvoir retenter une connexion socket
                 */
                this.destinationPort++;
            }
        }
    }
}

    
   
