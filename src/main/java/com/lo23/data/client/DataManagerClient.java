package com.lo23.data.client;

import com.lo23.common.Comment;
import com.lo23.common.exceptions.DataException;
import com.lo23.common.Rating;
import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.interfaces.comm.CommToDataClient;
import com.lo23.common.interfaces.data.DataClientToComm;
import com.lo23.common.interfaces.data.DataClientToIhm;
import com.lo23.common.interfaces.ihm.IhmToDataClient;
import com.lo23.common.user.*;
import com.lo23.communication.APIs.CommToDataClientAPI;
import com.lo23.ihm.APIs.IhmToDataClientAPI;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static com.lo23.data.Const.FILEPATH_ACCOUNTS;

/**
 * Manager Data client
 */
public class DataManagerClient
{
    /**
     * API de DataClient pour IHM
     */
    private DataClientToIhm dataClientToIhmApi;
    /**
     * API de DataClient pour Comm
     */
    private DataClientToComm dataClientToCommApi;
    /**
     * API de Comm pour DataClient
     */
    private CommToDataClient commToDataClientAPI;
    private IhmToDataClient ihmToDataClient;
    /**
     * Session courante
     */
    private Session sessionInfos;
    /**
     * Gestionnaire du téléversement de fichiers
     */
    private UploadManager uploadManager;
    /**
     * Gestionnaire pour le téléchargement de fichiers
     */
    private DownloadManager downloadManager;

    /**
     * Constructeur de DataManagerClient
     */
    public DataManagerClient(IhmToDataClient ihmToDataClient)
    {
        this.sessionInfos = new Session();
        this.uploadManager = new UploadManager();
        this.downloadManager = new DownloadManager();
        this.dataClientToCommApi = new DataClientToCommApi(this);
        this.dataClientToIhmApi = new DataClientToIhmApi(this);
        this.ihmToDataClient = ihmToDataClient;
        this.commToDataClientAPI = CommToDataClientAPI.getInstance();
        this.downloadManager.setCommToDataClientAPI(this.commToDataClientAPI);
    }

    UploadManager getUploadManager ()
    {
        return this.uploadManager;
    }

    DownloadManager getDownloadManager() {
        return downloadManager;
    }

    Session getSessionInfos()
    {
        return sessionInfos;
    }

    public void setDownloadManager(DownloadManager downloadManager) {
        this.downloadManager = downloadManager;
    }

    /**
     * Renvoie l'API de DataClient pour IHM.
     * @return Référence vers l'API de DataClient pour IHM
     */
    public DataClientToIhm getDataClientToIhmApi ()
    {
        return this.dataClientToIhmApi;
    }

    public DataClientToComm getDataClientToCommApi(){
        return this.dataClientToCommApi;
    }

    public CommToDataClient getCommToDataClientApi ()
    {
        return this.commToDataClientAPI;
    }
    public IhmToDataClient getIhmToDataClient()
    {
        return this.ihmToDataClient;
    }

    public void setCommToDataClientAPI (CommToDataClient fromCommApi)
    {
        this.commToDataClientAPI = fromCommApi;
    }

    /**
     * Connecte l'utilisateur au serveur après avoir vérifié ses identifiants
     * @param login login de l'utilisateur
     * @param password password de l'utilisateur
     */
    public boolean login(String login, String password)
    {
        boolean retValue = false;
        // TODO hash password for comparison
        File[] listOfUserFiles = new File("files/accounts").listFiles();

        String hashedPassword = hashPassword(password);

        // Etude de chaque fichier utilisateur
        for (File userFile : listOfUserFiles)
        {
            if (userFile.isFile())
            {
                try(FileInputStream fileIn = new FileInputStream(userFile.getPath());
                    ObjectInputStream objectIn = new ObjectInputStream(fileIn))
                {
                    Object obj = objectIn.readObject();
                    System.out.println(userFile.getName());
                    System.out.println(obj.getClass());
                    UserAccount comparisonAccount = (UserAccount) obj;
                    //UserAccount comparisonAccount = (UserAccount) obj;
                    if(comparisonAccount.getLogin().equals(login))
                    {
                        if(comparisonAccount.checkPassword(hashedPassword))
                        {
                            this.sessionInfos.setCurrentUser(comparisonAccount);
                            retValue = true;
                        }
                    }
                }
                catch(IOException |ClassNotFoundException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return retValue;
    }

    /**
     * Méthode qui connecte l'utilisateur courant au serveur
     * @return Succès de la connexion
     */
    public boolean serverLogin(String serverIp) {
        UserAccount userToConnect = this.sessionInfos.getCurrentUser();

        System.out.println("SIZEQSQQSZZEE " + this.sessionInfos.getCurrentUser().getProposedFiles().size());

        // FIXME Est-ce que le cast en UserStats empeche l'envoi du mdp ?
        commToDataClientAPI.requestUserConnexion(userToConnect,
                userToConnect.getProposedFiles(),
                serverIp);

        return true;
    }

    /**
     * Envoie une demande de déconnexion d'un utilisateur
     */
    public boolean logout()
    {
        // TODO catch erreur eventuelle.
        this.saveUserInfo(this.getSessionInfos().getCurrentUser());
        this.sessionInfos.getCurrentUser().getProposedFiles().clear();
        this.getCommToDataClientApi().requestLogoutToServer(this.sessionInfos.getCurrentUser());
        this.sessionInfos.setCurrentUser(null);
        return true; //TODO return to user logout successful ?
    }

    /**
     * Sauvegarde un profil nouvellement créé en local
     * @param user descripteur d'utilisateur
     * @return vrai si sauvegarde avec succès
     */
    boolean saveUserInfo(UserAccount user)
    {
        boolean registerSuccess = true;
        try(FileOutputStream fileOut =
                    new FileOutputStream(
                            FILEPATH_ACCOUNTS + user.getLogin() + "_" + user.getId() + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut))
        {
            // Création du flux vers le nouveau fichier

            // Sérialisation de l'utilisateur dans son fichier
            out.writeObject(user);
        }
        catch (IOException i)
        {
            i.printStackTrace();
            registerSuccess = false;
        }

        System.out.println("SAUVEGARDE" +user.getFirstName()+ " " +user.getPassword());

        return registerSuccess;
    }

    /**
     * Lit un fichier et le désérialise sous la forme d'un UserAccount, si compatible.
     * @param path Chemin du fichier à ouvrir (doit être un objet UserAccount sérialisé)
     * @return Objet UserAccount
     */
    private UserAccount openAccountFromFile (String path)
    {
        UserAccount account = null;
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream((path)))
        )
        {
            account = (UserAccount) in.readObject();
            in.close();
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return account;
    }

    /**
     * Retourne le hash d'un mot de passe.
     * Utilise l'algorithme MD5 sans salage.
     * @param passwordToHash Mot de passe à hasher
     * @return Hash du mot de passe
     */
    String hashPassword (String passwordToHash)
    {
        try
        {
            // Crée une instance de la classe MessageDigest avec l'algorithme MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            // On donne à l'algorithme le mot de passe sous forme d'octets
            md.update(passwordToHash.getBytes());
            // On récupère le résultat de l'algorithme
            byte[] bytes = md.digest();
            // On convertit le résultat de l'algorithme en héxadécimal
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes)
            {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Vérifie l'existence d'un compte utilisateur sérialisé, dans le dossier adapté : {@link com.lo23.data.Const#FILEPATH_ACCOUNTS}
     * @param username Nom d'utilisateur du compte à vérifier
     * @return vrai si le compte existe, faux sinon
     */
    public boolean accountExists(String username) {
        long numberOfMatchingFiles = 0;

        //Itère sur les fichiers de FILEPATH_ACCOUNTS et compte le nombre de fichiers qui ont "username_" en début de nom.
        try (Stream<Path> paths = Files.walk(Paths.get(FILEPATH_ACCOUNTS))) {
            numberOfMatchingFiles = paths
                    .filter(Files::isRegularFile)
                    .map(Path::toString)
                    .map(s -> s.substring(FILEPATH_ACCOUNTS.length()))
                    .filter(path -> path.matches("^(" + username + "_)(.)*$"))
                    .count();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Si au moins un fichier correspond, on retourne vrai
        return numberOfMatchingFiles > 0;
    }

    /**
     * Méthode qui rend un fichier indisponible en local puis envoie
     * un message au serveur pour partager l'information.
     * @param fileToMakeUnavailable fichier à rendre indisponible
     */
    public void makeLocalFileUnavailable(FileHandler fileToMakeUnavailable){
        /*
        Ici on ne supprime pas les parties de fichier sur le disque parce que
        dans l'éventualité ou on rendrait le fichier dispo de nouveau, on
        override les fileParts  qui existent déjà donc les laisser en mémoire
        ne pose pas de problème.
         */
        System.out.println("[DATA] Suppression du fichier :" + fileToMakeUnavailable.getHash() + "côté client");
        this.commToDataClientAPI.makeFilesUnavailableToServer(fileToMakeUnavailable, this.sessionInfos.getCurrentUser());
        // Supression du fichier en local
        UserAccount currentUser = this.sessionInfos.getCurrentUser();
        currentUser.removeProposedFile(fileToMakeUnavailable);
    }

    /**
     * Met à jour le profil utilisateur en local et
     * envoie une demande de propagation d'information
     * au serveur
     */
    public void changeUserInfos(String login, String password, String firstname, String lastname, int age)
    {
        this.sessionInfos.getCurrentUser().setPassword(password);
        // Si autre chose que le mdp a été changé
        if (!this.sessionInfos.getCurrentUser().getFirstName().equals(firstname)
                || !this.sessionInfos.getCurrentUser().getLastName().equals(lastname)
                || this.sessionInfos.getCurrentUser().getAge() != age)
        {
            // Mise à jour de l'utilisateur connecté
            this.sessionInfos.getCurrentUser().setFirstName(firstname);
            this.sessionInfos.getCurrentUser().setLastName(lastname);
            this.sessionInfos.getCurrentUser().setAge(age);

            // Communication des changements au serveur pour qu'il se mette à jour
            this.commToDataClientAPI.sendUserChangesToServer(this.getSessionInfos().getCurrentUser());
        }
    }

    public void downloadFile(FileHandler fileToDownload)
    {
       downloadManager.download(fileToDownload);
    }

    public void storeNewFilePart(FileHandler fileHandler, long blocNumber, byte[] data) {
        this.downloadManager.storeNewFilePart(fileHandler, blocNumber, data);
    }

    public void removeConnectedUser(User disconectedUser) {
        this.sessionInfos.getDirectory().removeUser(disconectedUser);
    }

    public void updateFileInfo(FileHandler updatedFile) {
        this.sessionInfos.getDirectory().updateFileInfo(updatedFile, this.sessionInfos.getCurrentUser());
       // this.getCommToDataClientApi().sendUpdatedFileInfo(updatedFile, this.sessionInfos.getCurrentUser());

    }

    public void updateConnectedUsers()
    {
        this.getIhmToDataClient().UpdateConnectedUsers(this.getSessionInfos().getLoggedUsers());
    }

}