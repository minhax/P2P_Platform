package com.lo23.common.interfaces.ihm;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.user.UserIdentity;
import java.util.List;

public interface IhmToDataClient {
    /**
     * Met a jour la liste des fichiers disponibles avec un nouveau fichier
     * @param fileInfo informations du fichier qui vient d'être ajouté
     * @param user l'utilisateur qui a mis a disposition ce fichier
     */
     void updateAvailableFiles(FileHandlerInfos fileInfo, UserIdentity user);

    /**
     * Met à jour la liste des fichiers de l'utilisateur concerné
     * @param userInfo utilisateur concerné par la mise a jour
     * @param fileInfo ensemble des fichiers nouvellement ajouté par l'utilisateur
     */
     void sendUpdates(UserIdentity userInfo, List<FileHandlerInfos> fileInfo);

    /**
     * Ajoute une source à la liste des utilisateurs mettant à disposition un ficher
     * @param fileInfo : le fichier concerné
     * @param user : utilisateur mettant à disposition un fichier
     */
    void addFileSource(FileHandlerInfos fileInfo, UserIdentity user);

    /**
     * Affiche l'utilisateur mettant à disposition le fichier
     * @param userInfo les informations sur l'utilisateur
     * @return l'utilisateur
    */
    UserIdentity updateFileDownload(UserIdentity userInfo);


    /**
     * Mets à jour la barre de progression du télechargement
     * @param percentage pourcentage de complétion duj télechargement
     */
    void showPercentageComplete(float percentage);


}
