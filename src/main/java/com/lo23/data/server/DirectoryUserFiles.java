package com.lo23.data.server;

import com.lo23.common.Comment;
import com.lo23.common.Rating;
import com.lo23.common.Tag;
import com.lo23.common.exceptions.DataException;
import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.user.User;
import com.lo23.common.user.UserIdentity;
import com.lo23.data.Utils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Annuaire permettant de gérer les fichiers proposés par les utilisateurs.
 */
public class DirectoryUserFiles
{

    /**
     * Map contenant les fichiers proposés par les utilisateurs.
     */
    private HashMap<UserIdentity, Vector<FileHandler>> userFiles;
    /**
     * Map contenant les utilisateurs qui proposent chaque fichier.
     */
    private HashMap<FileHandler, Vector<UserIdentity>> filesUser;

    /**
     * Constructeur qui instancie les deux HashMap permettant de gérer l'annuaire.
     */
    public DirectoryUserFiles()
    {
        this.userFiles = new HashMap<>();
        this.filesUser = new HashMap<>();
    }

    /**
     * Permet d'ajouter un couple UserIdentity, FileHandler à l'annuaire des fichiers proposés.
     * @param user Utilisateur
     * @param file Fichier
     * @throws IllegalStateException Levée si fichier déjà proposé par cet utilisateur
     * @throws IllegalArgumentException Levée si les paramètres passés sont mauvais
     */
    public void addProposedFile(UserIdentity user, FileHandler file) throws IllegalStateException, IllegalArgumentException
    {

        // update userFiles
        Vector<FileHandler> existentFiles = this.userFiles.getOrDefault(user, new Vector<>());

        if (!existentFiles.contains(file))
            existentFiles.add(file);
        else
            System.out.println("File existait déjà!");

        this.userFiles.put(user, existentFiles);

        // update filesUser
        Vector<UserIdentity> existentSources = this.filesUser.getOrDefault(file, new Vector<>());
        if (!existentSources.contains(user))
            existentSources.add(user);
        else
            System.out.println("User source existait déjà!");
        this.filesUser.put(file, existentSources);

    }


    /**
     * Permet d'enlever un couple UserIdentity FileHandler de l'annuaire
     * @param user Utilisateur
     * @param file Fichier
     * @throws NullPointerException Exception levée si mauvais arguements passés
     */
    public void removeProposedFile(User user, FileHandler file) throws NullPointerException
    {
        // On vérifie que les paramètres passés sont valides, sinon on lève une exception.
        Utils.throwExceptionIfNull("User should not be null", user);
        Utils.throwExceptionIfNull("File should not be null", file);

        // On lève une exception si l'utilisateur propose déjà ce fichier, sinon on enlève les informations des HashMap
        if (this.userFiles.get(user).contains(file) && this.filesUser.get(file).contains(user))
        {
            // On enlève le fichier
            this.userFiles.get(user).remove(file);
            this.filesUser.get(file).remove(user);

            // Nettoyage des HashMap
            if (this.getFilesProposedByUser(user).isEmpty())
            {
                this.userFiles.remove(user);
            }

            if (this.getUsersThatProposeFile(file).isEmpty())
            {
                this.filesUser.remove(file);
            }
        }
        else
        {
            throw new IllegalArgumentException("This file is not proposed by this user !");
        }
    }

    /**
     * Permet de supprimer un utilisateur et tous ses fichiers de l'annuaire
     * @param userToRemove Utilisateur à supprimer
     */
    public void removeUser(User userToRemove) throws IllegalArgumentException
    {
        Utils.throwExceptionIfNull("User should not be null", userToRemove);

        // Maj de userFiles
        this.userFiles.remove(userToRemove);

        this.filesUser.forEach((file, sources) -> {
            sources.removeIf(userIdentity -> userIdentity.equals(userToRemove));
        });

        // enlève les fichiers qui n'ont plus aucune source
        this.filesUser.entrySet().removeIf(entry -> entry.getValue().isEmpty());
    }




    /**
     * Permet de récupérer les fichiers proposés par un utilisateur
     * @param user Utilisateur
     * @return Vector contenant les fichiers proposés par l'utilisateur passé en paramètre
     * @throws NullPointerException Exception levée si l'utilisateur ne propose aucun fichier
     */
    public Vector<FileHandler> getFilesProposedByUser(User user) throws NullPointerException
    {
        // Est ce que l'utilisateur propose des fichiers ?
        Utils.throwExceptionIfNull("User should not be null", user);
        return this.userFiles.get(user);
    }

    /**
     * Permet de récupérer les utilisateurs proposant un fichier
     * @param file Fichier
     * @return Vector contenant les utilisateurs proposant ce fichier
     * @throws NullPointerException Exception levée si le fichier n'est proposé par aucun utilisateur
     */
    public Vector<UserIdentity> getUsersThatProposeFile(FileHandler file) throws NullPointerException
    {
        Utils.throwExceptionIfNull("File not available", filesUser.get(file));
        return this.filesUser.get(file);
    }

    /**
     * Permet de récupérer tous les fichiers proposés actuellement
     * @return Un Set des fichiers proposés.
     */
    public Set<FileHandler> getProposedFiles()
    {
        return this.filesUser.keySet();
    }

    public void updateSourcesAfterUserModification(UserIdentity oldUserIdentity, UserIdentity newUserIdentity)
    {
        System.out.println("pass, ne devrait pas");
        // MAJ de l'UserIdentity en tant que source dans userFiles
        this.userFiles.put(newUserIdentity, this.userFiles.remove(oldUserIdentity));
        // MAJ de l'UserIdentity en tant que source dans filesUser
        for (Vector<UserIdentity> sources : this.filesUser.values()) {
            int i = 0;
            boolean foundSource = false;
            UserIdentity currentSource = null;
            while(i < sources.size() && !foundSource)
            {
                currentSource = sources.get(i);
                if(currentSource==oldUserIdentity)
                {
                    foundSource = true;
                    currentSource.setFirstName(newUserIdentity.getFirstName());
                    currentSource.setLastName(newUserIdentity.getLastName());
                    currentSource.setAge(newUserIdentity.getAge());
                }
                i = i + 1;
            }
        }
    }

    /**
     * Remplace un fichier proposé par sa version modifiée
     * @param modifiedFile Version modifiée d'un fichier
     */
    /*
    public void updateFilesAfterModification(FileHandler modifiedFile) throws DataException
    {
        if (modifiedFile==null)
        {
            throw new DataException("Modified file is null");
        }
        System.out.println("Le fichier à remplacer contient " + modifiedFile.getRatings().size());

        // On recherche si le fichier est proposé
        int found = 0;
        for (FileHandler proposedFileToModify : this.getProposedFiles())
        {
            if(proposedFileToModify.getHash().equals(modifiedFile.getHash()))
            {
                found = 1;
                // MODIFICATION DU FICHIER DANS FILESUSER
                proposedFileToModify.setDesc(modifiedFile.getDesc());

                Iterator oldRatings = proposedFileToModify.getRatings().iterator();
                while(oldRatings.hasNext())
                {
                    proposedFileToModify.removeRating((Rating)oldRatings.next());
                }

                Iterator newRatings = modifiedFile.getRatings().iterator();
                while (newRatings.hasNext())
                {
                    Rating newRating = (Rating) newRatings.next();
                    proposedFileToModify.addRating(newRating);
                }

                // modification des commentaires
                Iterator oldComments = proposedFileToModify.getComments().iterator();
                while(oldComments.hasNext())
                {
                    proposedFileToModify.removeComment((Comment)oldComments.next());
                }

                Iterator newComments = modifiedFile.getComments().iterator();
                while (newComments.hasNext())
                {
                    Comment newComment = (Comment) newComments.next();
                    proposedFileToModify.addComment(newComment);
                }

                Iterator oldTags = proposedFileToModify.getTags().iterator();
                while(oldTags.hasNext())
                {
                    proposedFileToModify.removeTag((Tag)oldTags.next());
                }

                Iterator newTags = modifiedFile.getTags().iterator();
                while (newTags.hasNext())
                {
                    Tag newTag = (Tag) newTags.next();
                    proposedFileToModify.addTag(newTag);
                }


                // MODIFICATION DU FICHIER DANS USERFILES
                for (UserIdentity userThatProposesFile : this.getUsersThatProposeFile(proposedFileToModify))
                {
                    System.out.println("We found users that do propose the file");
                    Vector<FileHandlerInfos> proposedFiles = this.getFilesProposedByUser(userThatProposesFile);
                    for (FileHandlerInfos fileToModify : proposedFiles)
                    {
                        if(fileToModify.getHash().equals(modifiedFile.getHash())) {
                            System.out.println("We found the file among the others that the users propose");
                            // modification de la description
                            fileToModify.setDesc(modifiedFile.getDesc());

                            // modification des ratings. Loin d'être optimisé
                            Iterator oldRatingsUserFile = proposedFileToModify.getRatings().iterator();
                            while (oldRatingsUserFile.hasNext())
                            {
                                fileToModify.removeRating((Rating) oldRatingsUserFile.next());
                            }
                            Iterator newRatingsUserFile = proposedFileToModify.getRatings().iterator();
                            while (newRatingsUserFile.hasNext())
                            {
                                fileToModify.addRating((Rating)newRatingsUserFile.next());
                            }

                            Iterator oldCommentsUserFile = proposedFileToModify.getComments().iterator();
                            while (oldCommentsUserFile.hasNext())
                            {
                                fileToModify.removeComment((Comment) oldCommentsUserFile.next());
                            }
                            Iterator newCommentsUserFile = proposedFileToModify.getComments().iterator();
                            while (newCommentsUserFile.hasNext())
                            {
                                fileToModify.addComment((Comment)newCommentsUserFile.next());
                            }

                            Iterator oldTagsUserFile = proposedFileToModify.getTags().iterator();
                            while (oldTagsUserFile.hasNext())
                            {
                                fileToModify.removeTag((Tag) oldTagsUserFile.next());
                            }
                            Iterator newTagsUserFile = proposedFileToModify.getTags().iterator();
                            while (newTagsUserFile.hasNext())
                            {
                                fileToModify.addTag((Tag)newTagsUserFile.next());
                            }
                        }
                    }
                }
            }
        }
        if (found==0)
        {
            throw new DataException("The modified file does not seem proposed by anyone on the network");
        }
    }
    */

    public HashMap<FileHandler, Vector<UserIdentity>> getFilesUser()
    {
        return filesUser;
    }

    public HashMap<UserIdentity, Vector<FileHandler>> getUserFiles()
    {
        return userFiles;
    }

    public UserIdentity getUser(UUID id){
        for (UserIdentity temp :
                this.userFiles.keySet())
        {
            if(temp.getId().equals(id)){
                return temp;
            }
        }
        System.out.println("User n'existe pas dans le repertoire");
        return null;
    }

    public void updateFileInfo(FileHandler updatedFile, UserIdentity user) {
        //user : user responsable de la modif
        //updatedFile : FileHandler contenant les modifications
        // TODO :
    }

    /**
     * Méthode houbi houba pour gagner du temps sur le traitement.
     * Que Dieu me pardonne pour ça, la vérité
     * @param uf Le HashMap qui deviendra l'attribut UserFiles ...
     */
    public void setUserFiles(HashMap<UserIdentity, Vector<FileHandler>> uf)
    {
        System.out.println("Setting user files, size = " + uf.size());
        this.userFiles = uf;
    }
    public void addUsertoUserFiles(UserIdentity user)
    {
        this.userFiles.put(user, new Vector<>());
    }
}
