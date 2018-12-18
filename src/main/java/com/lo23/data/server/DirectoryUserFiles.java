package com.lo23.data.server;

import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.user.User;
import com.lo23.common.user.UserIdentity;
import com.lo23.data.Utils;

import java.util.*;

/**
 * Annuaire permettant de gérer les fichiers proposés par les utilisateurs.
 */
public class DirectoryUserFiles
{
    @Override
    public String toString() {
        return "DirectoryUserFiles{" +
                "userFiles=" + userFiles.size() +
                ", filesUser=" + filesUser.size() +
                '}';
    }

    /**
     * Map contenant les fichiers proposés par les utilisateurs.
     */
    private HashMap<UserIdentity, Vector<FileHandlerInfos>> userFiles;
    /**
     * Map contenant les utilisateurs qui proposent chaque fichier.
     */
    private HashMap<FileHandlerInfos, Vector<UserIdentity>> filesUser;

    /**
     * Constructeur qui instancie les deux HashMap permettant de gérer l'annuaire.
     */
    public DirectoryUserFiles()
    {
        this.userFiles = new HashMap<>();
        this.filesUser = new HashMap<>();
    }

    /**
     * Permet d'ajouter un couple UserIdentity, FileHandlerInfos à l'annuaire des fichiers proposés.
     * @param user Utilisateur
     * @param file Fichier
     * @throws IllegalStateException Levée si fichier déjà proposé par cet utilisateur
     * @throws IllegalArgumentException Levée si les paramètres passés sont mauvais
     */
    public void addProposedFile(UserIdentity user, FileHandlerInfos file) throws IllegalStateException, IllegalArgumentException
    {

        // update userFiles
        Vector<FileHandlerInfos> existentFiles = this.userFiles.getOrDefault(user, new Vector<>());
        existentFiles.add(file);
        this.userFiles.put(user, existentFiles);

        // update filesUser
        Vector<UserIdentity> existentSources = this.filesUser.getOrDefault(file, new Vector<>());
        existentSources.add(user);
        this.filesUser.put(file, existentSources);


//
//
//        // On vérifie que les paramètres passés sont valides, sinon on lève une exception.
//        Utils.throwExceptionIfNull("User should not be null", user);
//        Utils.throwExceptionIfNull("File should not be null", file);
//
//        System.out.println("le user " + user.getLogin() + " a un fichier du nom : " + file.getTitle());
//        // On lève une exception si l'utilisateur propose déjà ce fichier
//        try {
//            if (this.userFiles.get(user).contains(file) && this.filesUser.get(file).contains(user) ){
//                throw new IllegalStateException("This user already propose this file !");
//            }
//        } catch (NullPointerException npe){
//            npe.printStackTrace();
//        }
//
//        // On vérifie si l'utilisateur propose déjà des fichiers, si ce n'est pas le cas on l'ajoute à la map
//        if (this.userFiles.get(user) == null)
//        {
//            System.out.println("L'utilisateur n'avait pas de fichier");
//            Vector<FileHandlerInfos> v = new Vector<>();
//            v.add(file);
//            this.userFiles.put(user, v);
//        }
//        // Sinon on ajoute simplement le fichier
//        else
//        {
//            System.out.println("L'utilisateur possedait des fichiers");
//            this.userFiles.get(user).add(file);
//        }
//
//        // On vérifie si le fichier est déjà proposé, si ce n'est pas le cas on l'ajoute à la map
//        if (this.filesUser.get(file) == null)
//        {
//            Vector<UserIdentity> v = new Vector<>();
//            v.add(user);
//            this.filesUser.put(file, v);
//        }
//        // Sinon on ajoute simplement le fichier
//        else
//        {
//            this.filesUser.get(file).add(user);
//        }
    }


    /**
     * Permet d'enlever un couple UserIdentity FileHandlerInfos de l'annuaire
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
     * @param user Utilisateur à supprimer
     */
    public void removeUser(User user) throws IllegalArgumentException
    {
        Utils.throwExceptionIfNull("User should not be null", user);

//        HashMap<UserIdentity, Vector<FileHandlerInfos>> userFiles;

//        HashMap<FileHandlerInfos, Vector<UserIdentity>> filesUser;

        UserIdentity[] sourceToRemove = new UserIdentity[1];
        FileHandlerInfos[] sourceConcerned = new FileHandlerInfos[1];


        this.userFiles.remove(user);

        this.filesUser.forEach((file,sources) -> {
            sources.forEach(userIdentity -> {
                if (userIdentity.equals(user)) {
                    System.out.println("USER REMOVED SUCCESSFULLY");
                    sourceToRemove[0] = userIdentity;
                    sourceConcerned[0] = file;
                }
            });
        });

        boolean[] removeEntry = new boolean[1];
        removeEntry[0] = false;

        this.filesUser.forEach((file,sources) -> {
            if (file.equals(sourceConcerned[0])) {
                System.out.println("FILE REMOVED SUCCESSFULLY");
                sources.remove(sourceToRemove[0]);
            }
            if (sources.size()==0)
                removeEntry[0] = true;
        });

        if (removeEntry[0]) {
            //BUG ICI
            System.out.println("PASS");

            this.filesUser.remove(sourceConcerned);
        }

//
//
//
//        if(this.userFiles.get(user)!=null) {
//            // Suppression de tous les fichiers de l'utilisateur
//            Vector<FileHandlerInfos> tmp = new Vector<>();
//            tmp.addAll(this.getFilesProposedByUser(user));
//            FileHandlerInfos f;
//
//            for (Iterator<FileHandlerInfos> i = tmp.iterator(); i.hasNext(); ) {
//                f = i.next();
//                this.removeProposedFile(user, f);
//            }
//        }
    }

    /**
     * Permet de récupérer les fichiers proposés par un utilisateur
     * @param user Utilisateur
     * @return Vector contenant les fichiers proposés par l'utilisateur passé en paramètre
     * @throws NullPointerException Exception levée si l'utilisateur ne propose aucun fichier
     */
    public Vector<FileHandlerInfos> getFilesProposedByUser(User user) throws NullPointerException
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
    public Set<FileHandlerInfos> getProposedFiles()
    {
        return this.filesUser.keySet();
    }

    public void updateSourcesAfterUserModification(UserIdentity oldUserIdentity, UserIdentity newUserIdentity)
    {
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

    public HashMap<FileHandlerInfos, Vector<UserIdentity>> getFilesUser()
    {
        return filesUser;
    }

    public HashMap<UserIdentity, Vector<FileHandlerInfos>> getUserFiles()
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

    public void updateFileInfo(FileHandlerInfos updatedFile, UserIdentity user) {
        //user : user responsable de la modif
        //updatedFile : FileHandlerInfos contenant les modifications
        // TODO :
    }

    /**
     * Méthode houbi houba pour gagner du temps sur le traitement.
     * Que Dieu me pardonne pour ça, la vérité
     * @param uf Le HashMap qui deviendra l'attribut UserFiles ...
     */
    public void setUserFiles(HashMap<UserIdentity, Vector<FileHandlerInfos>> uf)
    {
        this.userFiles = uf;
    }
    public void addUsertoUserFiles(UserIdentity user)
    {
        this.userFiles.put(user, new Vector<>());
    }
}
