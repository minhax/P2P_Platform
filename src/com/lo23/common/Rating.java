package com.lo23.common;

import com.lo23.common.user.User;

/**
 * Définit une note attribuée par un utilisateur sur un fichier
 */
public class Rating {
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
