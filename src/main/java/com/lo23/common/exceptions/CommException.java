package com.lo23.common.exceptions;

/**
 * Classe représentant une erreur spécifique levée par une méthode du groupe Data.
 */

public class CommException extends Exception{
	public CommException(String message, String ipadresse)
	{
		super(message);
		System.out.println("Demande de suppression de l'adresse IP : " + ipadresse);
	}
}
