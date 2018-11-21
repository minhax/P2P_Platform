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

    /**
     * Envoie les modifications relatives à un utilisateur
     * @param user utilisateur concerné
     */
    void sendUserChanges(UserIdentity user);

    /**
     * Rend indisponible un fichier (communication avec le serveur)
     * @param file fichier que l'on rend indisponible
     * @param user utilisateur qui le rend indisponible
     */

    void makeFilesUnavailableToServer(FileHandler file, User user);

    /**
     * Transmet l'information comme quoi un fichier est rendu insponible par un utilisateur
     * @param user utilisateur qui le rend indisponible
     * @param file fichier que l'on rend indisponible
     */
    void sendFileChanges(User user, FileHandler file);

    /**
     * Transmet l'ajout d'un nouveau commentaire sur un fichier
     * @param comment Commentaire
     * @param file Fichier commenté
     */
    void sendFilesChanges(Comment comment, FileHandler file);

    /**
     * Transmet la demande de déconnexion de l'utilisateur (recevoir la demande)
     * @param user utilisateur qui se déconnecte
     */
    void requestLogoutToServer(UserStats user);

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
     * Envoie une nouvelle note attribuée à un fichier
     * @param rate note
     * @param file fichier modifié
     */
    void sendFileChanges(Rating rate, FileHandler file);

    /*/**
     * Transmet la demande de partage de fichier (déjà partagé) de l'application client à CommServeur
     * @param file fichier qui va être partagé
     * @param user utilisateur qui propose le fichier
     */
<<<<<<< HEAD
    //public void requestAddSource(FileHandlerInfos file, UserIdentity user);
=======
    void requestAddSource(FileHandler file, UserIdentity user);
>>>>>>> communication2


    /**
     * Transmet la demande de partage de fichier (nouveau partage) de l'application client à CommServeur
     * @param file fichier qui va être partagé
     * @param user utilisateur qui propose le fichier
     */
<<<<<<< HEAD
    public void requestUploadFile(FileHandlerInfos file, User user);
=======
    void requestUploadFile(FileHandler file, UserIdentity user);
>>>>>>> communication2


    /*/**
     * Envoie les infos sur la nouvelle source d'un fichier à tous les clients du réseau
     * @param file fichier partagé
     * @param user utilisateur qui devient source pour ce fichier
     */
<<<<<<< HEAD
    //public void sendNewFileSource(FileHandler file, UserIdentity user);
=======
    void sendNewFileSource(FileHandler file, UserIdentity user);
>>>>>>> communication2

    /*/**
     * Upload le fichier
     * @param file fichier partagé
     * @param user utilisateur qui upload
     */
<<<<<<< HEAD
    //public void uploadFile(FileHandler file, UserIdentity user);
=======
    void uploadFile(FileHandler file, UserIdentity user);
>>>>>>> communication2

    /**
     * Transmet la demande de recherche de source d'un fichier (depuis l'appli client vers commserveur)
     * @param file fichier recherché
     * @param user utilisateur qui effectue la demande
     */
    void requestFileLoc(FileHandler file, UserIdentity user);


}
