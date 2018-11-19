package com.lo23.communication.CommunicationManager;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public abstract class CommunicationManager {
	
	//protectedSocketHandlerSend socketHSend;
	//protected SocketHandlerReceive socketHReceive; //Vraiment utile?
	// protected SocketHandler socketH
     protected String ip;
	// envoi du message crée au préalable au socket handler
	public String getIp(){
		return this.ip;
	}
	/*
	public void sendMessageToSocket(Message M){
		// Le message est cree dans les methodes de l'interface puis transmis
		//this.socketHSend.socket.write(msg)
		//il faut retourner immediatement sans attendre le retour de la socket
	*/
	public void getIPadress() throws Exception {
		
		Enumeration<NetworkInterface> interfaces = null;
		try {
			interfaces = NetworkInterface.getNetworkInterfaces();
		}catch(SocketException e)
		{
			e.printStackTrace();
		}
		while (interfaces.hasMoreElements()) {
			NetworkInterface networkInterface = interfaces.nextElement();
			// drop inactive
			try {
				if (!networkInterface.isUp())
					continue;
			}catch (SocketException e)
			{
				e.printStackTrace();
			}
			// smth we can explore
			Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
			while(addresses.hasMoreElements()) {
				InetAddress addr = addresses.nextElement();
				System.out.println("Nom cannonique" + addr.getCanonicalHostName());
				System.out.println("CommunicationManager Addresse IP est : " + this.ip);
				//if(addr.isLoopbackAddress())
				//	continue;
				//else
				//	this.ip = addr.toString();
				//	return;
			}
		}
	}
	
	}