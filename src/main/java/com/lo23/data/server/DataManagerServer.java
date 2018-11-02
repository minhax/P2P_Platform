package com.lo23.data.server;

import com.lo23.common.interfaces.comm.CommToDataServer;
import com.lo23.common.interfaces.data.DataServerToComm;

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
    private ConnectionsManager connections;

    /**
     * API venant de la partie communication
     */
    private CommToDataServer commToDataApi;

    /**
     * API servant à la partie communication //TODO Est ce que c'est utile dans cette classe ?
     */
    private DataServerToComm toCommApi;

    /**
     * Constructeur permettant d'instancier le gestionnaire de la partie Data du serveur
     * @param serverName Nom du serveur à instancier
     */
    public DataManagerServer(String serverName){
        this.serverInfos = new ServerInfos(serverName);
        this.connections = new ConnectionsManager();
        // TODO Implémenter les interfaces pour pouvoir les instancier ici
    }

}
