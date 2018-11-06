package com.lo23.common.interfaces.ihm;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.user.User;
import com.lo23.common.user.UserAccount;
import com.lo23.common.user.UserIdentity;

import javax.print.DocFlavor;
import java.util.List;

public interface IhmToDataClient {

    /**
     * Demande à Data les champs que l'utilisateur devra renseigner
     * @return registerForm
     */
    public registerForm requestRegisterForm();

    /**
     * IHM envoie les informations de l'utilisateur à Data pour la création de compte en local
     * @param user : informations du compte utilisateur
     */
    public void requestRegisterForm(UserAccount user);

    /**
     * IHM envoie les informations de l'utilisateur à Data pour les vérifications pour connexion
     * @param user : infos sur le compte utilisateur
     * @param server : infos sur le serveur
     */
    public void checkAccount(UserAccount user, ServerInfos server);

    /**
     * IHM transmet à Data la demande de déconnexion
     */
    public void logout();

    /**
     * IHM récupère les informations du compte pour l'exportation
     * @param user informations du compte
     * @return UserAccount
     */
    public UserAccount requestAccountData(User user);

    /**
     * IHM envoie les informations du compte pour l'importation
     * @param user
     */
    public void storeDataAccount(UserAccount user);

    /**
     * IHM récupère les informations du compte pour la modification
     * @return UserAccount
     */
    public UserAccount requestAccountInfo();

    /**
     * IHM envoie à Data les données du compte modifié
     * @param user : nouvelles données d'utilisateur (UserAccount)
     */
    public void submitUserChanges(UserAccount user);

    public List <userAccount> refreshConnectedUsers(UserAccount user);
    /**
     *Demande à data la liste des utilisateurs connectés
     * @param User informations du compte
     */

    public List <userAccount> searchUser(User user);
    /**
     *Demande à data la liste des utilisateurs avec le compte user
     * @param User informations du compte
     */

    public UserAccount getUserInfo(String UserID);
    /**
     *Demande à data les informations relatives à un certain compte
     * @param UserID identifiant d'un compte
     */
    public void sendFileInfo(FileHandlerInfos fileInfo);
    /**
     *Envoie à data les informations relatives à un fichier donné
     * @param fileInfo informations d'un fichier
     */


    public List<UserIdentity> refreshConnectedUsers();
    
    /**
    *Demande � data de rendre un fichier indisponible
    * @param fileID identifiant du fichier
    */
    public void makeFileUnavailable(String fileID);
    
    /**
    *Envoie � data la note d'un fichier 
    * @param rate la note du fichier sur 5
    * @param fileID identifiant du fichier
    */
    public void sendRate(Rating rate, String fileID);
    
    /**
    *Envoie � data la mise � jour des information d'un fichier
    *par l'utilisateur
    * @param fileInfo les informations relative au fichier
    */
    public void updateFileInfo(FileHandlerInfos fileInfo);
    
    /**
    *Envoi d'un commentaire relatif � un fichier
    * @param comment le commentaire en question
    * @param fileID l'identifiant du fichier
    */
    public void sendComment(String comment, String fileID);
    
    /**
    *Envoie une requ�te de recherche de fichier par rapport � son nom
    * @param  fileName le nom du fichier recherch�
    * @return <List> FileStats la liste des informations des fichiers trouv�
    */
    public List<FileStats> searchFile(String fileName);
    
    /**
    *Demande la liste des fichiers partag�s
    * @return <List> FileStats la liste des fichiers partag�s et de leurs statistique
    */
    public List<FileStats> getUploadedFiles();
    
    /**
     *Demande la liste des fichiers disponible
     * @return <List> FileStats la liste des fichiers disponible et de leurs statistique
     */
     public List<FileStats> getAvailableFiles();
     
     /**
     *Demande la source d'un fichier
     *@param fileID l'identifiant du fichier
     */
     public void requestFileLocation(String fileID);
}
