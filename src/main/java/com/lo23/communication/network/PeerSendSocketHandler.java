package com.lo23.communication.network;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

// Peer To Send Handler Socket

public class PeerSendSocketHandler extends Thread
{
    Socket SendThreadSocket;
    String addrPeer;
    
    public PeerSendSocketHandler(Socket SendThreadSocket, String addrPeer)
    {
        this.SendThreadSocket = SendThreadSocket;       
        this.addrPeer = addrPeer;
    }
    
    @SuppressWarnings("unused")
	public void run()
    {
        try
        {
            ObjectOutputStream objOS = new ObjectOutputStream(SendThreadSocket.getOutputStream());
            ObjectInputStream objIS = new ObjectInputStream(SendThreadSocket.getInputStream());
            
            while(true)
            {
                // Mettre les methodes de traitements pour recuperer
            }
        }
        catch(Exception e)
        {            
        }
    }
}
