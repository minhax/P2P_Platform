package com.lo23.common;

import java.io.Serializable;

/**
 * Définit un tag sur un fichier
 */
public class Tag implements Serializable
{
    /**
     * Serial UID for class serialisation
     */
    private static final long serialVersionUID = 100000000004L;

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
