package com.lo23.communication.APIs;

import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.comm.CommToDataServer;
import com.lo23.common.user.User;
import com.lo23.common.user.UserIdentity;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.communication.Messages.Users_Server.connectedUserMsg;
import com.lo23.communication.Messages.Users_Server.removeDisconnectedUserMsg;

import java.util.List;

public class CommToDataServerAPI implements CommToDataServer {


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

    @Override
    public void removeDisconnectedUser(UserIdentity user, List<FileHandlerInfos> fileInfos){
        removeDisconnectedUserMsg message=new removeDisconnectedUserMsg(user, fileInfos);
        commManagerServer.broadcast(message);

    }

    @Override
    public void sendConnectedUserToAll(UserIdentity user, List<FileHandlerInfos> fileInfos){
        connectedUserMsg message=new connectedUserMsg(user, fileInfos);
        commManagerServer.broadcast(message);

    }

    @Override
    public void sendFileLoc(List<UserIdentity> sourceUsers){

    }

    @Override
    public void sendUpdatedAccountToAll(UserIdentity user){

    }

    @Override
    public void sendNewFileSource(FileHandler file, UserIdentity user){

    }

}
