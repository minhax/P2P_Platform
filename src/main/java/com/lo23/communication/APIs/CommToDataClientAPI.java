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
import com.lo23.communication.Messages.Users_Client.updateUserInfoMsg;
import com.lo23.communication.Messages.Files_Client.makeFileUnavailableMsg;
import com.lo23.communication.Messages.Files_Client.uploadFileMsg;
import com.lo23.communication.Messages.Files_Client.addCommentMsg;
import com.lo23.communication.Messages.Files_Client.rateFileMsg;
import com.lo23.communication.network.Client.Client;
import com.lo23.data.Const;


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
    private static CommToDataClientAPI Instance;

    /* Accesseurs */
    public static CommToDataClientAPI getInstance()
    {
        if (Instance == null)
            Instance = new CommToDataClientAPI();
        return Instance;
    }

    public static CommunicationManagerClient getCommunicationManager()
    {
        return commManagerClient;
    }


    public void setCommunicationManager(CommunicationManagerClient commManager)
    {
        commManagerClient=commManager;
    }


    /*========= Implémentation des méthodes ============= */

    @Override
    public void sendFileChanges(FileHandler file){

    }

    @Override
    public void sendFileChanges(Rating rate, FileHandler file){

    }

    @Override
    public void sendUserChangesToServer(UserIdentity user) {
        
        /**
         * Récupération du Cmc et de l'adresseIP du server
         */
        
        CommunicationManagerClient cmc = CommunicationManagerClient.getInstance();
        
        /**
         * Création du message
         */
        
        updateUserInfoMsg msg = new updateUserInfoMsg(user);
        
        /**
         * @param msg : message a envoyer
         * @param ipServer : Adresse IP du serveur
         * @param Const.SERVER_DEFAULT_PORT : Port constant du serveur (1028)
         */
        
        Client c = new Client(msg, cmc.getAddressIpServer(), Const.SERVER_DEFAULT_PORT);
        c.start();
    }

        @Override
    public void makeFilesUnavailableToServer(FileHandler file, User user){
        CommunicationManagerClient cmc= CommunicationManagerClient.getInstance();
        
        makeFileUnavailableMsg message=new makeFileUnavailableMsg(file, user);
        
        Client c = new Client(message, cmc.getAddressIpServer(), Const.SERVER_DEFAULT_PORT);
        c.start();
    }

    @Override
    public void sendCommentedFile(Comment comment, FileHandlerInfos commentedFile, User user){
        CommunicationManagerClient cmc = CommunicationManagerClient.getInstance();
        
        addCommentMsg msg = new addCommentMsg(commentedFile, comment, user);
        
        Client c = new Client(msg, cmc.getAddressIpServer(), Const.SERVER_DEFAULT_PORT);
        c.start();
    }

    @Override
    public void sendRatedFile(Rating rating, FileHandlerInfos ratedFile, User user){
        CommunicationManagerClient cmc = CommunicationManagerClient.getInstance();
        
        rateFileMsg msg = new rateFileMsg( rating, ratedFile, user);
        
        Client c = new Client(msg, cmc.getAddressIpServer(), Const.SERVER_DEFAULT_PORT);
        c.start();
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
        CommunicationManagerClient cmc= CommunicationManagerClient.getInstance();
        String myIPAdress = null;
        
        try {
            myIPAdress = CommunicationManager.findIPadress();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("[API] Erreur dans la recherche d'adresse IP");
        }
        logoutMsg message=new logoutMsg(user, myIPAdress);
        Client c = new Client(message, cmc.getAddressIpServer(), Const.SERVER_DEFAULT_PORT);
        c.start();
        System.out.println("[COM] Deconnexion reussie");
    }

    /*@Override
    public void requestLogout(UserIdentity user){
        // A priori même rôle que requestLogoutToServer (à changer plus tard si besoin)

    }*/

    @Override
    public void requestUserConnexion(UserStats user, List<FileHandler> fi, String serverIP) {
        CommunicationManagerClient cmc = CommunicationManagerClient.getInstance();
        cmc.setAddressIpServer(serverIP);
        
        connectionMsg message = new connectionMsg(user, fi);
    
        System.out.println(" Demande de connexion pour l'utilisateur :" + user.getId() + " " + user.getLogin());
        Client c = new Client(message, serverIP, Const.SERVER_DEFAULT_PORT);
        c.start();
    }

    @Override
    public void requestAddSource(FileHandler file, UserIdentity user){
    
    }
    
    @Override
    public void sendFileChanges(User user, FileHandler file){

    }

    //A priori pas utile puisque l'ajout d'un fichier (méthode requestUploadFile) ajoute la source automatiquement
    /*@Override
    public void requestAddSource(FileHandlerInfos file, UserIdentity user){
        CommunicationManagerClient cms = CommunicationManagerClient.getInstance();
        Server server=new Server();
        addSourceMsg message=new addSourceMsg(file, user);
        server.sendMessage(message);
        //l'info arrive de l'appli client et doit ensuite être envoyée à CommServer
    }*/


    @Override
    public void requestUploadFile(FileHandler file, UserIdentity user){
        CommunicationManagerClient cmc= CommunicationManagerClient.getInstance();

        uploadFileMsg message=new uploadFileMsg(file, user);

        Client c = new Client(message, cmc.getAddressIpServer(), Const.SERVER_DEFAULT_PORT);
        c.start();
    }
    
    @Override
    public void uploadFile(FileHandlerInfos fi, UserIdentity user){
        CommunicationManagerClient cmc = CommunicationManagerClient.getInstance();
        
        uploadFileMsg message = new uploadFileMsg(fi,user );
        
        System.out.println("Client cree");
        Client c = new Client(message, cmc.getAddressIpServer(), Const.SERVER_DEFAULT_PORT);
        c.start();
    }

    @Override
    public void requestFileLoc(FileHandler file, UserIdentity user){


    }

}