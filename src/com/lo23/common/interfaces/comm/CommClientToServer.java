package com.lo23.common.interfaces.comm;
import user.*;
import filehandler.*;
import com.lo23.common.Rating;
import com.lo23.common.Comment;

public interface CommClientToServer{
    /** Envoie une demande de déconnexion au serveur
     * @param user utilisateur qui se déconnecte
     */
    void public requestLogoutToServer(User user);

    /** Envoie les informations concernant les modifs de fichier (notation) au serveur central
     * @param rate note à ajouter
     * @param file fichier modifié
     */
    void public sendFileChangesToServer(Rating rate, FileHandler file);

    /** Envoie les informations concernant les modifs de fichier (contenu) au serveur central
     * @param champ champ modifié
     * @param file fichier modifié
     */
    void public sendFileChangesToServer(String champ, FileHandler file);

    /** Envoie les informations concernant les modifs de fichier (ommentaire) au serveur central
     * @param comment commentaire à ajouter
     * @param file fichier modifié
     */
    void public sendFileChangesToServer(Comment comment, FileHandler file);

    /**
     * Demande la connexion d'un utilisateur
     * @param user Utilisateur qui se connecte
     */
    void public requestUserConnexion(UserStats user);

    /**
     * Demande l'emplacement d'un fichier
     * @user utilisateur demandeur
     * @file fichier que l'on recherche
     */
    void public requestFileLoc(FileHandler file, UserId user);

    /**
     * Envoie les modifs concernant un utilisateur au serveur
     * @param user utilisateur concerné
     */
    void public sendUserChangesToServer(UserId user);




}