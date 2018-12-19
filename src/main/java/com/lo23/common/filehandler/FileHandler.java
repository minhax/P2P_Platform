package com.lo23.common.filehandler;

import java.io.Serializable;

/**
 * Définit un descripteur de fichier, qui représente
 * le fichier et contient ses métadonnées.
 */
public class FileHandler implements Serializable
{
    /**
     * Serial UID for class serialisation
     */
    private static final long serialVersionUID = 100000000005L;

    /**
     * Identifiant unique du fichier
     */
    private String hash;
    /**
     * Nom du fichier à afficher dans l'IHM
     */
    private String title;
    /**
     * Taille en octets du fichier
     */
    private long size;
    /**
     * Type MIME du fichier (pas forcément identique à l'extension)
     */
    private String type;
    /**
     * Nombre de blocks générés par le découpage du fichier
     */
    private int nbBlocks;

    /**
     * Constructeur du FileHandler
     * @param hash Hash du fichier
     * @param title Nom du fichier
     * @param size Taille du fichier en octets
     * @param type Type du fichier (peut être différent de l'extension)
     * @param nbBlocks nombre de blocks générés par le découpage du fichier
     */
    public FileHandler (String hash, String title, long size, String type, int nbBlocks)
            throws IllegalArgumentException
    {
        if (hash.length() == 0)
            throw new IllegalArgumentException("hash can't be empty !");
        if (size <= 0)
            throw new IllegalArgumentException("size can't be less than 0 !");
        if (nbBlocks <= 0)
            throw new IllegalArgumentException("nbBlocks can't be less than 0 !");

        this.hash = hash;
        this.title = title;
        this.size = size;
        this.type = type;
        this.nbBlocks = nbBlocks;
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof FileHandler))
        {
            return false;
        } else
        {
            FileHandler u = (FileHandler) o;
            return u.getHash().equals(this.getHash());
        }
    }

    @Override
    public int hashCode() {
        return this.getHash().hashCode();
    }

    /**
     *
     * @return Hash du fichier
     */
    public String getHash ()
    {
        return hash;
    }

    /**
     *
     * @return Nom du fichier
     */
    public String getTitle ()
    {
        return title;
    }

    /**
     *
     * @param title Nouveau nom du fichier
     */
    public void setTitle (String title)
    {
        this.title = title;
    }

    /**
     *
     * @return Taille du fichier en octets
     */
    public long getSize ()
    {
        return size;
    }

    /**
     *
     * @return Type du fichier
     */
    public String getType ()
    {
        return type;
    }

    /**
     *
     * @return Nombre de blocs après découpage du fichier
     */
    public int getNbBlocks ()
    {
        return nbBlocks;
    }
}
