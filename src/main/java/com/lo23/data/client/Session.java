package com.lo23.data.client;

import com.lo23.common.user.UserAccount;

/**
 * Décrit la session courante ouverte par un utilisateur
 */
class Session
{
    private UserAccount currentUser;

    Session()
    {
        this.currentUser = null;
    }

    UserAccount getCurrentUser()
    {
        return currentUser;
    }

    void setCurrentUser(UserAccount currentUser)
    {
        this.currentUser = currentUser;
    }
}
