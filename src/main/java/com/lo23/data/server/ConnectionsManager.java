package com.lo23.data.server;

import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.user.User;
import com.lo23.common.user.UserIdentity;
import com.lo23.common.user.UserStats;

import java.util.Set;
import java.util.Vector;

/**
 * Permet de gérer les connections des utilisateurs ainsi que les fichiers proposés par ces utilisateurs.
 */
public class ConnectionsManager
{
    /**
     * Vecteur des utilisateurs connectés
     */
    private Vector<UserIdentity> connectedUsers;
    /**
     * Annuaires des fichiers proposés par utilisateurs.
     */
    private DirectoryUserFiles directory;

    /**
     * Constructeur de gestionnaire de connexions.
     */
    public ConnectionsManager()
    {
        this.connectedUsers = new Vector<>();
        this.directory = new DirectoryUserFiles();
    }

    /**
     * Permet de connecter un utilisateur
     * @param user Utilisateur à connecter
     * @throws IllegalArgumentException Exception levée si le paramètre passé est mauvais
     * @throws IllegalStateException Exception levée si l'utilisateur est déjà connecté
     */
    public void connectUser(UserIdentity user) throws IllegalArgumentException, IllegalStateException
    {
        // Si le paramètre passé est null
        if (user == null)
        {
            throw new IllegalArgumentException("User should not be null");
        }

        // Si l'utilisateur est déjà connecté
        if (this.connectedUsers.contains(user))
        {
            throw new IllegalStateException("User is already connected !");
        }

        // On récupère les fichiers proposés par l'user qui se connecte pour mettre à jour le directory
        /*
        // fetchUsersProposedFiles(user) à implémenter
        Vector<FileHandlerInfos> userFiles = fetchUsersProposedFiles(user)
        if (userFiles!=null)
        {
            for (Iterator<FileHandlerInfos> i = userFiles.iterator(); i.hasNext();)
            {
                f = i.next();
                this.directory.addProposedFile(user, f);
            }
        }
        */
        // On connecte l'utilisateur
        this.connectedUsers.add(user);
    }

    /**
     * Permet de deconnecter un utilisateur
     * @param user Utilisateur à deconnecter
     * @throws IllegalArgumentException Exception levée si le paramètre passé est mauvais
     * @throws IllegalStateException Exception levée si l'utilisateur n'est pas connecté
     */
    public void disconnectUser(UserIdentity user) throws IllegalArgumentException, IllegalStateException
    {
        // Si le paramètre passé est null
        if (user == null)
        {
            throw new IllegalArgumentException("User should not be null");
        }

        // Si l'utilisateur n'est pas connecté
        if (!this.connectedUsers.contains(user))
        {
            throw new IllegalStateException("User is not connected !");
        }


        try
        {
            // On deconnecte l'utilsateur
            this.connectedUsers.remove(user);
            // On retire les fichiers de l'utilisateur s'il en a
            if(this.directory.getFilesProposedByUser(user)!=null)
            {
                this.directory.removeUser(user);
            }
        }
        catch (IllegalArgumentException iae)
        {
            iae.printStackTrace();
        }
    }


    /**
     * Retourne un vecteur des utilisateurs connectés
     * @return Les utilisateur connectés
     */
    public Vector<UserIdentity> getConnectedUsers(){
        return this.connectedUsers;
    }

    /**
     * Permet d'obtenir les fichiers proposés
     * @return Un ensemble contenant les fichiers proposés.
     */
    public Set<FileHandlerInfos> getProposedFiles(){
        return this.directory.getProposedFiles();
    }

    public void addFileToDirectory(UserStats user, FileHandlerInfos file){
        this.directory.addProposedFile(user, file);
    }

    public void removeFileSourceFromDirectory(User user, FileHandler file){
        this.directory.removeProposedFile(user, file);
    }


}
