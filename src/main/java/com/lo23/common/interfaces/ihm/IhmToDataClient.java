package com.lo23.common.interfaces.ihm;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.user.User;
import com.lo23.common.user.UserAccount;
import com.lo23.common.user.UserIdentity;

import javax.print.DocFlavor;
import java.util.List;

public interface IhmToDataClient {

     void UpdateAvailableFiles(FileHandlerInfos fileInfo, UserIdentity user);
    /**
     * Met a jour la liste des fichiers disponibles avec un nouveau fichier
     * @param fileInfo informations du fichier qui vient d'être ajouté
     * @param user l'utilisateur qui a mis a disposition ce fichier
     */

     void sendUpdates(UserIdentity userInfo, List<FileHandlerInfos> fileInfo);

    /**
     * Met à jour la liste des fichiers de l'utilisateur concerné
     * @param userInfo utilisateur concerné par la mise a jour
     * @param fileInfo ensemble des fichiers nouvellement ajouté par l'utilisateur
     */

}
