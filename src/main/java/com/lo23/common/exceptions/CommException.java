package com.lo23.common.exceptions;

import com.lo23.communication.CommunicationManager.Server.ClientInfo;

/**
 * Classe représentant une erreur spécifique levée par une méthode du groupe Data.
 */

public class CommException extends Exception{
	public CommException(String message, ClientInfo client)
	{
		super(message);
		System.out.println("Demande de suppression de l'adresse IP : " + client.getIPAdress()+ "Sur le port:" + client.getPort());
	}
}
