package com.lo23.communication.APIs;

import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.user.User;
import com.lo23.common.user.UserIdentity;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;

import java.util.List;

public class CommToDataServerAPI {


    protected static CommunicationManagerServer commManagerServer ;

    /* Constructeur */
    private CommToDataServerAPI()
    {
        commManagerServer= CommunicationManagerServer.getInstance();
    }

    /* Initialisation du singleton*/
    private static CommToDataServerAPI Instance=new CommToDataServerAPI();

    /* Accesseurs */
    public static CommToDataServerAPI getInstance()
    {
        return Instance;
    }

    public static CommunicationManagerServer getCommunicationManager()
    {
        return commManagerServer;
    }


    public void setCommunicationManager(CommunicationManagerServer commManager)
    {
        this.commManagerServer=commManager;
    }


    /*========= Implémentation des méthodes ============= */

    public void removeDisconnectedUser(User user, List<FileHandler> files){
        //créer message de déconnexion
        //appeler méthode de broadcast du commManager

    }

    public void sendFileLoc(List<UserIdentity> sourceUsers){

    }


    public void sendUpdatedAccountToAll(UserIdentity user){

    }


    public void sendNewFileSource(FileHandler file, UserIdentity user){

    }

}
