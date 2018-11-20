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
 * @param
 * ... TODO FIX HARDCODE EQUALS
 * @return String IPadress
**/
	public static String findIPadress() throws Exception {
		
		Enumeration<NetworkInterface> interfaces = null;
		try {
			interfaces = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		while (interfaces.hasMoreElements()) {
			NetworkInterface networkInterface = interfaces.nextElement();
			// drop inactive
			try {
				if (!networkInterface.isUp())
					continue;
			} catch (SocketException e) {
				e.printStackTrace();
			}
			// smth we can explore
			Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
			while (addresses.hasMoreElements()) {
				InetAddress addr = addresses.nextElement();
				String ip = addr.getCanonicalHostName().toString();
				if (ip.regionMatches(0, "172", 0, 3)) {
					System.out.println("Ajout de l'adresse IP " + ip);
					return ip;
				} else
					continue;
			}
		}
		return null;
	}
}