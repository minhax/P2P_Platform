package com.lo23.communication.network;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.Serializable;


// Peer To Send Socket 

public class PeerSendSocket extends Thread implements Serializable
{
    int peerPort;
    
    String  addrPeer = null;
    ServerSocket SendServerSocket;  
    Socket SocketSend = null;
  
    public PeerSendSocket() {}
   
    PeerSendSocket(int peerServerPort, String addrPeer)
    {
    	
    	this.peerPort = peerServerPort;
    	
    	this.addrPeer = addrPeer;    
    }
    
    public void run()
    {
    	
    	try
        {
    		
    		    SendServerSocket = new ServerSocket(peerPort);
    			
    		    SocketSend = SendServerSocket.accept();
    			
    			new PeerSendSocketHandler(SocketSend, addrPeer).start();
        } 
    	
    	catch (Exception e)
        {
    		
    	}
    }    
} 
