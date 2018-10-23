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
     * @throws IllegalArgumentException Exception remontée si il ya une erreur dans le nom, prénom ou age de l'utilisateur
     */
    public UserIdentity(String login, String firstName, String lastName, int age) throws IllegalArgumentException
    {
        super(login);
        if(firstName.length()<=0)
        {
            throw new IllegalArgumentException("Firstname should not be an empty String");
        }
        if(lastName.length()<=0)
        {
            throw new IllegalArgumentException("Lastname should not be an empty String");
        }
        if(age<=0)
        {
            throw new IllegalArgumentException("Age should not be negative");
        }
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
