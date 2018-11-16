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

    public UserAccount getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserAccount currentUser) {
        this.currentUser = currentUser;
    }
}
