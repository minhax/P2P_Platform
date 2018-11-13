package com.lo23.common.user;

import com.lo23.common.filehandler.FileHandler;

import java.io.Serializable;
import java.util.Vector;

/**
 * Classe qui définie un compte utilisateur avec toutes ses informations utiles.
 */
public class UserAccount extends UserStats
{
    /**
     * Mot de passe du compte utilisateur
     */
    private String password;

    /**
     * Liste des fichiers proposés par l'utilisateur
     */
    private Vector<FileHandler> proposedFiles;

    /**
     * Adresse IP du serveur utilisé lors de la dernière connection
     */
    private String lastConnectionServerIP;

    /**
     * Constructeur de compte utilisateur
     *
     * @param login     Login de l'utilisateur
     * @param firstName Prénom de l'utilisateur
     * @param lastName  Nom de famille de l'utilisateur
     * @param age       Age de l'utilisateur
     * @param password  Mot de passer du compte utilisateur
     * @throws IllegalArgumentException Exception remontée si le mot de passe n'est pas conforme
     */
    public UserAccount(String login, String firstName, String lastName, int age, String password) throws IllegalArgumentException
    {
        super(login, firstName, lastName, age);
        if (password.length() <= 0) {
            throw new IllegalArgumentException("Password should not be an empty String");
        }
        this.password = password;
        this.proposedFiles = new Vector<>();
    }

    public UserAccount ()
    {
        super();
        this.password = "";
        this.proposedFiles = new Vector<>();
    }

    /**
     * Permet de vérifier le mot de passe.
     *
     * @param submittedPassword Mot de passe à vérifier
     * @return Retourne true si le mot de passe soumis est le bon
     */
    public boolean checkPassword(String submittedPassword) {
        return this.password.equals(submittedPassword);
    }

    /**
     * Permet d'ajouter un fichier proposé
     * @param file Fichier à ajouter
     */
    public void addProposedFile(FileHandler file)
    {
        this.proposedFiles.add(file);
        super.incrementNbFilesUploaded();
    }

    /**
     * Supprime un fichier proposé du compte
     * @param file Fichier à supprimer
     */
    public void removeProposedFile(FileHandler file)
    {
        this.proposedFiles.remove(file);
        super.decrementNbFilesUploaded();
    }

    /**
     * Retourne la liste des fichiers proposés par l'utilisateur
     * @return Liste des fichiers proposés
     */
    public Vector<FileHandler> getProposedFiles()
    {
        return this.proposedFiles;
    }

    public String getLastConnectionServerIP()
    {
        return lastConnectionServerIP;
    }

    public void setLastConnectionServerIP(String lastConnectionServerIP) {
        this.lastConnectionServerIP = lastConnectionServerIP;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
