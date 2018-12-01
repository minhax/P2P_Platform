package com.lo23.communication.CommunicationManager;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public abstract class CommunicationManager {
	
	protected String ip;
	
	public String getIp() {
		return this.ip;
	}
	
	/**
	 * Retourne et affiche l'adresse IP sur le serveur UTC de la machine appelante
	 *
	 * @param ... TODO FIX HARDCODE EQUALS
	 * @return String IPadress
	 **/
	public static String findIPadress() throws Exception {
		
		String ip = "192.168.0.19";
		return ip;
	}
}
