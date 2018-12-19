package com.lo23.data.client;

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
    private Vector<UserIdentity> otherLoggedUsers = new Vector<>();

    /**
     * Annuaire faisant la correspondance entre
     * utilisateurs et fichiers
     */
    private DirectoryUserFiles directory;

    /**
     * Constructeur de Session
     */
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

    Vector<UserIdentity> getOtherLoggedUsers()
    {
        return new Vector<>(otherLoggedUsers);
    }

    void setOtherLoggedUsers(Vector<UserIdentity> otherLoggedUsers)
    {
        if (otherLoggedUsers == null)
            throw new NullPointerException(("Data:Session:: trying to set a null vector"));

        this.otherLoggedUsers = otherLoggedUsers;
    }

    /**
     * Ajoute un utilisateur à la liste des
     * utilisateurs connectés
     * @param user utilisateur à ajouter
     */
    void mergeUserIntoLoggedUsers(UserIdentity user)
    {
        boolean userFound = false;
        for(UserIdentity usr : this.getOtherLoggedUsers())
        {
            if(usr.getId().equals(user.getId()))
            {
                this.getOtherLoggedUsers().remove(usr);
                this.getOtherLoggedUsers().add(user);
                userFound = true;
            }
        }
        // Si l'utilisateur n'a pas été trouvé
        if(!userFound)
        {
            this.otherLoggedUsers.add(user);
        }
    }


    public Vector<UserIdentity> getLoggedUsers(){
        return this.otherLoggedUsers;
    }


    /**
     * Met à jour l'annuaire de correspondance entre
     * les utilisateurs et les fichiers.
     * @param newDirectory dictionnaire à remplacer
     */
    void setDirectoryUserFiles(DirectoryUserFiles newDirectory){
        this.directory=newDirectory;
    }

    public DirectoryUserFiles getDirectory()
    {
        return directory;
    }

}
