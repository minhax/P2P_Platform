package com.lo23.common.interfaces.data;

import com.lo23.common.Comment;
import com.lo23.common.Rating;
import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.user.User;
import com.lo23.common.user.UserIdentity;
import com.lo23.common.user.UserStats;

import java.util.List;

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
     * @param user utilisateur qui télécharge le fichier // TODO askip c'est pour dire au serveur de renvoyer les sources de fichiers à cet utilisateur
     */
    public void requestFileLocToServer(FileHandler file, UserIdentity user);

    //public void connect(UserStats user, ServerInfo server); // TODO diag Envoyer demande de connexion
    //public void requestLogout(User user, ServerInfo server); // TODO diag Envoyer demande de connexion

    /**
     * Envoie à Comm le descripteur de fichier contenant
     * les changements apportés à ce fichier
     * @param file nouveau descripteur de fichier qui remplacera l'ancien
     */
    public void sendFilesChanges(FileHandler file);

    /**
     * Envoie à Comm le descripteur d'utilisateur contenant
     * les changement (autres que mdp) apportés à cet utilisateur
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
     * Envoie à Comm le nouvel utilisateur source pour un fichier
     * dans le cas où le fichier téléversé par cet utilisateur
     * existe déjà et donc dans le cas où il faut ajouter cet
     * utilisateur aux sources existantes pour ce fichier
     * @param file fichier
     * @param user nouvelle source
     */
    public void sendNewFileSource(FileHandler file, UserIdentity user);

    /**
     *
     * @param user
     * @param files
     */
    public void sendUpdates(UserIdentity user, List<FileHandlerInfos> files);

    /**
     * Envoie à Comm le fichier qu'un utilisateur souhaite
     * rendre indisponible
     * @param file fichier à rendre indisponible
     * @param User utilisateur qui rend le fichier indisponible
     */ // TODO pourquoi appeler ça sendFileChanges ?
    public void sendFileChanges(FileHandler file, User User);


}
