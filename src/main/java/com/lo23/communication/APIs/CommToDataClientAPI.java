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
import com.lo23.communication.network.Client;


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
        this.commManagerClient=commManager;
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
         * recupere le CommunicationManager cote Client
         * recupere l'addresse du serveur
         * cree un message de type updateUserInfoMsg pour la modification des infos de l'utilisateur user
         * cree un objet Client qui permet d'envoyer le message au serveur via socket
         */
        CommunicationManagerClient cmc = CommunicationManagerClient.getInstance();
        //A modifier plus tard ==> cms.getPort()
        int portServer = 1026;
        String addrServer = cmc.getAddressIpServer();
        updateUserInfoMsg msg = new updateUserInfoMsg(user);
        Client c = new Client(msg, portServer, addrServer, 0, null, 0);
    }


    @Override
    public void sendUserChanges(UserIdentity user){

    }

    @Override
    public void makeFilesUnavailableToServer(FileHandlerInfos file, User user){
        CommunicationManagerClient cmc= CommunicationManagerClient.getInstance();
        String ip = cmc.getAddressIpServer();
        makeFileUnavailableMsg message=new makeFileUnavailableMsg(file, user);
        Client client=new Client(message, 1026, ip, 0, null, 0);
    }

    @Override
    public void sendCommentedFile(Comment comment, FileHandler commentedFile){

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
        CommunicationManagerClient cmc= CommunicationManagerClient.getInstance();
        String myIPAdress = null;
        int portServ = 1026;
        try {
            myIPAdress = CommunicationManager.findIPadress();
        }catch (Exception e){
            e.printStackTrace();
        }
        logoutMsg message=new logoutMsg(user, myIPAdress);
        Client c = new Client(message, portServ, cmc.getAddressIpServer(), 0, null, 0);
        System.out.println("[COM] Deconnexion reussie");
    }

    /*@Override
    public void requestLogout(UserIdentity user){
        // A priori même rôle que requestLogoutToServer (à changer plus tard si besoin)

    }*/

    @Override
    public void requestUserConnexion(UserStats user, List<FileHandlerInfos> fi, String serverIP){
        CommunicationManagerClient cmc = CommunicationManagerClient.getInstance();
        cmc.setAddressIpServer(serverIP);
        int portServ = 1026;
        connectionMsg message = new connectionMsg(user, fi);
        System.out.println("Client cree");
        Client c = new Client(message, portServ, serverIP, 0, null, 0);
    }

    /*@Override
    public void connect(UserStats user, long IP){
        // A priori même rôle que requestUserConnexion (à changer plus tard si besoin)

    }*/


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
    public void requestUploadFile(FileHandlerInfos file, UserIdentity user){ //TODO Fix le parametre UserIdentity ou User?
        //TODO: rajouter exception
        CommunicationManagerClient cmc= CommunicationManagerClient.getInstance();
        String ip = cmc.getAddressIpServer();
        uploadFileMsg message=new uploadFileMsg(file, user);
        Client client=new Client(message, 1026, ip, 0, null, 0);

        //l'info arrive de l'appli client et doit ensuite être envoyée à CommServer
    }


    @Override
    public void sendNewFileSource(FileHandler file, UserIdentity user){
        //A priori la même chose que addNewFileSource ?

    }

    @Override
    public void uploadFile(FileHandlerInfos fi, UserIdentity user){
        CommunicationManagerClient cmc = CommunicationManagerClient.getInstance();
        int portServ = 1026;
        uploadFileMsg message = new uploadFileMsg(fi,user );
        System.out.println("Client cree");
        Client c = new Client(message, portServ, cmc.getAddressIpServer(), 0, null, 0);
    }

    @Override
    public void requestFileLoc(FileHandler file, UserIdentity user){


    }

}