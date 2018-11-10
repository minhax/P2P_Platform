package com.lo23.common.interfaces.comm;

import com.lo23.common.filehandler.*;
import com.lo23.common.user.*;


public interface CommToDataServer {

    /**
     * Retire un utilisateur de la liste des utilisateurs connectés
     * @param user utilisateur à retirer
     * @param files fichiers pour lesquels il était source
     */
    void removeDisconnectedUser(User user, List<FileHandler> files);

    /**
     * Transmet la liste des utilisateurs proposant un fichier au téléchargement
     * @param sourceUsers liste des utilisateurs sources pour un fichier
     */
    void sendFileLoc(List<UserIdentity> sourceUsers);

    /**
     * Transmet les informations mises à jour pour un utilisateurs à toutes les applciations clients
     * @param user utilisateur mis à jour
     */
    void sendUpdatedAccountToAll(UserIdentity user);

    /**
     * Transmet la nouvelle source d'un fichier
     * @param file fichier concerné
     * @param user utilisateur qui partage le fichier
     */
    void sendNewFileSource(FileHandler file, UserIdentity user);
}



