package com.lo23.common.interfaces.comm;

import com.lo23.common.Comment;
import com.lo23.common.Rating;
import com.lo23.common.filehandler.*;
import com.lo23.common.user.*;

import java.util.List;


public interface CommToDataClient
{
    /**
     * Envoie les modifications de fichier
     * @param champ modification dans le contenu
     * @param file fichier que l'on modifie
     */
    public void sendFileChanges(String champ, FileHandler file);

    /**
     * Envoie les modifications relatives à un utilisateur (communication avec le serveur)
     * @param user utilisateur concerné
     */
    public void sendUserChangesToServer(UserIdentity user);

    /**
     * Envoie les modifications relatives à un utilisateur
     * @param user utilisateur concerné
     */
    public void sendUserChanges(UserIdentity user);

    /**
     * Rend indisponible un fichier (communication avec le serveur)
     * @param file fichier que l'on rend indisponible
     * @param user utilisateur qui le rend indisponible
     */
    public void makeFilesUnavailableToServer(FileHandler file, User user);

    /**
     * Transmet l'information comme quoi un fichier est rendu insponible par un utilisateur
     * @param user utilisateur qui le rend indisponible
     * @param file fichier que l'on rend indisponible
     */
    public void sendFileChanges(User user, FileHandler file);

    /**
     * Transmet l'ajout d'un nouveau commentaire sur un fichier
     * @param comment Commentaire
     * @param file Fichier commenté
     */
    public void sendFilesChanges(Comment comment, FileHandler file);

    /**
     * Transmet la demande de déconnexion de l'utilisateur (recevoir la demande)
     * @param user utilisateur qui se déconnecte
     */
    public void requestLogoutToServer(UserStats user);

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
    public void requestUserConnexion(UserStats user);


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
    public void sendFileChanges(Rating rate, FileHandler file);

    /**
     * Transmet la demande de partage de fichier (déjà partagé) de l'application client à CommServeur
     * @param file fichier qui va être partagé
     * @param user utilisateur qui propose le fichier
     */
    public void requestAddSource(FileHandler file, UserIdentity user);


    /**
     * Transmet la demande de partage de fichier (nouveau partage) de l'application client à CommServeur
     * @param file fichier qui va être partagé
     * @param user utilisateur qui propose le fichier
     */
    public void requestUploadFile(FileHandler file, UserIdentity user);


    /**
     * Envoie les infos sur la nouvelle source d'un fichier à tous les clients du réseau
     * @param file fichier partagé
     * @param user utilisateur qui devient source pour ce fichier
     */
    public void sendNewFileSource(FileHandler file, UserIdentity user);

    /**
     * Upload le fichier
     * @param file fichier partagé
     * @param user utilisateur qui upload
     */
    public void uploadFile(FileHandler file, UserIdentity user);

    /**
     * Transmet la demande de recherche de source d'un fichier (depuis l'appli client vers commserveur)
     * @param file fichier recherché
     * @param user utilisateur qui effectue la demande
     */
    public void requestFileLoc(FileHandler file, UserIdentity user);


}
