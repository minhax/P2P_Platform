package com.lo23.common.user;

import java.util.UUID;

/**
 * Définit un utilisateur selon son UUID et son login.
 */
public class User {
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

    public UUID getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }
}
