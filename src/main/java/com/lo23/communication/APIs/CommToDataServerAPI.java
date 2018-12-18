package com.lo23.communication.APIs;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.comm.CommToDataServer;
import com.lo23.common.user.UserIdentity;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;

import java.util.List;

public class CommToDataServerAPI implements CommToDataServer {


    private CommunicationManagerServer commManagerServer;

    /* Constructeur */
    public CommToDataServerAPI(CommunicationManagerServer cms)
    {

        this.commManagerServer= cms;
    }



    /*========= Implémentation des méthodes ============= */
    
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
