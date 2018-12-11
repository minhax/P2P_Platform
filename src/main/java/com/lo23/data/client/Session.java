package com.lo23.data.client;

import com.lo23.common.user.UserAccount;
import com.lo23.common.user.UserStats;
import com.lo23.data.server.DirectoryUserFiles;

import java.util.Vector;

/**
 * Décrit la session courante ouverte par un utilisateur
 */
class Session
{
    /**
     * Utilisateur connecté
     */
    private UserAccount currentUser;

    /**
     * Autres utilisateurs connectés
     */
    private Vector<UserStats> otherLoggedUsers;

    private DirectoryUserFiles directory;

    Session()
    {
        this.directory = new DirectoryUserFiles();
        this.currentUser = null;
    }

    public UserAccount getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserAccount currentUser) {
        this.currentUser = currentUser;
    }

    Vector<UserStats> getOtherLoggedUsers()
    {
        return new Vector<>(otherLoggedUsers);
    }

    /**
     * Ajoute un utilisateur à la liste des
     * utilisateurs connectés
     * @param user utilisateur à ajouter
     */
    void mergeUserIntoLoggedUsers(UserStats user)
    {
        // Si le user n'est pas déjà dans la liste
        if(!this.getOtherLoggedUsers().contains(user))
        {
            // Ajouter le user
            this.getOtherLoggedUsers().add(user);
        }
        else
        {
            // Remplacer l'un par l'autre FIXME sale ?
            this.getOtherLoggedUsers().remove(user);
            this.getOtherLoggedUsers().add(user);
        }
    }


    public Vector<UserStats> getLoggedUsers(){
        return this.otherLoggedUsers;
    }


    /**
     * Permet de mettre à jour l'annuaire de correspondance entre les utilisateurs et les fichiers.
     * @param newDirectory Dictionnaire à remplacer
     */
    void setDirectoryUserFiles(DirectoryUserFiles newDirectory){
        this.directory=newDirectory;
    }

    public DirectoryUserFiles getDirectory()
    {
        return directory;
    }

}
