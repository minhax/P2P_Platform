package com.lo23.common;

import com.lo23.common.user.User;

import java.io.Serializable;

/**
 * Définit une note attribuée par un utilisateur sur un fichier
 */
public class Rating implements Serializable
{
    /**
     * Serial UID for class serialisation
     */
    private static final long serialVersionUID = 100000000001L;

    /**
     * Valeur numérique de la note
     */
    private int value;
    /**
     * Utilisateur associé
     */
    private User author;

    /**
     * Constructeur de Rating
     * @param value Valeur de la note
     * @param user Utilisateur associé
     */
    public Rating(int value, User user)
    {
        this.value = value;
        this.author = user;
    }

    public int getValue()
    {
        return value;
    }

    public User getAuthor()
    {
        return author;
    }
}
