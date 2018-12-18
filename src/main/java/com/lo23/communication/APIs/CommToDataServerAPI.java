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

    @Override
    public void sendFilePart(User userAsking, User userSource, FileHandlerInfos file, long part, byte[] content){
        //Depuis la source jusqu'à l'utilisateur demandeur
        try {
            sendFileMsg message = new sendFileMsg(userAsking, userSource, file, part, content);
            String ipUserAsking = this.commManagerServer.findUserIp(userSource.getId());
            Client client = new Client(message, ipUserAsking, Const.CLIENT_DEFAULT_PORT);
            client.start();
        }
        catch (Exception e){
            e.printStackTrace(); // Pour les test
            System.out.println("Client deconnecte, arret du telechargement");

        }
    }

}
