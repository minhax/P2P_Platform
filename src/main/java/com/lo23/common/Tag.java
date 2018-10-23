package com.lo23.common;

/**
 * Définit un tag sur un fichier
 */
public class Tag {
    /**
     * Mot-clé
     */
    private String label;

    /**
     * Constructeur de Tag
     * @param label Mot-clé
     */
    public Tag (String label)
    {
        this.label = label;
    }

    public String getLabel()
    {
        return label;
    }
}
