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
import com.lo23.communication.Messages.Files_Client.addSourceMsg;
import com.lo23.communication.Messages.Files_Client.makeFileUnavailableMsg;
import com.lo23.communication.Messages.Files_Client.uploadFileMsg;
import com.lo23.communication.Messages.Files_Server.fileSourceMsg;
import com.lo23.communication.Messages.Message;
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
    public void sendFileChanges(String champ, FileHandler file){

    }

    @Override
    public void sendUserChangesToServer(UserIdentity user){

    }

    @Override
    public void sendUserChanges(UserIdentity user){

    }

    @Override
    public void makeFilesUnavailableToServer(FileHandlerInfos file, User user){
        CommunicationManagerClient cmc= CommunicationManagerClient.getInstance();
        String ip = cmc.getAddressIpServer();
        makeFileUnavailableMsg message=new makeFileUnavailableMsg(file, user);
        Client client=new Client(message, 1026, ip);
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
        CommunicationManagerClient cmc= CommunicationManagerClient.getInstance();
        String myIPAdress = null;
        try{
            myIPAdress = CommunicationManager.findIPadress();
        }catch (Exception e){
            e.printStackTrace();
        }
        logoutMsg message=new logoutMsg(user, myIPAdress);
        Client c = new Client(message, portServ, cmc.getAddressIpServer());
    }

    /*@Override
    public void requestLogout(UserIdentity user){
        // A priori même rôle que requestLogoutToServer (à changer plus tard si besoin)

    }*/

    @Override
    public void requestUserConnexion(UserStats user, List<FileHandlerInfos> fi, String serverIP){
        CommunicationManagerClient cmc = CommunicationManagerClient.getInstance();
        cmc.setAddressIpServer(serverIP);
        int portServ = 0;
        connectionMsg message=new connectionMsg(user, fi);
        System.out.println("Client cree");
        Client c=new Client(message, portServ, serverIP);
    }

    /*@Override
    public void connect(UserStats user, long IP){
        // A priori même rôle que requestUserConnexion (à changer plus tard si besoin)

    }*/


    @Override
    public void sendFileChanges(Rating rate, FileHandler file){

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
    public void requestUploadFile(FileHandlerInfos file, User user){
        //TODO: rajouter exception
        CommunicationManagerClient cmc= CommunicationManagerClient.getInstance();
        String ip = cmc.getAddressIpServer();
        uploadFileMsg message=new uploadFileMsg(file, user);
        Client client=new Client(message, 1026, ip);

        //l'info arrive de l'appli client et doit ensuite être envoyée à CommServer
    }


    /*@Override
    public void sendNewFileSource(FileHandler file, UserIdentity user){
        //A priori la même chose que addNewFileSource ?

    }*/

    /*@Override
    public void uploadFile(FileHandler file, UserIdentity user){

        //Même chose que requestUploadFile ??

    }*/

    @Override
    public void requestFileLoc(FileHandler file, UserIdentity user){


    }

}