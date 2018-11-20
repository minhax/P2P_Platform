package com.lo23.communication.APIs;

import com.lo23.common.Comment;
import com.lo23.common.Rating;
import com.lo23.common.filehandler.*;
import com.lo23.common.interfaces.comm.CommToDataClient;
import com.lo23.common.user.User;
import com.lo23.common.user.UserIdentity;
import com.lo23.common.user.UserStats;
import com.lo23.communication.CommunicationManager.Client.CommunicationManagerClient;
import com.lo23.communication.CommunicationManager.CommunicationManager;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.communication.Messages.Authentication_Client.connectionMsg;
import com.lo23.communication.Messages.Authentication_Client.logoutMsg;
<<<<<<< HEAD
import com.lo23.communication.Messages.Message;
import com.lo23.communication.Messages.Users_Client.updateUserInfoMsg;
=======
import com.lo23.communication.network.Client;
>>>>>>> communication2
import com.lo23.communication.network.Server;

import java.util.List;

public class CommToDataClientAPI implements CommToDataClient
{

    protected static CommunicationManagerClient commManagerClient ;

    /* Constructeur */
    private CommToDataClientAPI()
    {
        commManagerClient=CommunicationManagerClient.getInstance();
    }

    /* Initialisation du singleton*/
    private static CommToDataClientAPI Instance=new CommToDataClientAPI();

    /* Accesseurs */
    public static CommToDataClientAPI getInstance()
    {
        return Instance;
    }

    public static CommunicationManagerClient getCommunicationManager()
    {
        return commManagerClient;
    }


    public void setCommunicationManager(CommunicationManagerClient commManager)
    {
        this.commManagerClient=commManager;
    }


    /*========= Implémentation des méthodes ============= */


    @Override
    public void sendFileChanges(String champ, FileHandler file){

    }

    @Override
    public void sendUserChangesToServer(UserIdentity user)
    {
        /**
         * recupere le CommunicationManager cote Client
         * recupere l'addresse du serveur
         * cree un message de type updateUserInfoMsg pour la modification des infos de l'utilisateur user
         * cree un objet Client qui permet d'envoyer le message au serveur via socket
         */
        CommunicationManagerClient  cmc= CommunicationManagerClient.getInstance();
        //A modifier plus tard ==> cms.getPort()
        int portServer=1026;
        String addrServer=cmc.getAddressIpServer();
        updateUserInfoMsg msg = new updateUserInfoMsg(user);
        Client c= new Client(msg,portServer,addrServer);

    }

    @Override
    public void sendUserChanges(UserIdentity user){

    }

    @Override
    public void makeFilesUnavailableToServer(FileHandler file, User user){

    }

    @Override
    public void sendFileChanges(User user, FileHandler file){

    }

    @Override
    public void sendFilesChanges(Comment comment, FileHandler file){

    }
    /**
     * Demande de déconnexion de l'utilisateur sur le serveur
     *
     * @param UserStats user
     * Récupère instance cms
     * Récupère l'adresse IP de la machine, et le serveur sur lequel il est via getAdressIPServer()
     * Crée logout Msg + Client
     * @return void
     **/
    @Override
    public void requestLogoutToServer(UserStats user){
        CommunicationManagerClient cmc = CommunicationManagerClient.getInstance();
        String myIPAdress = null;
        int portServ = 0;
        try {
            myIPAdress = CommunicationManager.findIPadress();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        logoutMsg message = new logoutMsg(user,myIPAdress);
        Client c = new Client(message, portServ, cmc.getAddressIpServer());
    }
    /**
     * Demande de connexion de l'utilisateur sur le serveur
     *
     * @param UserStats user
     * @param List<FileHandlerInfos> fi
     * @param String serverIP
     *
     * Récupère instance cmc
     * Set adresseIPServer pour le cmc
     * Crée le message + client
     * @return void
     **/
    @Override
    public void requestUserConnexion(UserStats user, List<FileHandlerInfos> fi, String serverIP){
        CommunicationManagerClient cmc = CommunicationManagerClient.getInstance();
        cmc.setAddressIpServer(serverIP);
        int portServ = 0;
        connectionMsg message = new connectionMsg(user, fi);
	    System.out.println("Client cree");
	    Client c = new Client(message, portServ, serverIP);
    }


    /*@Override
    public void connect(UserStats user, long IP){
        // A priori même rôle que requestUserConnexion (à changer plus tard si besoin)

    }*/


    @Override
    public void sendFileChanges(Rating rate, FileHandler file){

    }

    @Override
    public void requestAddSource(FileHandler file, UserIdentity user){

    }


    @Override
    public void requestUploadFile(FileHandler file, UserIdentity user){

    }


    @Override
    public void sendNewFileSource(FileHandler file, UserIdentity user){

    }

    @Override
    public void uploadFile(FileHandler file, UserIdentity user){

    }

    @Override
    public void requestFileLoc(FileHandler file, UserIdentity user){

    }

}