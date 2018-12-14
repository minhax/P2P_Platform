package com.lo23.communication.network;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.Serializable;


// Peer To Send Socket 

public class PeerSendSocket extends Thread implements Serializable {

    int portServerPeer;
    ServerSocket SendServerSocket;
    Socket SocketSend = null;

    public PeerSendSocket(int portServerPeer) {
        this.portServerPeer = portServerPeer;
    }

    public void run() {

        try {

            SendServerSocket = new ServerSocket(portServerPeer);

            SocketSend = SendServerSocket.accept();

            new PeerSendSocketHandler(SocketSend).start(); // creation de nouveau thread pour le client

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 
