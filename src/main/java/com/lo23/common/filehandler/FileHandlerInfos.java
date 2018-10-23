package com.lo23.common.filehandler;

import com.lo23.common.Comment;
import com.lo23.common.Rating;
import com.lo23.common.Tag;

import java.util.Vector;

/**
 * Définit un descripteur de fichier qui contient les tags,
 * notes et commentaires en plus des métadonnées de base.
 */
public class FileHandlerInfos extends FileHandler
{
    /**
     * Description du fichier
     */
    private String desc;
    /**
     * Liste des tags du fichier
     */
    private Vector<Tag> tags;
    /**
     * Liste des notes du fichier
     */
    private Vector<Rating> ratings;
    /**
     * Liste des commentaires du fichier
     */
    private Vector<Comment> comments;

    /**
     * Constructeur du FileHandlerInfos
     * @param hash Hash du fichier
     * @param title Nom du fichier
     * @param size Taille du fichier en octets
     * @param type Type du fichier
     * @param nbBlocks Nombre de blocks du fichier découpé
     * @param desc Description du fichier
     */
    public FileHandlerInfos (String hash, String title, int size, String type, int nbBlocks, String desc)
    {
        super(hash, title, size, type, nbBlocks);
        this.desc = desc;
        this.tags = new Vector<>();
        this.ratings = new Vector<>();
        this.comments = new Vector<>();
    }

    /**
     *
     * @return Description du fichier
     */
    public String getDesc ()
    {
        return desc;
    }

    /**
     *
     * @param desc Nouvelle description du fichier
     */
    public void setDesc (String desc)
    {
        this.desc = desc;
    }

    /**
     * Récupère une copie de la liste des tags du fichier
     * @return Liste de tags
     */
    public Vector<Tag> getTags ()
    {
        return new Vector<>(tags);
    }

    /**
     * Ajoute un tag au fichier
     * @param t Tag à ajouter
     */
    public void addTag (Tag t)
    {
        tags.add(t);
    }

    /**
     * Retire un tag au fichier
     * @param t Tag à retirer
     * @return true si l'élément était bien présent dans la liste de tags
     */
    public boolean removeTag (Tag t)
    {
        return tags.remove(t);
    }

    /**
     * Récupère une copie de la liste des notes du fichier
     * @return Liste de notes
     */
    public Vector<Rating> getRatings ()
    {
        return new Vector<>(ratings);
    }

    /**
     * Ajoute une note au fichier
     * @param r Note à ajouter
     */
    public void addRating (Rating r)
    {
        ratings.add(r);
    }

    /**
     * Retire une note au fichier
     * @param r Note à retirer
     * @return true si l'élément était bien présent dans la liste de notes
     */
    public boolean removeRating (Rating r)
    {
        return ratings.remove(r);
    }

    /**
     * Récupère une copie de la liste des commentaire du fichier
     * @return Liste de commentaires
     */
    public Vector<Comment> getComments ()
    {
        return new Vector<>(comments);
    }

    /**
     * Ajoute un commentaire au fichier
     * @param c Commentaire à ajouter
     */
    public void addComment (Comment c)
    {
        comments.add(c);
    }

    /**
     * Retire un commentaire au fichier
     * @param c Commentaire à retirer
     * @return true si l'élément était bien présent dans la liste de commentaires
     */
    public boolean removeComment (Comment c)
    {
        return comments.remove(c);
    }
}
