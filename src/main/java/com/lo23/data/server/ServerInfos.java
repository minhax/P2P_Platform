package com.lo23.data.server;

/**
 * Définit les informations d'un serveur
 */
public class ServerInfos
{
    /**
     * Nom du serveur
     */
    private String name;

    /**
     * Constructeur d'infos de serveur
     * @param name nom du serveur
     */
    public ServerInfos(String name)
    {
        this.name = name;
    }

    /**
     * Retourne le nom du serveur
     * @return nom du serveur
     */
    public String getName()
    {
        return name;
    }
}
