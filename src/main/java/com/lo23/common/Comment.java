package com.lo23.common;

import com.lo23.common.user.User;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Définit un commentaire fait par un utilisateur sur un fichier
 */
public class Comment implements Serializable
{
    /**
     * Serial UID for class serialisation
     */
    private static final long serialVersionUID = 100000000002L;

    /**
     * Corps du commentaire
     */
    private String text;
    /**
     * Date de publication du commentaire
     */
    private LocalDateTime date;
    /**
     * Auteur du commentaire
     */
    private User author;

    /**
     * Constructeur de Comment
     * @param text Corps du commentaire
     * @param user Auteur du commentaire
     */
    public Comment(String text, User user)
    {
        this.text = text;
        this.author = user;
        this.date = LocalDateTime.now();
    }

    public String getText()
    {
        return text;
    }

    public LocalDateTime getDate()
    {
        return date;
    }

    public User getAuthor()
    {
        return author;
    }
}
