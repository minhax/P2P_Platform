package com.lo23.data.server;

import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.user.User;
import com.lo23.common.user.UserIdentity;
import com.lo23.common.user.UserStats;

import java.util.Iterator;
import java.util.List;
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

    public DirectoryUserFiles getDirectory() {
        return directory;
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

        // fetchUsersProposedFiles(user) à implémenter
        Set<FileHandlerInfos> userFiles = getProposedFiles();
        if (userFiles!=null)
        {
            for (Iterator<FileHandlerInfos> i = userFiles.iterator(); i.hasNext();)
            {
                FileHandlerInfos f = i.next();
                this.directory.addProposedFile(user, f);
            }
        }
        // On connecte l'utilisateur
        this.connectedUsers.add(user);
    }

    /**
     * Permet de deconnecter un utilisateur
     * @param user Utilisateur à deconnecter
     * @throws IllegalArgumentException Exception levée si le paramètre passé est mauvais
     * @throws IllegalStateException Exception levée si l'utilisateur n'est pas connecté
     */
    public void disconnectUser(User user) throws IllegalArgumentException, IllegalStateException
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

    public void addFileToDirectory(UserIdentity user, FileHandlerInfos file)
    {
        this.directory.addProposedFile(user, file);
    }

    public void removeFileSourceFromDirectory(User user, FileHandler file)
    {
        this.directory.removeProposedFile(user, file);
    }

    public List<FileHandler> getUserFiles(User user){
        return (List<FileHandler>)(List<?>)this.directory.getFilesProposedByUser(user);
    }

    /**
     * Permet de modifier un utilisateur dans l'annuaire des connectés
     * @param modifiedUser Utilisateur à modifier
     * @throws IllegalStateException Exception levée si l'utilisateur n'est pas dans l'annuaire
     * @return L'objet UserIdentity tel qu'il était avant la modification
     */
    public void modifyConnectedUser(UserIdentity modifiedUser) throws IllegalStateException
    {
        int i = 0;
        boolean foundUser = false;
        UserIdentity oldUserIdentity = null;
        UserIdentity currentUser = null;

        // Itération sur les utilisateurs connectés
        while(i < this.getConnectedUsers().size() && !foundUser)
        {
            currentUser = this.getConnectedUsers().get(i);
            // Si on a trouvé l'utilisateur à modifier
            // (ceci fonctionne car un utilisateur ne peut modifier son ID)
            if(currentUser.getId()==modifiedUser.getId())
            {
                // On a trouvé l'utilisateur dans l'annuaire :
                // on modifie ses informations
                foundUser = true;
                oldUserIdentity = currentUser;
                currentUser.setFirstName(modifiedUser.getFirstName());
                currentUser.setLastName(modifiedUser.getLastName());
                currentUser.setAge(modifiedUser.getAge());
            }
            i = i + 1;
        }
        if (foundUser)
        {
            // Mise à jour des sources avec le nouvel objet UserIdentity dans this.directory
        }
        else
        {
            throw new IllegalStateException("User to modify is not connected/Does not exist !");
        }
    }
}
