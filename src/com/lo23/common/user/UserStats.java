package com.lo23.common.user;

/**
 * Classe qui définie un utilisateur avec ses statistiques de téléchargements et téléversements.
 */
public class UserStats extends UserIdentity
{
    /**
     * Nombre de fichiers téléversés
     */
    private int nbFilesUploaded;

    /**
     * Nombre de fichiers téléchargés
     */
    private int nbFilesDownloaded;

    /**
     * Constructeur de UserStats
     *
     * @param login     Login du User
     * @param firstName Prénom de l'utilisateur
     * @param lastName  Nom de famille de l'utilisateur
     * @param age       Age de l'utilisateur
     */
    public UserStats(String login, String firstName, String lastName, int age)
    {
        super(login, firstName, lastName, age);
    }

    public int getNbFilesUploaded()
    {
        return nbFilesUploaded;
    }

    public int getNbFilesDownloaded()
    {
        return nbFilesDownloaded;
    }

    /**
     * Méthode qui incrémente le nombre de fichiers téléversés
     */
    public void incrementNbFilesUploaded()
    {
        this.nbFilesUploaded++;
    }

    /**
     * Méthode qui incrémente le nombre de fichiers téléchargés
     */
    public void incrementNbFilesDownloaded()
    {
        this.nbFilesDownloaded++;
    }

}
