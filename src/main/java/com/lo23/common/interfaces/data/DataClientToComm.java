package com.lo23.common.interfaces.data;

import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.user.User;
import com.lo23.common.user.UserIdentity;
import com.lo23.common.user.UserStats;

import java.util.List;

public interface DataClientToComm
{
    /**
     * Réponse du serveur à DataClient
     * concernant les sources d'un fichier
     * @param sources sources du fichier
     */
    void receiveFileLocations(List<UserIdentity> sources);

    void receiveFilePart(FileHandler fileHandler, long blocNumber, byte[] data);

    // TODO cette méthode est encore floue et à revoir, mais c'est pas urgent pour l'instant
    /**
     * Demande à Data le fichier à télécharger
     * @param userWhoRequestedFile utilisateur qui veut
     *                             télécharger le fichier
     * @param fileToDownload fichier demandé
     * @return fichier à télécharger
     */
    FileHandlerInfos requestFileToDownload(UserIdentity userWhoRequestedFile, FileHandler fileToDownload);

    /**
     * Demande à Data de recomposer le fichier à partir des fileparts
     * @param file infos du fichier à recomposer
     */
    void mergeFileParts(FileHandlerInfos file);

    /**
     * Notifie DataClient d'un nouveau fichier
     * proposé au partage
     * @param newSharedFile fichier proposé
     */
    void notifyNewSharedFileToAll(FileHandler newSharedFile);

    /**
     * Notifie DataClient d'une nouvelle source pour
     * un fichier existant
     * @param existingFile fichier existant
     * @param newSource nouvelle source
     */
    void notifyNewSourceToAll(FileHandler existingFile, UserIdentity newSource);

    /**
     * Notifie les clients distants des modifications
     * apportées à un fichier partagé
     * @param modifiedFile fichier modifié
     */
    void notifyUpdatedSharedFileToAll(FileHandler modifiedFile);

    /**
     * Notifie les clients distants du profil
     * d'un autre utilisateur ayant subi des modifications
     * @param newlyModifiedUser profil autre utilisateur modifié
     */
    void notifyOtherUserUpdatedAccountToAll(UserStats newlyModifiedUser);

    /**
     * Notifie les clients distants de la déconnexion d'un autre
     * utilisateur et donc de son retrait en tant que source
     * des fichiers qu'il propose
     * @param newlyDisconnectedUser autre utilisateur déconnecté
     * @param files fichiers dont cet utilisateur est la source
     */
    void notifyOtherUserDisconnectedToAll(User newlyDisconnectedUser, List<FileHandlerInfos> files);

    /**
     * Notifie les clients distants de la connexion d'un autre
     * utilisateur et donc de son ajout en tant que source
     * des fichiers qu'il propose
     * @param newlyConnectedUser autre utilisateur connecté
     * @param files fichiers dont cet utilisateur est la source
     */
    void notifyOtherUserConnectedToAll(UserIdentity newlyConnectedUser, List<FileHandlerInfos> files);

    /**
     * Fonction qui permet d'obtenir le filePart numéro "part" du fichier
     * "file" pour l'envoyer à userAsking.
     * @param userAsking
     * @param userSource
     * @param file
     * @param part
     */
    void getFilePart(User userAsking, User userSource, FileHandler file, long part);
}
