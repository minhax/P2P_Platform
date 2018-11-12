package com.lo23.common.interfaces.comm;

import com.lo23.common.filehandler.*;
import com.lo23.common.user.*;

import java.util.List;


public interface CommToDataServer {

    /**
     * Retire un utilisateur de la liste des utilisateurs connectés (broadcast de l'info à tous les utilisateurs connectés)
     * @param user utilisateur à retirer
     * @param files fichiers pour lesquels il était source
     */
    public void removeDisconnectedUser(User user, List<FileHandler> files);

    /**
     * Ajoute un utilisateur à la liste des utilisateurs connecté (broadcast de l'info à tous les utilisateurs connectés)
     * @param user utilisateur qui se connecte
     * @param files fichiers qu'il met à disposition
     */
    public void sendConnectedUserToAll(UserIdentity user, List<FileHandler> files);

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
     * Transmet la nouvelle source d'un fichier
     * @param file fichier concerné
     * @param user utilisateur qui partage le fichier
     */
    public void sendNewFileSource(FileHandler file, UserIdentity user);
}



