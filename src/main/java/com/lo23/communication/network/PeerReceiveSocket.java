package com.lo23.communication.network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.io.Serializable;

// Peer to Receive 


public class PeerReceiveSocket implements Serializable{
	
	@SuppressWarnings({"unused"})
	public PeerReceiveSocket()
	{  
		Socket socket;
		String addrPeer = null;
		int peerServerPort = 0;
   
		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // just for test Input for the client
  			
			System.out.println("Client parameters");
			
			System.out.println("addr of the peer as a server :");
			addrPeer = br.readLine(); // Sera recupere par des methodes.
			
			System.out.println("port of the peer as a server :");
			peerServerPort = Integer.parseInt(br.readLine()); // Idem
  
			PeerSendSocket objSendSocket = new PeerSendSocket(peerServerPort, addrPeer);
			objSendSocket.start();
				
			// Connexion avec le client qui nous envoie les donnees
			
			InfoPeer infoPeer = new InfoPeer();
			String addrReceive = infoPeer.getAddrPeer();
			int portPeer = infoPeer.getPortPeer();
			
			socket = new Socket(addrReceive, portPeer);
			
			System.out.println("Connection with the client succesful");
			
			ObjectInputStream objIS = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream objOS = new ObjectOutputStream(socket.getOutputStream()); 
  
			// Inclure methode pour recuperer le/les fichiers sur le client qui doit envoyer les fichiers;
					
			System.out.println("Waiting for ServerClass");
			
			String clientAsServerAddr = "localhost";
			int clientAsServerPort = 100;
				
			// Méthodes pour récupérer les fichiers sur le client ou on download le fichier
			clientAsServer(clientAsServerAddr, clientAsServerPort);  
		}
		catch(Exception e)
		{
			System.out.println("Error to connect peers");
		}
	}
	@SuppressWarnings({ "unused", "resource" })
	public static void clientAsServer(String clientAsServerAddr, int clientAsServerPort) throws ClassNotFoundException
	{   
		try
        {
				
				Socket clientAsServersocket = new Socket(clientAsServerAddr, clientAsServerPort);
				
				ObjectOutputStream clientObjectOS = new ObjectOutputStream(clientAsServersocket.getOutputStream());
				ObjectInputStream clientObjectIS = new ObjectInputStream(clientAsServersocket.getInputStream());
				
				// Methode pour confirmer la récupération du fichier sur le client
				
		} 
		catch (Exception e) {
		}
	}
}
