package com.lo23.common.interfaces.ihm;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.user.UserIdentity;

public interface IhmToDataClient {

    /**
     * Ajouter une source à la liste des utilisateurs mettant à disposition un ficher
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
