package com.lo23.common.user;

/**
 * Classe qui définie un utilisateur et ses informations de base.
 */
public class UserIdentity extends User
{

    /**
     * Prénom de l'utilisateur
     */
    private String firstName;

    /**
     * Nom de famille de l'utilisateur.
     */
    private String lastName;

    /**
     * Age de l'utilisateur.
     */
    private int age;


    /**
     * Constructeur de UserIdentity
     *
     * @param login     Login du User
     * @param firstName Prénom de l'utilisateur
     * @param lastName  Nom de famille de l'utilisateur
     * @param age       Age de l'utilisateur
     */
    public UserIdentity(String login, String firstName, String lastName, int age)
    {
        super(login);
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public int getAge()
    {
        return age;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setAge(int age)
    {
        this.age = age;
    }
}
