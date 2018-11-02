package com.lo23.common.interfaces.comm;

import com.lo23.common.filehandler.*;
import com.lo23.common.user.*;


public interface CommToDataServer {

    /**
     * Envoie à data la demande d'ajout d'une nouvelle source d'un fichier
     * @param file fichier pour lequel on ajoute une source
     * @param user utilisateur qui devient une nouvelle source
     */
    public void addNewFileSource(FileHandler file, UserIdentity user);

    /**
     * Demande d'ajout d'un utilisateur à la liste des utilisateurs connectés
     * @param user utilisateur qui se connecte
     */
    public void addNewConnectedUser(UserStats user);

    /**
     * Met à jour les informations relatives à un utilisateur
     * @param user Utilisateur pour lequel on met à jour les données
     */
    public void updateUserChanges(UserIdentity user);

    /**
     * Demande au serveur la localisation d'un fichier
     * @param file fichier que l'on recherche
     * @param user utilisateur qui fait la demande
     */
    public void requestFileLoc(FileHandler file, UserIdentity user);

    /**
     * Retire un utilisateur de la liste des personnes mettant un fichier à disposition
     * @param user utilisateur concerné
     * @param file fichier concerné
     */
    public void removeFileSource(FileHandler file, UserIdentity user);

    /**
     * Supprime un utilisateur de la liste des utilisateurs connectés
     * @param user utilisateur qui se déconnecte
     */
    public void deleteDisconnectedUser(User user);

    /**
     * Permet d'enlever un utilisateur deconnecté de la liste des personnes proposant tel ou tel fichier, pour chacun des fichiers qu'il met à disposition
     * @param user utilisateur déconnecté
     */
    public void deleteDisconnectedUserFiles(User user);

    /**
     * Met à jour les informations concernant les fichier
     * @param champ champ modifié
     * @param file fichier modifié
     */
    public void updateFileChanges(String champ, FileHandler file);

    /**
     * Ajoute les informations relatives à un fichier
     * @param file fichier à ajouter
     * @param user utilisateur qui propose le fichier
     */
    public void addNewFileToServer(FileHandler file, UserIdentity user);


}
