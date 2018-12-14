package com.lo23.communication.network;

import java.io.*;
import java.net.*;

import com.lo23.communication.Messages.Message;
import java.io.Serializable;

public class Client implements Serializable {

    private Message msg; // Message qu'on transfert sur le reseau
    private int portServ; // Le port du server central (hardcoder a 1026)
    private String addrServ; // L'adresse du server central
    private int peerPortServ; // Le port du client qui agit en tant que server (pour recevoir des messages)
    private String addrPeerServ; // L'adresse du client
    private int clientPortServ; // Le port sur lequel le client ouvre sa socket

    public Client(Message msg,
                  int portServ, String addrServ,
                  int peerPortServ, String addrPeerServ)
    {

        this.msg = msg;
        this.portServ = portServ;
        this.addrServ = addrServ;
        this.peerPortServ = peerPortServ;
        this.addrPeerServ = addrPeerServ;
        this.clientPortServ = 0;

        try{
            /*
                Si le message est pour le server central
             */
            if (msg.isToServ()){
                // socket Client to Server Central

                System.out.println("Connection au serveur...");

                Socket clientSocket = new Socket(addrServ, portServ);
                
                ObjectOutputStream objOS = new ObjectOutputStream(clientSocket.getOutputStream());
                objOS.flush();

                System.out.println("Creation du flux ");
                System.out.println();

                msg.setPort(clientSocket.getLocalPort());

                this.clientPortServ = clientSocket.getLocalPort();

                System.out.println("Envoi du message" + msg.toString());

                objOS.writeObject(msg); // client send data to the server

                clientSocket.close();
    
                // Client act as a server

                /*
                    Si le port est disponible, on peut ouvrir un ServerSocket sur ce Client,
                    sinon on ne fait rien
                 */
                if (available(msg.getPort()))
                {
                    PeerSendSocket objServer = new PeerSendSocket(this.clientPortServ);
                    objServer.start();
                }
            }

            /*
                Sinon le message est pour un client
             */
            else
                clientAsServer(addrPeerServ, peerPortServ, msg);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
        Permet de communiquer avec un Client en tant que Server
     */
    @SuppressWarnings({ "unused", "resource" })
    public static void clientAsServer(String clientAsServerAddr, int clientAsServerPort, Message msg)
    {
        try
        {
            Socket clientAsServerSocket = new Socket(clientAsServerAddr, clientAsServerPort); //msg.getPort());

            ObjectOutputStream objOS = new ObjectOutputStream(clientAsServerSocket.getOutputStream());
            objOS.flush();

            objOS.writeObject(msg);

            clientAsServerSocket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
        Methode qui verifie si un port est disponible ou non
     */
    public static boolean available(int port)
    {
        if (port < 1 || port > 65535) {
            return false;
        }
        ServerSocket servSockets = null;
        try {
            servSockets = new ServerSocket(port);
            servSockets.setReuseAddress(true);
            return true;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (servSockets != null) {
                try {
                    servSockets.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}