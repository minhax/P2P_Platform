package com.lo23.common.interfaces.data;

import com.lo23.common.Comment;
import com.lo23.common.Rating;
import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.user.User;
import com.lo23.common.user.UserIdentity;

public interface DataClientToComm
{

    /**
     * Notifie Comm de l'ajout d'un commentaire sur un fichier
     * @param comment commentaire
     * @param file fichier commenté
     */
    public void sentFileChanges(Comment comment, FileHandler file);

    /**
     * Demande à Comm les sources du fichier que l'utilisateur
     * veut télécharger
     * @param file fichier à télécharger
     * @param user utilisateur qui télécharge le fichier // TODO askip c'est pour dire au serveur de renvoyer les sources du fichier à cet utilisateur
     */
    public void requestFileLocToServer(FileHandler file, UserIdentity user);

    /**
     * Envoie une demande de connexion d'un utilisateur
     * au serveur
     * @param user utilisateur qui se connecte
     * @param server IP du serveur
     */
    //public void connect(UserStats user, ServerInfo server); // TODO diag Envoyer demande de connexion

    /**
     * Envoie une demande de déconnexion d'un utilisateur
     * @param user utilisateur qui se déconnecte
     * @param server IP du serveur
     */
    //public void requestLogout(User user, ServerInfo server); // TODO diag Envoyer demande de connexion

    /**
     * Envoie à Comm le descripteur de fichier contenant
     * les changements apportés à ce fichier
     * @param file nouveau descripteur de fichier qui remplacera l'ancien
     */
    public void sendFilesChanges(FileHandler file);

    /**
     * Envoie à Comm le descripteur d'utilisateur contenant
     * les changement (autres que seulement mdp) apportés à
     * cet utilisateur
     * @param user nouveau descripteur d'utilisateur qui remplacera l'ancien
     */
    public void sendUserChanges(UserIdentity user);

    /**
     * Envoie à Comm la note attribuée à un fichier
     * @param rating note à comptabiliser
     * @param file fichier sur lequel appliquer la note
     */
    public void sendFileChanges(Rating rating, FileHandler file);

    /**
     * Envoie à Comm le fichier qu'un utilisateur souhaite
     * rendre indisponible
     * @param file fichier à rendre indisponible
     * @param User utilisateur qui rend le fichier indisponible
     */
    public void sendFileChanges(FileHandler file, User User);

    /**
     * Envoie à Comm le commentaire attribué à un fichier
     * @param comment commentaire à ajouter
     * @param file fichier sur lequel ajouter le commentaire
     */
    public void sendFileChanges(Comment comment, FileHandler file);

    /**
     * Envoie à Comm le nouvel utilisateur source pour un fichier
     * dans le cas où le fichier téléversé par cet utilisateur
     * existe déjà et donc dans le cas où il faut ajouter cet
     * utilisateur aux sources existantes pour ce fichier
     * @param file fichier
     * @param user nouvelle source
     */
    public void sendNewFileSource(FileHandler file, UserIdentity user);

    /**
     * Envoie à Comm le descripteur d'un fichier que l'utilisateur
     * souhaite mettre à disposition pour la première fois
     * @param file fichier
     * @param user source
     */
    public void uploadFile(FileHandler file, UserIdentity user);

    /**
     * Envoie à Comm le fichier à télécharger et la source
     * depuis lequel l'obtenir
     * @param user source choisie
     * @param file fichier à télécharger
     */
    public void askDownload(UserIdentity user, FileHandler file);
}
