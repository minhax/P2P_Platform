package com.lo23.data.server;

import com.lo23.common.interfaces.comm.CommToDataServer;
import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.common.user.UserStats;

/**
 * Classe qui va gérer la partie Data du serveur.
 */
public class DataManagerServer
{
    /**
     * Infos générales sur le serveur
     */
    private ServerInfos serverInfos;

    /**
     * Gestionnaire des connexions et des fichiers proposés
     */
    ConnectionsManager connections;

    /**
     * API venant de la partie communication
     */
    CommToDataServer commToDataApi;

    /**
     * API servant à la partie communication
     */
     DataServerToComm toCommApi;

    /**
     * Constructeur permettant d'instancier le gestionnaire de la partie Data du serveur
     * @param serverName Nom du serveur à instancier
     */
    public DataManagerServer(String serverName)
    {
        this.serverInfos = new ServerInfos(serverName);
        this.connections = new ConnectionsManager();
        this.toCommApi = new DataServerToCommAPI( this);
    }

    /**
     * Permet de spécifier l'API Communication à utiliser
     * @param api API proposée par communication
     */
    public void setCommToDataServer(CommToDataServer api)
    {
        this.commToDataApi = api;
    }

    public DataServerToComm getDataServerToCommApi ()
    {
        return this.toCommApi;
    }
}