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

    private CommunicationManagerClient commManagerClient ;
    private CommunicationManagerServer commManagerServer ;


    /* Constructeur */
    public CommToDataClientAPI(CommunicationManagerClient cmc, CommunicationManagerServer cms)
    {

        this.commManagerClient=cmc;
        this.commManagerServer=cms;
    }



    /* Accesseurs */

    public CommunicationManagerClient getCommunicationManager()
    {

        return this.commManagerClient;
    }


    public void setCommunicationManagerClient(CommunicationManagerClient commManager)
    {

        this.commManagerClient=commManager;
    }

    public void setCommunicationManagerServer(CommunicationManagerServer commManager){
        this.commManagerServer=commManager;
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
<<<<<<< HEAD
        
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
=======
        //A modifier plus tard ==> cms.getPort()
        int portServer = 1026;
        String addrServer = this.commManagerClient.getAddressIpServer();
        updateUserInfoMsg msg = new updateUserInfoMsg(user, this.commManagerServer, this.commManagerClient);
        Client c = new Client(msg, portServer, addrServer, 0, null);
>>>>>>> b3c8c952d5edd679f04bf216db7d24dd97e40c7c
    }

        @Override
    public void makeFilesUnavailableToServer(FileHandlerInfos file, User user){
<<<<<<< HEAD
        CommunicationManagerClient cmc= CommunicationManagerClient.getInstance();
        
        makeFileUnavailableMsg message=new makeFileUnavailableMsg(file, user);
        
        Client c = new Client(message, cmc.getAddressIpServer(), Const.SERVER_DEFAULT_PORT);
        c.start();
=======
        String ip = this.commManagerClient.getAddressIpServer();
        makeFileUnavailableMsg message=new makeFileUnavailableMsg(file, user, this.commManagerServer, this.commManagerClient);
        Client client=new Client(message, 1026, ip, 0, null);
>>>>>>> b3c8c952d5edd679f04bf216db7d24dd97e40c7c
    }

    @Override
    public void sendCommentedFile(Comment comment, FileHandlerInfos commentedFile, User user){
<<<<<<< HEAD
        CommunicationManagerClient cmc = CommunicationManagerClient.getInstance();
        
        addCommentMsg msg = new addCommentMsg(commentedFile, comment, user);
        
        Client c = new Client(msg, cmc.getAddressIpServer(), Const.SERVER_DEFAULT_PORT);
        c.start();
=======
        String ip = this.commManagerClient.getAddressIpServer();
        addCommentMsg msg = new addCommentMsg(commentedFile, comment, user, this.commManagerServer, this.commManagerClient);
        Client c = new Client(msg, 1026, ip, 0, null);
>>>>>>> b3c8c952d5edd679f04bf216db7d24dd97e40c7c
    }

    @Override
    public void sendRatedFile(Rating rating, FileHandlerInfos ratedFile, User user){
<<<<<<< HEAD
        CommunicationManagerClient cmc = CommunicationManagerClient.getInstance();
        
        rateFileMsg msg = new rateFileMsg( rating, ratedFile, user);
        
        Client c = new Client(msg, cmc.getAddressIpServer(), Const.SERVER_DEFAULT_PORT);
        c.start();
=======
        String ip = this.commManagerClient.getAddressIpServer();
        rateFileMsg msg = new rateFileMsg(ratedFile, rating, user, this.commManagerServer, this.commManagerClient);
        Client c = new Client(msg, 1026, ip, 0, null);
>>>>>>> b3c8c952d5edd679f04bf216db7d24dd97e40c7c
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
    public void requestLogoutToServer(UserStats user, List<FileHandlerInfos> userFiles){
        String myIPAdress = null;
        
        try {
            myIPAdress = this.commManagerClient.findIPadress();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("[API] Erreur dans la recherche d'adresse IP");
        }
<<<<<<< HEAD
        logoutMsg message=new logoutMsg(user, myIPAdress);
        Client c = new Client(message, cmc.getAddressIpServer(), Const.SERVER_DEFAULT_PORT);
        c.start();
=======
        logoutMsg message=new logoutMsg(user, myIPAdress, userFiles, this.commManagerServer, this.commManagerClient);
        Client c = new Client(message, portServ, this.commManagerClient.getAddressIpServer(), 0, null);
>>>>>>> b3c8c952d5edd679f04bf216db7d24dd97e40c7c
        System.out.println("[COM] Deconnexion reussie");
    }

    /*@Override
    public void requestLogout(UserIdentity user){
        // A priori même rôle que requestLogoutToServer (à changer plus tard si besoin)

    }*/

    @Override
    public void requestUserConnexion(UserStats user, List<FileHandlerInfos> fi, String serverIP){
<<<<<<< HEAD
        CommunicationManagerClient cmc = CommunicationManagerClient.getInstance();
        cmc.setAddressIpServer(serverIP);
        
        connectionMsg message = new connectionMsg(user, fi);
    
        System.out.println(" Demande de connexion pour l'utilisateur :" + user.getId() + " " + user.getLogin());
        Client c = new Client(message, serverIP, Const.SERVER_DEFAULT_PORT);
        c.start();
=======
        this.commManagerClient.setAddressIpServer(serverIP);
        int portServ = 1026;
        connectionMsg message = new connectionMsg(user, fi, this.commManagerServer, this.commManagerClient);
        System.out.println("Client cree");
        Client c = new Client(message, portServ, serverIP, 0, null);
>>>>>>> b3c8c952d5edd679f04bf216db7d24dd97e40c7c
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
<<<<<<< HEAD
    public void requestUploadFile(FileHandlerInfos file, UserIdentity user){
        CommunicationManagerClient cmc= CommunicationManagerClient.getInstance();

        uploadFileMsg message=new uploadFileMsg(file, user);

        Client c = new Client(message, cmc.getAddressIpServer(), Const.SERVER_DEFAULT_PORT);
        c.start();
=======
    public void requestUploadFile(FileHandlerInfos file, UserIdentity user){ //TODO Fix le parametre UserIdentity ou User?
        //TODO: rajouter exception
        String ip = this.commManagerClient.getAddressIpServer();
        uploadFileMsg message=new uploadFileMsg(file, user, this.commManagerServer, this.commManagerClient);
        Client client=new Client(message, 1026, ip, 0, null);
>>>>>>> b3c8c952d5edd679f04bf216db7d24dd97e40c7c
    }

    @Override
    public void uploadFile(FileHandlerInfos fi, UserIdentity user){
<<<<<<< HEAD
        CommunicationManagerClient cmc = CommunicationManagerClient.getInstance();
        
        uploadFileMsg message = new uploadFileMsg(fi,user );
        
        System.out.println("Client cree");
        Client c = new Client(message, cmc.getAddressIpServer(), Const.SERVER_DEFAULT_PORT);
        c.start();
=======
        int portServ = 1026;
        uploadFileMsg message = new uploadFileMsg(fi,user, this.commManagerServer, this.commManagerClient);
        System.out.println("Client cree");
        Client c = new Client(message, portServ, this.commManagerClient.getAddressIpServer(), 0, null);
>>>>>>> b3c8c952d5edd679f04bf216db7d24dd97e40c7c
    }

    @Override
    public void requestFileLoc(FileHandler file, UserIdentity user){


    }

}