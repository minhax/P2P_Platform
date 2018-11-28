package com.lo23.common.exceptions;

/**
 * Classe représentant une erreur spécifique levée par une méthode du groupe Data.
 */
public class DataException extends Exception
{
    /**
     * Constructeur
     * @param message Message d'erreur
     */
    public DataException (String message)
    {
        super(message);
    }
}
