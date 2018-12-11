package com.lo23.common.user;

/**
 * Classe qui définie un utilisateur avec ses statistiques de téléchargements et téléversements.
 */
public class UserStats extends UserIdentity
{
    private static final long serialVersionUID = 100000000001L;

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
        this.nbFilesUploaded = 0;
        this.nbFilesDownloaded = 0;
    }

    public UserStats ()
    {
        super();
        this.nbFilesUploaded = 0;
        this.nbFilesDownloaded = 0;
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
     * Méthode qui décrémente le nombre de fichiers téléversés
     */
    public void decrementNbFilesUploaded() throws IllegalStateException
    {
        if(this.nbFilesUploaded  <= 0)
        {
            throw new IllegalStateException("Number of uploaded files can't be negative");
        }
        this.nbFilesUploaded--;
    }


    /**
     * Méthode qui incrémente le nombre de fichiers téléchargés
     */
    public void incrementNbFilesDownloaded()
    {
        this.nbFilesDownloaded++;
    }

}
