package com.lo23.data.client;

import com.lo23.common.exceptions.DataException;
import com.lo23.common.user.UserAccount;
import com.lo23.common.user.UserIdentity;
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
    void mergeUserIntoLoggedUsers(UserIdentity user)
    {

        UserStats tmp = null;
        for (UserStats usr :
                this.getOtherLoggedUsers() )
        {
            if (usr.getId().equals(user.getId())){
                tmp = usr;
            }
        }

        if (tmp == null){
            System.out.println("On ne trouve pas le UserStats correspondant");
            //throw new DataException("On ne trouve pas le UserStats correspondant");
        }

        // Si le user n'est pas déjà dans la liste
        if(!this.getOtherLoggedUsers().contains(tmp))
        {
            // Ajouter le user
            this.getOtherLoggedUsers().add(tmp);
        }
        else
        {
            // Remplacer l'un par l'autre FIXME sale ?
            this.getOtherLoggedUsers().remove(tmp);
            this.getOtherLoggedUsers().add(tmp);
        }
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
