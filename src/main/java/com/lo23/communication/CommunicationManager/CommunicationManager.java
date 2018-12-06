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
	 * @return String IPadress
	 **/
	public static String findIPadress() throws Exception {
		try {
			Enumeration<NetworkInterface> net = NetworkInterface.getNetworkInterfaces();
			while (net.hasMoreElements()) {
				NetworkInterface networkInterface = net.nextElement();
				Enumeration<InetAddress> add = networkInterface.getInetAddresses();
				while (add.hasMoreElements()) {
					InetAddress a = add.nextElement();
					if (!a.isLoopbackAddress() && !a.getHostAddress().contains(":")) {
//	                    if (Debug.debug)
//	                    {
//	                        Log.d(TAG, "getIPV4 : " + a.getHostAddress());
//	                    }
						return a.getHostAddress();
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return null;
	}
}
		/**Enumeration<NetworkInterface> interfaces = null;
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
			while (addresses.hasMoreElements())
			{
				InetAddress addr = addresses.nextElement();
				String hostname = addr.getHostName();
				String s = "utc";
				if (hostname.contains(s)) {
					String ip = addr.getHostAddress();
					System.out.println(ip);
					return ip;
				}
				else
					continue;
			} **/
			/*
			while (addresses.hasMoreElements()) {
				InetAddress addr = addresses.nextElement();
				String ip = addr.getCanonicalHostName().toString();
				if (ip.regionMatches(0, "172", 0, 3)) {
					System.out.println("Ajout de l'adresse IP " + ip);
					return ip;
				}
				 else
					continue;
			}
			*/
	/**	}
		return null;
	}**/
