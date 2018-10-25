package com.lo23.common.interfaces.ihm;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.user.User;
import com.lo23.common.user.UserAccount;
import com.lo23.common.user.UserIdentity;

import java.util.List;

public interface IhmToDataClient {

    /**
     *
     * @return
     */
    public static List<FileHandlerInfos> getAvailableFiles()
    {
        return null;
    }

    public static List<FileHandlerInfos> getUploadedFiles()
    {
        return null;
    }

    public static List<UserIdentity> regreshConnectedUsers()
    {
        return null;
    }

    public static registerForm requestRegisterForm()
    {
        return null;
    }

    public static UserAccount requestAccountData(User user)
    {
        return null;
    }
    
    /**
    *Demande à data de rendre un fichier indisponible
    * @param fileID identifiant du fichier
    */
    public static void makeFileUnavailable(string fileID);
    
    /**
    *Envoie à data la note d'un fichier 
    * @param rate la note du fichier sur 5
    * @param fileID identifiant du fichier
    */
    public static void sendRate(Rating rate, string fileID);
    
    /**
    *Envoie à data la mise à jour des information d'un fichier
    *par l'utilisateur
    * @param fileInfo les informations relative au fichier
    */
    public static void updateFileInfo(fileHandlerInfos fileInfo);
    
    /**
    *Envoi d'un commentaire relatif à un fichier
    * @param comment le commentaire en question
    * @param fileID l'identifiant du fichier
    */
    public static void sendComment(string comment, string fileID);

    
    /**
    *Envoie une requête de recherche de fichier par rapport à son nom
    * @param  fileName le nom du fichier recherché
    * @return <List> FileStats la liste des informations des fichiers trouvé
    */
    public static <List> FileStats searchFile(string fileName);
    
    /**
    *Demande la liste des fichiers partagés
    * @return <List> FileStats la liste des fichiers partagés et de leurs statistique
    */
    public static <List> FileStats getUploadedFiles();
    
    /**
     *Demande la liste des fichiers disponible
     * @return <List> FileStats la liste des fichiers disponible et de leurs statistique
     */
     public static <List> FileStats getAvailableFiles();
     
     /**
     *Demande la source d'un fichier
     *@param fileID l'identifiant du fichier
     */
     public static void requestFileLocation(string fileID)
}
