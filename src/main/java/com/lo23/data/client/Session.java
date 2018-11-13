package com.lo23.data.client;

import com.lo23.common.user.User;

/**
 * Décrit la session courante ouverte par un utilisateur
 */
public class Session
{
    private User currentUser;

    public Session()
    {

    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

}
