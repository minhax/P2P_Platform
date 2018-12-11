package com.lo23.communication.APIs;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.comm.CommToDataServer;
import com.lo23.common.user.UserIdentity;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.communication.Messages.Files_Server.fileSourceMsg;
import com.lo23.communication.Messages.Users_Server.connectedUserMsg;
import com.lo23.communication.Messages.Users_Server.removeDisconnectedUserMsg;

import java.util.List;

public class CommToDataServerAPI implements CommToDataServer {


    private CommunicationManagerServer commManagerServer;

    /* Constructeur */
    private CommToDataServerAPI(CommunicationManagerServer cms)
    {
        this.commManagerServer= cms;
    }



    /*========= Implémentation des méthodes ============= */

    @Override
    public void removeDisconnectedUser(UserIdentity user, List<FileHandlerInfos> fileInfos){

    }

    @Override
    public void sendConnectedUserToAll(UserIdentity user, List<FileHandlerInfos> fileInfos){
    
    }

    @Override
    public void sendFileLoc(List<UserIdentity> sourceUsers){

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
    public void sendNewFileSource(FileHandlerInfos file, UserIdentity user){

    }

}
