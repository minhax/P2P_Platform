package com.lo23.common.user;

/**
 * Classe qui définie un compte utilisateur avec toutes ses informations utiles.
 */
public class UserAccount extends UserStats
{
    /**
     * Mot de passe du compte utilisateur
     */
    private String password;

    /**
     * Constructeur de compte utilisateur
     *
     * @param login     Login de l'utilisateur
     * @param firstName Prénom de l'utilisateur
     * @param lastName  Nom de famille de l'utilisateur
     * @param age       Age de l'utilisateur
     * @param password
     * @throws IllegalArgumentException Exception remontée si le mot de passe n'est pas conforme
     */
    public UserAccount(String login, String firstName, String lastName, int age, String password) throws IllegalArgumentException
    {
        super(login, firstName, lastName, age);
        if(password.length()<=0)
        {
            throw new IllegalArgumentException("Password should not be an empty String");
        }
        this.password = password;
    }

    /**
     * Permet de vérifier le mot de passe.
     *
     * @param submittedPassword Mot de passe à vérifier
     * @return Retourne true si le mot de passe soumis est le bon
     */
    public boolean checkPassword(String submittedPassword)
    {
        return this.password.equals(submittedPassword);
    }
}
