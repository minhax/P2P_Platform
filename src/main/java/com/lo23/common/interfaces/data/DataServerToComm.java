package com.lo23.common.interfaces.data;


import com.lo23.common.Rating;
import com.lo23.common.exceptions.DataException;
import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.user.User;
import com.lo23.common.user.UserIdentity;
import com.lo23.common.user.UserStats;

import java.util.List;

/**
 * Interface destinée à l'équipe Comm pour communiquer avec la partie Data Serveur.
 */
public interface DataServerToComm
{
    /**
     * Ajoute un utilisateur à la liste des
     * utilisateurs connectés
     * @param newlyConnectedUser utilisateur à ajouter
     */
    void addNewConnectedUser(UserStats newlyConnectedUser);

    /**
     * Ajoute les fichiers proposés par un utilisateur
     * au serveur
     * @param filesSharedByUser fichiers à ajouter
     * @param user Utilisateur proposant le fichier
     */
    void addNewUserFiles(List<FileHandlerInfos> filesSharedByUser, UserStats user);

    /**
     * Déconnecte un utilisateur
     * @param disconnectingUser utilisateur à déconnecter
     */
    void removeDisconnectedUser(User disconnectingUser);

    /**
     * Retire une source des sources d'un fichier
     * @param file fichier duquel retirer une source
     * @param sourceToRemove utilisateur à retirer des sources
     * @return fichier dont on a retiré une source
     */
    void removeFileSource(FileHandler file, User sourceToRemove);

    /**
     * Met à jour les informations d'un utilisateur
     * @param modifiedUser utilisateur modifié
     */
    void updateUserChanges(UserIdentity modifiedUser);

    /**
     * Ajoute un fichier au serveur
     * @param file fichier à ajouter
     * @param user utilisateur qui partage le fichier
     */
    void addNewFileToServer(FileHandlerInfos file, User user);

    /**
     * Retourne la liste des utilisateurs sources
     * d'un certain fichier
     * @param file fichier dont on demande les sources
     * @return liste des utilisateurs possédant le fichier
     */
    List<UserIdentity> requestFileLocationServer(FileHandler file);

    /**
     * Met à jour les informations d'un fichier
     * @param file fichier à mettre à jour
     */
    void updateFileChanges(FileHandlerInfos file);

    void addFileRating(Rating rating, FileHandlerInfos fileToRate) throws DataException;
}
