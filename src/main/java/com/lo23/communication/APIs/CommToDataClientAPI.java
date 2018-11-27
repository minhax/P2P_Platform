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
import com.lo23.communication.Messages.Authentication_Client.connectionMsg;
import com.lo23.communication.Messages.Authentication_Client.logoutMsg;
import com.lo23.communication.network.Client;
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
    public void sendFileChanges(FileHandler file){

    }

    @Override
    public void sendUserChangesToServer(UserIdentity user){

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

    @Override
    public void sendRatedFile(Rating rating, FileHandler ratedFile){

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
        int portServ = 1026;
        connectionMsg message = new connectionMsg(user, fi);
	    System.out.println("Client cree");
	    Client c = new Client(message, portServ, serverIP);
    }


    /*@Override
    public void connect(UserStats user, long IP){
        // A priori même rôle que requestUserConnexion (à changer plus tard si besoin)

    }*/


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