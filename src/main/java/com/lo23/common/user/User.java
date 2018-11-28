package com.lo23.common.user;

import java.io.Serializable;
import java.util.UUID;

/**
 * Définit un utilisateur selon son UUID et son login.
 */
public class User implements Serializable
{
    /**
     * Serial UID for class serialisation
     */
    private static final long serialVersionUID = 100000000001L;

    /**
     * Identifiant unique de l'utilisateur.
     */
    private UUID id;

    /**
     * Login utilisé lors de la connexion de l'utilisateur.
     */
    private String login;


    /**
     * Constructeur de User
     * @param login Login du User
     * @throws IllegalArgumentException Exception remontée si le login comporte une erreur
     */
    public User(String login) throws IllegalArgumentException
    {
        if(login.length()<=0)
        {
            throw new IllegalArgumentException("Login should not be an empty String");
        }
        this.login = login;
        this.id = UUID.randomUUID();
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof User))
        {
            return false;
        } else
        {
            User u = (User) o;
            if (u.getId().equals(this.getId()))
            {
                return true;
            } else
            {
                return false;
            }
        }
    }

    public User ()
    {
        this.login = "";
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }
}
