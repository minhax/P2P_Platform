package com.lo23.common.interfaces.comm;

import com.lo23.common.filehandler.*;
import com.lo23.common.user.*;

import java.util.List;


public interface CommToDataServer {

    /**
     * Transmet la liste des utilisateurs proposant un fichier au téléchargement
     * @param sourceUsers liste des utilisateurs sources pour un fichier
     */
    public void sendFileLoc(List<UserIdentity> sourceUsers);

    /**
     * Transmet les informations mises à jour pour un utilisateurs à toutes les applciations clients
     * @param user utilisateur mis à jour
     */
    public void sendUpdatedAccountToAll(UserIdentity user);

    /**
     * Transmet la nouvelle source d'un fichier à tous les utilisateurs
     * @param file fichier concerné
     * @param user utilisateur qui partage le fichier
     */
    public void sendNewFileSource(FileHandlerInfos file, UserIdentity user);

    public void  sendFilePart(User userAsking, User userSource, FileHandlerInfos file, long part, byte[] content);
}



