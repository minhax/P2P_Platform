package com.lo23.common.interfaces.data;

import com.lo23.common.Comment;
import com.lo23.common.Rating;
import com.lo23.common.exceptions.DataException;
import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.user.User;
import com.lo23.common.user.UserAccount;
import com.lo23.common.user.UserIdentity;

import java.io.File;
import java.util.List;

public interface DataClientToIhm
{

    /**
     * Crée un compte à partir des informations de base.
     * @param login Login de l'utilisateur
     * @param password Mot de passe (en clair) de l'utilisateur
     * @param firstname Prénom de l'utilisateur
     * @param lastname Nom de l'utilisateur
     * @param age Age de l'utilisateur
     * @throws DataException Exception lors de la création du compte
     */
    void createAccount (String login, String password, String firstname, String lastname, int age) throws DataException;

    /**
     * Demande à Data la source d'un fichier
     * @param fileToDownload fichier à télécharger
     */
    void requestFileLocation(FileHandler fileToDownload);

    /**
     * Demande à Data les informations détaillées d'un
     * utilisateur connecté
     * @param otherUser utilisateur dont on souhaite
     *                  les informations
     * @return descripteur détaillé de l'utilisateur
     */
    UserIdentity requestOtherUserInfo(User otherUser);

    /**
     * Envoie à Data les métadonnées d'un fichier proposé
     * entrées par l'utilisateur
     * @param newFile descripteur du fichier proposé
     */
    void requestShareNewFile(FileHandler newFile);

    /**
     * Envoie à Data la note attribuée à un fichier
     * @param rating note
     * @param ratedFile fichier noté
     */
    void requestRateFile(Rating rating, FileHandler ratedFile);

    /**
     * Envoie à Data le commentaire attribué à un fichier
     * @param comment commentaire
     * @param commentedFile fichier commenté
     */
    void requestCommentFile(Comment comment, FileHandler commentedFile);

    /**
     * Envoie à Data un fichier qui a subi des modifications
     * @param modifiedFile fichier modifié
     */
    void requestUpdateFileInfo(FileHandler modifiedFile);

    /**
     * Demande à Data le profil de l'utilisateur connecté
     * @return utilisateur de la session courante
     */
    UserAccount requestAccountInfos();

    /**
     * Envoie à Data le profil modifié de l'utilisateur connecté
     * @param modifiedUser profil utilisateur modifié
     */
    void requestSubmitUserChanges(UserAccount modifiedUser);

    /**
     * Envoie à Data un fichier devant être rendu
     * indisponible
     * @param file fichier à rendre indisponible
     */
    void requestMakeFileUnavailable(FileHandler file);

    /**
     * Envoie à Data une demande de déconnexion de
     * l'utilisateur connecté
     */
    void requestLogout();

    /**
     * Demande à Data de vérifier si les informations entrées par
     * l'utilisateur correspondent à un profil utilisateur local
     * existant
     * @param login pseudo entré par l'utilisateur
     * @param password mot de passe entré par l'utilisateur
     */
    boolean requestCheckCredentials(String login, String password);

    /**
     * Retourne la liste des fichiers mis à disposition
     * par l'utilisateur
     * @return fichiers qu'on a mis à disposition
     */
    List<FileHandler> requestFilesSharedByMe();

    /**
     * Retourne la liste de tous les fichiers disponibles
     * au partage
     * @return fichiers disponibles
     */
    List<FileHandler> requestFilesSharedByOthers();

    /**
     * Retourne les profils utilisateur tiers correspondant
     * à la recherche saisie par l'utilisateur
     * @param searchTerm saisie de l'utilisateur
     * @return utilisateurs correspondant à la saisie
     */
    List<UserIdentity> requestSearchUser(String searchTerm);

    /**
     * Retourne les fichiers disponibles correspondant
     * à la recherche saisie par l'utilisateur
     * @param searchTerm saisie de l'utilisateur
     * @return fichiers correspondant à la saisie
     */
    List<FileHandlerInfos> requestSearchFile(String searchTerm);

    /**
     * Envoie à Data le chemin vers un fichier
     * de profil utilisateur sérialisé à importer
     * @param pathToAccountFile chemin vers le fichier .ser
     */
    void requestImportAccountData(String pathToAccountFile);

    /**
     * Envoie à Data un profil que l'on souhaite exporter
     * dans un fichier
     * @param profileToExport utilisateur à exporter
     * @return fichier résultant de l'export
     */
    File requestExportAccountData(User profileToExport);

    /**
     * Retourne la liste locale des utilisateurs connectés
     * @return liste des utilisateurs connectés
     */
    List<UserIdentity> requestConnectedUsers();



}
