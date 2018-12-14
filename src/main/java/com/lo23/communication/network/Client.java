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
    //private int peerPortServ; // Le port du client qui agit en tant que server (pour recevoir des messages)
    private String serverAdress; // L'adresse du client
    private int involvedPort; // Le port sur lequel le client ouvre sa socket
    private boolean jobDone;
    public Client(Message msg,
                  String serverAdress)
    {

        this.msg = msg;
        //this.portServ = portServ;
        //this.addrServ = addrServ;
        //this.peerPortServ = peerPortServ;
        this.serverAdress = serverAdress;
        this.involvedPort = Const.INVOLVED_PORT;
        this.jobDone = false;
        while(!this.jobDone)
        {
            this.start(this.serverAdress,this.involvedPort);
        }
    }
    
    /**
     * Create socket between a client (initialize connection) and a server
     * @param serveradress
     * @param involvedPort
     * Increment involvedPort if occupied on server, to try another socket connection
     */
    public void start(String serverAdress, int port)
        {
            Socket socket;
            try {
                socket = new Socket(serverAdress,port);
                this.setJobDone(true);
                /**TODO TREATEMENT**/
                socket.close();
            } catch (IOException e) {
        
                e.printStackTrace();
            }    finally {
                this.involvedPort++;
            }
        }
        public void setJobDone(boolean value)
        {
            this.jobDone = value;
        }
    }

    
   
