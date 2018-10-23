package com.lo23.common.filehandler;

/**
 * Définit un handler de fichier qui contient les statistiques du fichier en plus des autres métadonnées.
 */
public class FileHandlerStats extends FileHandlerInfos
{
    /**
     * Nombre de téléchargements du fichier
     */
    private int nbDownloads;

    /**
     * Constructeur du FileHandlerStats
     * @param hash Hash du fichier
     * @param title Nom du fichier
     * @param size Taille du fichier en octets
     * @param type Type du fichier
     * @param nbBlocks Nombre de blocks du fichier découpé
     * @param desc Description du fichier
     */
    public FileHandlerStats (String hash, String title, int size, String type, int nbBlocks, String desc)
    {
        super(hash, title, size, type, nbBlocks, desc);
        nbDownloads = 0;
    }

    /**
     *
     * @return Nombre de téléchargements du fichier
     */
    public int getNbDownloads ()
    {
        return nbDownloads;
    }

    /**
     * Incrémente le compteur de téléchargements du fichier.
     * @return Nouvelle valeur du compteur de téléchargements.
     */
    public int incNbDownloads () {
        return ++nbDownloads;
    }
}
