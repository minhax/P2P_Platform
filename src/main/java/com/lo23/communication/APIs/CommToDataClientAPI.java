package com.lo23.communication.APIs;

import com.lo23.common.Comment;
import com.lo23.common.Rating;
import com.lo23.common.filehandler.*;
import com.lo23.common.interfaces.comm.CommToDataClient;
import com.lo23.common.interfaces.data.DataClientToComm;
import com.lo23.common.user.User;
import com.lo23.common.user.UserIdentity;
import com.lo23.common.user.UserStats;
import com.lo23.communication.CommunicationManager.Client.CommunicationManagerClient;
import com.lo23.communication.CommunicationManager.CommunicationManager;
import com.lo23.communication.CommunicationManager.Server.CommunicationManagerServer;
import com.lo23.communication.Messages.Authentication_Client.connectionMsg;
import com.lo23.communication.Messages.Authentication_Client.logoutMsg;
import com.lo23.communication.Messages.Files_Client.*;
import com.lo23.communication.Messages.Files_Server.sendFileMsg;
import com.lo23.communication.Messages.Users_Client.updateUserInfoMsg;
import com.lo23.communication.network.Client.Client;
import com.lo23.data.Const;

import java.util.List;

/**
 * API de Comm à Data Client
 */
public class CommToDataClientAPI implements CommToDataClient
{
    /**
     * commManagerClient: une instance de la classe CommunicationManagerClient
     */
    protected static CommunicationManagerClient commManagerClient ;

    /**
     * commManagerServer: une instance de la classe CommunicationManagerServer
     */
    protected static CommunicationManagerServer commManagerServer;

    /**
     * Constructeur de l'API
     */
    private CommToDataClientAPI()
    {
        commManagerClient=CommunicationManagerClient.getInstance();
        commManagerServer=CommunicationManagerServer.getInstance();
    }

    /**
     * Initialisation du singleton
     */
    private static CommToDataClientAPI Instance;

    /**
     * l'accesseur (getter) de Instance
     * @return objet de type CommToDataClientAPI
     */
    public static CommToDataClientAPI getInstance()
    {
        if (Instance == null)
            Instance = new CommToDataClientAPI();
        return Instance;
    }

    /**
     * l'accesseur (getter) de CommunicationManager
     * @return objet de type CommunicationManager
     */
    public static CommunicationManagerClient getCommunicationManager()
    {
        return commManagerClient;
    }

    /**
     * l'accesseur (setter) de CommunicationManager
     * @param commManager objet de type CommunicationManagerClient
     */
    public void setCommunicationManager(CommunicationManagerClient commManager)
    {
        this.commManagerClient=commManager;
    }

    /*================ Implémentation des méthodes =============== */


    /**
     * Envoie les modifications relatives à un utilisateur (communication avec le serveur)
     * @param user utilisateur concerné
     */
    @Override
    public void sendUserChangesToServer(UserIdentity user)
    {
        /**
         * Récupération du Communication Manager cote Client et de l'adresseIP du server
         */
        CommunicationManagerClient commManagerClient = CommunicationManagerClient.getInstance();
        
        /**
         * Création du message
         */
        updateUserInfoMsg message = new updateUserInfoMsg(user);
        
        /**
         * Création d'un client qui permet d'envoyer le message au serveur
         * @param message : message à envoyer
         * @param commManagerClient.getAddressIpServer()=ipServer : Adresse IP du serveur
         * @param Const.SERVER_DEFAULT_PORT : Port constant du serveur (1028)
         */
        Client c = new Client(message, commManagerClient.getAddressIpServer(), Const.SERVER_DEFAULT_PORT);
        c.start();
    }

    /**
     * Rend indisponible un fichier (communication avec le serveur)
     * @param file fichier que l'on rend indisponible
     * @param user utilisateur qui le rend indisponible
     */
    @Override
    public void makeFilesUnavailableToServer(FileHandlerInfos file, User user)
    {

        /**
         * Récupération du Communication Manager cote Client et de l'adresseIP du server
         */
        CommunicationManagerClient commManagerClient= CommunicationManagerClient.getInstance();
        /**
         * Création du message
         */
        makeFileUnavailableMsg message=new makeFileUnavailableMsg(file, user);
        /**
         * Création d'un client qui permet d'envoyer le message au serveur
         * @param message : message à envoyer
         * @param commManagerClient.getAddressIpServer()=ipServer : Adresse IP du serveur
         * @param Const.SERVER_DEFAULT_PORT : Port constant du serveur (1028)
         */
        Client c = new Client(message, commManagerClient.getAddressIpServer(), Const.SERVER_DEFAULT_PORT);
        c.start();
    }

    /**
     * Transmet l'ajout d'un nouveau commentaire sur un fichier
     * @param comment Commentaire
     * @param commentedFile Fichier commenté
     * @param user : l'utilisateur qui ajoute les commentaires
     */
    @Override
    public void sendCommentedFile(Comment comment, FileHandlerInfos commentedFile, User user)
    {
        /**
         * Récupération du Communication Manager cote Client et de l'adresseIP du server
         */
        CommunicationManagerClient commManagerClient = CommunicationManagerClient.getInstance();
        /**
         * Création du message
         */
        addCommentMsg message = new addCommentMsg(commentedFile, comment, user);
        /**
         * Création d'un client qui permet d'envoyer le message au serveur
         * @param message : message à envoyer
         * @param commManagerClient.getAddressIpServer()=ipServer : Adresse IP du serveur
         * @param Const.SERVER_DEFAULT_PORT : Port constant du serveur (1028)
         */
        Client c = new Client(message, commManagerClient.getAddressIpServer(), Const.SERVER_DEFAULT_PORT);
        c.start();
    }

    /**
     * Envoie une nouvelle note attribuée à un fichier
     * @param rating note à ajouter au fichier
     * @param ratedFile fichier noté
     * @param user Utilisateur qui a noté le fichier
     */
    @Override
    public void sendRatedFile(Rating rating, FileHandlerInfos ratedFile, User user)
    {
        /**
         * Récupération du Communication Manager cote Client et de l'adresseIP du server
         */
        CommunicationManagerClient commManagerClient = CommunicationManagerClient.getInstance();
        /**
         * Création du message
         */
        rateFileMsg message = new rateFileMsg( rating, ratedFile, user);
        /**
         * Création d'un client qui permet d'envoyer le message au serveur
         * @param message : message à envoyer
         * @param commManagerClient.getAddressIpServer()=ipServer : Adresse IP du serveur
         * @param Const.SERVER_DEFAULT_PORT : Port constant du serveur (1028)
         */
        Client c = new Client(message, commManagerClient.getAddressIpServer(), Const.SERVER_DEFAULT_PORT);
        c.start();
    }

    /**
     * Demande de déconnexion de l'utilisateur sur le serveur
     * @param  user utilisateur qui se déconnecte
     **/
    @Override
    public void requestLogoutToServer(UserStats user)
    {
        /**
         * Récupération du Communication Manager cote Client et de l'adresseIP du server
         */
        CommunicationManagerClient commManagerClient= CommunicationManagerClient.getInstance();
        String myIPAdress = null;
        try
        {
            myIPAdress = CommunicationManager.findIPadress();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("[API] Erreur dans la recherche d'adresse IP");
        }
        /**
         * Création du message
         */
        logoutMsg message=new logoutMsg(user, myIPAdress);
        /**
         * Création d'un client qui permet d'envoyer le message au serveur
         * @param message : message à envoyer
         * @param commManagerClient.getAddressIpServer()=ipServer : Adresse IP du serveur
         * @param Const.SERVER_DEFAULT_PORT : Port constant du serveur (1028)
         */
        Client c = new Client(message, commManagerClient.getAddressIpServer(), Const.SERVER_DEFAULT_PORT);
        c.start();
        System.out.println("[COM] Déconnexion réussie");
    }

    /**
     * Transmet la demande de connexion d'un utilisateur au serveur
     * @param user utilisateur qui veut se connecter
     * @param fi la liste des infos sur les fichiers
     * @param serverIP l'adresse IP de serveur
     */
    @Override
    public void requestUserConnexion(UserStats user, List<FileHandlerInfos> fi, String serverIP)
    {
        /**
         * Récupération du Communication Manager cote Client
         */
        CommunicationManagerClient commManagerClient = CommunicationManagerClient.getInstance();
        /**
         * Changer l'adresse IP de commManagerClient par l'adresse donnée en paramètre (serverIP)
         */
        commManagerClient.setAddressIpServer(serverIP);
        /**
         * Création du message
         */
        connectionMsg message = new connectionMsg(user, fi);
        /**
         * Affichage d'un message contenant l'Id de l'utilisateur ainsi que son login
         */
        System.out.println(" Demande de connexion pour l'utilisateur :" + user.getId() + " " + user.getLogin());
        /**
         * Création d'un client qui permet d'envoyer le message au serveur
         * @param message : message à envoyer
         * @param serverIP : Adresse IP du serveur
         * @param Const.SERVER_DEFAULT_PORT : Port constant du serveur (1028)
         */
        Client c = new Client(message, serverIP, Const.SERVER_DEFAULT_PORT);
        c.start();
    }


    /**
     * Transmet la demande de partage de fichier (nouveau partage) de l'application client à CommServeur
     * @param file fichier qui va être partagé
     * @param user utilisateur qui propose le fichier
     */
    @Override
    public void requestUploadFile(FileHandlerInfos file, UserIdentity user)
    {
        /**
         * Récupération du Communication Manager cote Client et de l'adresseIP du server
         */
        CommunicationManagerClient commManagerClient = CommunicationManagerClient.getInstance();
        /**
         * Création du message
         */
        uploadFileMsg message=new uploadFileMsg(file, user);
        /**
         * Création d'un client qui permet d'envoyer le message au serveur
         * @param message : message a envoyer
         * @param commManagerClient.getAddressIpServer()=ipServer : Adresse IP du serveur
         * @param Const.SERVER_DEFAULT_PORT : Port constant du serveur (1028)
         */
        Client c = new Client(message, commManagerClient.getAddressIpServer(), Const.SERVER_DEFAULT_PORT);
        c.start();
    }

    /**
     * Envoie les infos sur la nouvelle source d'un fichier à tous les clients du réseau
     * @param fi fichier partagé
     * @param user utilisateur qui devient source pour ce fichier
     */
    @Override
    public void uploadFile(FileHandlerInfos fi, UserIdentity user)
    {
        /**
         * Récupération du Communication Manager cote Client et de l'adresseIP du server
         */
        CommunicationManagerClient commManagerClient = CommunicationManagerClient.getInstance();
        /**
         * Creation d'un message
         */
        uploadFileMsg message = new uploadFileMsg(fi,user );
        System.out.println("Client crée");
        /**
         * Création d'un client qui permet d'envoyer le message au serveur
         * @param message : message à envoyer
         * @param commManagerClient.getAddressIpServer()=ipServer : Adresse IP du serveur
         * @param Const.SERVER_DEFAULT_PORT : Port constant du serveur (1028)
         */
        Client c = new Client(message, commManagerClient.getAddressIpServer(), Const.SERVER_DEFAULT_PORT);
        c.start();
    }

    @Override
    public void requestFileLoc(FileHandler file, UserIdentity user)
    {

    }

    /**
     * Demande une partie de fichier de l'utilisateur
     * @param userAsking : l'utilisateur qui possede le fichier voulu
     * @param userSource : l'utilisateur source qui veut une partie de fichier a telecharger
     * @param file : le fichier a telecherger
     * @param part : la partie de ficher dont on a besion
     */
    @Override
    public void  getFilePart(User userAsking, User userSource, FileHandlerInfos file, long part)
    {
        try
        {
            /**
             * Récupération du Communication Manager cote Client et de l'adresseIP du server
             */
            CommunicationManagerServer commManagerServer = CommunicationManagerServer.getInstance();
            /**
             * Création du message
             */
            getFileMsg message = new getFileMsg(userAsking, userSource, file, part);
            /**
             * Récupération de l'adresseIP de l'utilisateur Source(userSource)
             */
            String ipUserSource = commManagerServer.findUserIp(userSource.getId());
            /**
             * Création d'un client qui permet d'envoyer le message au serveur
             * @param message : message à envoyer
             * @param commManagerClient.getAddressIpServer()=ipServer : Adresse IP du serveur
             * @param Const.SERVER_DEFAULT_PORT : Port constant du serveur (1028)
             */
            Client c = new Client(message, ipUserSource, Const.CLIENT_DEFAULT_PORT);
            c.start();
        }
        /**
         * Manipulation de reprise sur Erreur
         */
        catch (Exception e)
        {
            e.printStackTrace(); // Pour les tests
            /**
             * Récupération du Communication Manager cote Client une autre fois
             */
            CommunicationManagerClient commManagerClient = CommunicationManagerClient.getInstance();
            /**
             * Récupération de l'interface de dataClient
             */
            DataClientToComm dataInterface = commManagerClient.getDataInterface();
            /**
             * Appel de la methode notifyAskForFilePartAgain pour manipuler l'erreur
             */
            dataInterface.notifyAskForFilePartAgain(userSource, file, part);
        }
    }
    /**
     * envoie la partie dont l'utilisateur a besoin
     * @param userAsking : l'utilisateur qui veut une partie de fichier a telecharger
     * @param userSource : l'utilisateur qui possede le fichier voulu
     * @param file : le fichier a telecharger
     * @param part : la partie voulue de fichier
     * @param content : le contenu voulu de fichier
     */
    @Override
    public void sendFilePart(User userAsking, User userSource, FileHandlerInfos file, long part, byte[] content)
    {
        //Depuis la source jusqu'à l'utilisateur demandeur
        try
        {
            /**
             * Creation du message
             */
            sendFileMsg message = new sendFileMsg(userAsking, userSource, file, part, content);
            /**
             * Récupération de l'adresseIP de l'utilisateur Source(userSource)
             */
            String ipUserAsking = this.commManagerServer.findUserIp(userSource.getId());
            /**
             * Création d'un client qui permet d'envoyer le message au serveur
             * @param message : message à envoyer
             * @param commManagerClient.getAddressIpServer()=ipServer : Adresse IP du serveur
             * @param Const.SERVER_DEFAULT_PORT : Port constant du serveur (1028)
             */
            Client client = new Client(message, ipUserAsking, Const.CLIENT_DEFAULT_PORT);
            client.start();
        }
        /**
         * Manipulation de reprise sur Erreur
         */
        catch (Exception e)
        {
            e.printStackTrace(); // Pour les test
            /**
             * Affichage d'un message indiquant que le client est déconnecté et il y a un arrêt de téléchargement
             */
            System.out.println("Client déconnecté, arrêt du téléchargement");

        }
    }

}