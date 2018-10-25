package com.lo23.common.interfaces.comm;
import user.*;
import filehandler.*;

public interface CommServerToAppClientDistant{
    /**
     * Envoie les informations actualisées au sujet d'un utilisateur à tous les noeuds du réseau
     * @param user utilisateur modifié
     */
    public void sendUpdatedAccountToAll(UserIdentity user);

    /**
     * Envoie les infos concernant l'actualisation d'un fichier à tous les noeuds du réseau
     * @param file fichier modifié
     */
    public void sendUpdatedFileToAll(FileHandler file);

    /**
     * Indique à tous les noeuds qu'un utilisateur s'est déconnecté
     * @param user utilisateur qui s'est déconnecté
     * @param files fichiers que cet utilisateur mettait à disposition
     */
    public void removeDisconnectedUser(User user, List<FileHandler> files);

    /**
     * Envoie les infos concernant un nouveau fichier dispo à tous les noeuds du réseau
     * @param file nouveau fichier
     * @param user utilisateur qui met le fichier à dispo
     */
    public void sendNewFileInfo(FileHandler file, UserId user);
}