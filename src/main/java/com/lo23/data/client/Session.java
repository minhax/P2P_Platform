package com.lo23.data.client;

import com.lo23.common.user.UserAccount;

/**
 * Décrit la session courante ouverte par un utilisateur
 */
public class Session
{
    private UserAccount currentUser;

    public Session()
    {

    }

    public UserAccount getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserAccount currentUser) {
        this.currentUser = currentUser;
    }

}
