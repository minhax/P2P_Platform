package com.lo23.communication.network;

import java.io.*;
import java.net.*;

import com.lo23.communication.Messages.Message;
import com.lo23.data.Const;

import java.io.Serializable;

/**
 * Client for socket communication
 * Careful, the Central Server can act as a Client if he's sending messages to Peers
 */
public class Client implements Serializable{

    private Message msg; // Message qu'on transfert sur le reseau
    //private int portServ; // Le port du server central (hardcoder a 1026) /** In constant file
    private int serverPort; // Le port du client qui agit en tant que server (pour recevoir des messages)
    private String serverAdress; // L'adresse du client
    private int clientPort; // Le port sur lequel le client ouvre sa socket
    private boolean jobDone;
    public Client(Message msg,
                  String serverAdress)
    {
        //this.portServ = portServ;
        //this.addrServ = addrServ;
        this.msg = msg;
        this.serverAdress = serverAdress;
        this.serverPort = Const.SERVER_DEFAULT_PORT;
        this.jobDone = false;
        while(!this.jobDone)
        {
            this.start(this.msg, this.serverAdress,this.serverPort);
        }
    }
    
    /**
     * Create socket between a client (initialize connection) and a server
     * @param serveradress
     * @param clientPort
     * Increment involvedPort if occupied on server, to try another socket connection
     */
    public void start(Message msg ,String serverAdress, int serverPort)
        {
            Socket clientSocket;
            try {
                // Open socket connection on serverPort. If no one is listening, throws exception
                clientSocket = new Socket(serverAdress,serverPort);
                // Store ClientPort in the Client
                this.setClientPort(clientSocket.getLocalPort());
                // Exit loop
                this.setJobDone(true);
                /** Envoi du message **/
                ObjectOutputStream objOS = new ObjectOutputStream(clientSocket.getOutputStream());
                objOS.writeObject(msg); // client send data to the server
                
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }    finally {
                this.serverPort++;
            }
        }
        public void setJobDone(boolean value)
        {
            this.jobDone = value;
        }
        public void setClientPort(int clientPort){
            this.clientPort = clientPort;
        }
    }

    
   
