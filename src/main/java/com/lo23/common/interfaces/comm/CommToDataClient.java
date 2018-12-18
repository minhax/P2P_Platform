package com.lo23.common.interfaces.comm;

import com.lo23.common.Comment;
import com.lo23.common.Rating;
import com.lo23.common.filehandler.*;
import com.lo23.common.user.*;

import java.util.List;


public interface CommToDataClient
{
    /**
     * Envoie les modifications faites sur les
     * métadonnées d'un fichier
     * @param file fichier mis à jour
     */

    void sendFileChanges(FileHandler file);

    /**
     * Envoie les modifications relatives à un utilisateur (communication avec le serveur)
     * @param user utilisateur concerné
     */
    void sendUserChangesToServer(UserIdentity user);

    void sendFileChanges(Rating rate, FileHandler file);

    /**
     * Rend indisponible un fichier (communication avec le serveur)
     * @param file fichier que l'on rend indisponible
     * @param user utilisateur qui le rend indisponible
     */
    void makeFilesUnavailableToServer(FileHandlerInfos file, User user);

    /**
     * Transmet l'information comme quoi un fichier est rendu insponible par un utilisateur
     * @param user utilisateur qui le rend indisponible
     * @param file fichier que l'on rend indisponible
     */
    void sendFileChanges(User user, FileHandler file);

    /**
     * Transmet l'ajout d'un nouveau commentaire sur un fichier
     * @param comment Commentaire
     * @param commentedFile Fichier commenté
     */
    void sendCommentedFile(Comment comment, FileHandlerInfos commentedFile, User user);

    /**
     * Envoie une nouvelle note attribuée à un fichier
     * @param rating note à ajouter au fichier
     * @param ratedFile fichier noté
     * @param user Utilisateur qui a noté le fichier
     */
    void sendRatedFile(Rating rating, FileHandlerInfos ratedFile, User user);

    /**
     * Envoie une nouvelle note attribuée à un fichier
     * @param rating note à ajouter au fichier
     * @param ratedFile fichier noté
     */
    /*void sendRatedFile(Rating rating, FileHandler ratedFile);*/

    /**
     * Transmet la demande de déconnexion de l'utilisateur (recevoir la demande)
     * @param user utilisateur qui se déconnecte
     * @param userFiles Liste des fichiers que l'utilisateur met à disposition du réseau
     */
    void requestLogoutToServer(UserStats user, List<FileHandlerInfos> userFiles);
    /*
    /**
     * Transmet la demande de déconnexion de l'utilisateur (envoyer la demande)
     * @param user utilisateur qui se déconnecte
     */

    //public void requestLogout(UserIdentity user);


    /**
     * Transmet la demande de connexion d'un utiisateur au serveur
     * @param user utilisateur qui veut se connecter
     */
    void requestUserConnexion(UserStats user, List<FileHandlerInfos> fi, String serverIP);

    /*
    /**
     * Envoie la demande de connexion
     * @param user utilisateur qui se connecte
     * @param IP IP de la machine de l'utilisateur
     */

    //public void connect(UserStats user, long IP);


    /**
     * Transmet la demande de partage de fichier (déjà partagé) de l'application client à CommServeur
     * @param file fichier qui va être partagé
     * @param user utilisateur qui propose le fichier
     */
    void requestAddSource(FileHandler file, UserIdentity user);


    /**
     * Transmet la demande de partage de fichier (nouveau partage) de l'application client à CommServeur
     * @param file fichier qui va être partagé
     * @param user utilisateur qui propose le fichier
     */
    void requestUploadFile(FileHandlerInfos file, UserIdentity user);


    /**
     * Envoie les infos sur la nouvelle source d'un fichier à tous les clients du réseau
     * @param file fichier partagé
     * @param user utilisateur qui devient source pour ce fichier
     */

    void uploadFile(FileHandlerInfos file, UserIdentity user);

    /**
     * Transmet la demande de recherche de source d'un fichier (depuis l'appli client vers commserveur)
     * @param file fichier recherché
     * @param user utilisateur qui effectue la demande
     */
    void requestFileLoc(FileHandler file, UserIdentity user);


}

