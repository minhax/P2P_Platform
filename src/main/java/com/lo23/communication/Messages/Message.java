package com.lo23.communication.Messages;

import com.lo23.common.interfaces.data.DataClientToComm;
import java.io.Serializable;

/**
 * Classe abstraite pour le Message
 */
public abstract class Message implements Serializable
{
	/**
	 * port : port a utiliser
	 */
	private int port;

	/**
	 * treatment : méthode abstraite à utiliser dans les sous classes
	 */
	public abstract void treatment();

	public abstract boolean isToServ();

	/**
	 * l'accesseur (setter) de port
	 * @param port : le port à utiliser
	 */
	public void setPort(int port)
	{
		this.port = port;
	}

	/**
	 * l'accesseur (getter) de Port
	 * @return int
	 */
	public int getPort()
	{
		return this.port;
	};
}
