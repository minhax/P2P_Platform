package com.lo23.communication.APIs;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.comm.CommToDataServer;
import com.lo23.common.user.User;
import com.lo23.common.user.UserIdentity;
import com.lo23.communication.CommunicationManager.Client.CommunicationManagerClient;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.communication.Messages.Files_Server.sendFileMsg;
import com.lo23.communication.network.Client.Client;
import com.lo23.data.Const;

import java.util.List;
/**
 * API de Comm à Data Serveur
 */
public class CommToDataServerAPI implements CommToDataServer
{

    /**
     * commManagerServer : objet de type CommunicationManagerServer
     */
    protected static CommunicationManagerServer commManagerServer ;

    /* Constructeur */

    /**
     * Constructeur
     */
    private CommToDataServerAPI()
    {
        commManagerServer= CommunicationManagerServer.getInstance();
    }

    /**
     * Initialisation du singleton
     */
    private static CommToDataServerAPI Instance=new CommToDataServerAPI();

    /**
     * l'accesseur (getter) d'instance
     * @return objet de type CommToDataServerAPI
     */
    public static CommToDataServerAPI getInstance()
    {
        return Instance;
    }

    /**
     * l'accesseur (getter) de communicataionManager
     * @return objet de type CommunicationManagerServer
     */
    public static CommunicationManagerServer getCommunicationManager()
    {
        return commManagerServer;
    }

    /**
     * l'accesseur (setter) de communicataionManagerServer
     * @param commManager objet de type CommunicationManagerServer
     */
    public void setCommunicationManager(CommunicationManagerServer commManager)
    {
        this.commManagerServer=commManager;
    }


    /*========= Implémentation des méthodes ============= */
    
    @Override
    public void sendFileLoc(List<UserIdentity> sourceUsers)
    {

    }

    @Override
    public void sendUpdatedAccountToAll(UserIdentity user)
    {

        /**
         * cree un message de type updateAccountMsg
         * appelle de la methode treatment qui permet au serveur de diffuser les modifications apportées au user
         */
       // updatedAccountMsg msg = new updatedAccountMsg(user);
       // msg.treatment();
        //commManagerServer.broadcast(msg);
    }

    @Override
    public void sendNewFileSource(FileHandlerInfos file, UserIdentity user)
    {

    }

}
