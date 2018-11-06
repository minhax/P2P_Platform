package com.lo23.common.interfaces.data;

import com.lo23.common.Comment;
import com.lo23.common.Rating;
import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.user.User;
import com.lo23.common.user.UserIdentity;
import com.lo23.common.user.UserStats;

public interface DataClientToComm
{
    /**
     * Demande à Comm les sources du fichier que l'utilisateur
     * veut télécharger
     * @param file fichier à télécharger
     * @param user utilisateur qui télécharge le fichier
     *             et à qui il faut renvoyer les sources
     */
    FileHandlerInfos getFile(UserIdentity user, FileHandler file);

    /**
     *
     * @param file
     */
    void saveFile(FileHandlerInfos file);

    /**
     * Envoie à DataClient le fichier devant
     * être partagé
     * @param file fichier à partager
     */
    void sendFileInfo(FileHandler file);


}
