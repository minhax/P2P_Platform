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
import com.lo23.communication.Messages.Authentication;
import com.lo23.communication.Messages.Authentication_Client.connectionMsg;
import com.lo23.communication.Messages.Authentication_Client.logoutMsg;
import com.lo23.communication.Messages.Message;
import com.lo23.communication.network.Server;

import java.net.InetAddress;
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
    public void makeFilesUnavailableToServer(FileHandler file, User user){

    }

    @Override
    public void sendFileChanges(User user, FileHandler file){

    }

    @Override
    public void sendFilesChanges(Comment comment, FileHandler file){

    }

    @Override
    public void requestLogoutToServer(UserStats user){

        String ip = commManagerClient.getIp(); //TODO: Rajouter une exception plus tard
        Server server = new Server();
        logoutMsg message = new logoutMsg(user,ip);
        server.sendMessage(message);
    }

    /*@Override
    public void requestLogout(UserIdentity user){
        // A priori même rôle que requestLogoutToServer (à changer plus tard si besoin)

    }*/

    @Override
    public void requestUserConnexion(UserStats user, List<FileHandlerInfos> fi, String serverIP){
        CommunicationManagerClient cms = CommunicationManagerClient.getInstance();
        String ip = cms.getIp();
        System.out.println(ip);
        Server server = new Server();
        System.out.println("Serveur initialisee");
        connectionMsg message = new connectionMsg(user, fi,serverIP, ip);
        server.sendMessage(message);
    }


    /*@Override
    public void connect(UserStats user, long IP){
        // A priori même rôle que requestUserConnexion (à changer plus tard si besoin)

    }*/


    @Override
    public void sendFileChanges(Rating rate, FileHandler file){
        //System
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