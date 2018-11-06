package com.lo23.common.interfaces.data;

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
     * Permet d'ajouter un utilisateur à la liste des utilisateurs connectés.
     * @param user Utilisateur à ajouter.
     */
    void addNewConnectedUser(UserStats user);

    /**
     * Permet d'ajouter les fichiers d'un utilisateur au serveur.
     * @param files fichiers à ajouter
     */
    void addNewUserFiles(UserStats user, List<FileHandlerInfos> files);

    /**
     * Permet de déconnecter un utilisateur
     * @param user utilisateur à déconnecter
     */
    void deleteDisconnectedUser(UserIdentity user);

    /**
     * Permet de retirer une source
     * @param file fichier à retirer
     * @param user utilisateur à retirer
     * @return Retourne le fichier retirer
     */
    FileHandler removeFileSource(FileHandler file, User user);

    /**
     * Permet de mettre à jour les informations d'un utilisateur.
     * @param user Utilisateur
     * @return Utilisateur mis à jour
     */
    UserIdentity updateUserChanges(UserIdentity user);

    /**
     * Permet d'ajouter un fichier au serveur
     * @param file fichierà ajouter
     * @param user utilisateur qui ajoute le fichier
     * @return Fichier ajouté
     */
    FileHandlerInfos addNewFileToServer(FileHandlerInfos file, UserIdentity user);

    /**
     * Permet d'obtenir la liste des utilisateur possédant un certain fichier
     * @param file Fichier à demander
     * @param user Utilisateur courant
     * @return Liste des utilisateurs possédant le fichier
     */
    List<UserIdentity> requestFileLocServer(FileHandler file, UserIdentity user);

    /**
     * Permet de mettre à jour les informations d'un fichier
     * @param file Fichier à mettre à jour
     * @return Fichier mis à jour
     */
    FileHandler updateFileChanges(FileHandlerInfos file);

}
