package com.lo23.common.interfaces.ihm;

import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.user.UserIdentity;
import java.util.List;

public interface IhmToDataClient {
    
	/**
     * Met a jour la liste des utilisateurs connectés
     * @param user le nouvel utilisateur connecté au serveur
     */
	void UpdateConnectedUsers(UserIdentity user);

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
    void showPercentageComplete(FileHandler file, float percentage);


}
