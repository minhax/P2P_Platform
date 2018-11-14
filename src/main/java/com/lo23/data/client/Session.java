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

<<<<<<< HEAD
    UserAccount getCurrentUser()
    {
        return currentUser;
    }

    void setCurrentUser(UserAccount currentUser)
    {
=======
    public UserAccount getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserAccount currentUser) {
>>>>>>> c89ac062ae1cc657a331625997576b6a71e52165
        this.currentUser = currentUser;
    }
}
