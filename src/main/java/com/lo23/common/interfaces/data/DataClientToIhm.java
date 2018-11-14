package com.lo23.common.interfaces.data;

import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.user.UserIdentity;

import java.util.Vector;

public interface DataClientToIhm
{
    /**
     * Demande la mise à jour des fichiers disponibles
     * à afficher pour l'utilisateur
     * @param file nouveau fichier
     * @param user utilisateur qui a téléversé
     */
    public void updateAvailableFiles(FileHandler file, UserIdentity user);


    // TODO ces deux méthodes sont distinctes ? Ou la même ?
    /**
     * Demande à l'IHM de mettre à jour ses utilisateurs connectés
     * suite à la connexion d'un utilisateur
     * @param user utilisateur nouvellement connecté
     * @param files fichiers dont l'utilisateur est la source
     */
    public void sendUpdatesUserConnected(UserIdentity user, Vector<FileHandler> files);

    /**
     * Demande à l'IHM de mettre à jour ses utilisateurs connectés
     * suite à la déconnexion d'un utilisateur
     * @param user utilisateur nouvellement déconnecté
     * @param files fichiers dont l'utilisateur est la source
     */
    public void sendUpdatesUserDisconnected(UserIdentity user, Vector<FileHandler> files);

    /**
     * Demande à l'IHM de mettre à jour les sources d'un fichier
     * suite à l'ajout d'une nouvelle source
     * @param file fichier dont les sources seront mises à jour
     * @param user nouvelle source
     */
    public void addFileSource(FileHandler file, UserIdentity user);

    /**
     * Envoie à l'IHM le pourcentage de complétion du téléchargement
     * @param percentage pourcentage de complétion du téléchargement
     */
    public void showPercentageComplete(float percentage);




}
