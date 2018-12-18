package com.lo23.ihm.layouts.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CreateAccountModel {

    private StringProperty login, password, firstName, lastName,age;

    /**
     * Instancie la classe CreateAccountModel
     */
    public CreateAccountModel()
    {
        this.login=new SimpleStringProperty();
        this.password=new SimpleStringProperty();
        this.firstName=new SimpleStringProperty();
        this.lastName=new SimpleStringProperty();
        this.age=new SimpleStringProperty();
    }


    public String getLogin()
    {
        return login.get();
    }

    public StringProperty loginProperty()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login.set(login);
    }

    public String getPassword()
    {
        return password.get();
    }

    public StringProperty passwordProperty()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password.set(password);
    }

    public String getFirstName()
    {
        return firstName.get();
    }

    public StringProperty firstNameProperty()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName.set(firstName);
    }

    public String getLastName()
    {
        return lastName.get();
    }

    public StringProperty lastNameProperty()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName.set(lastName);
    }

    public String getAge()
    {
        return age.get();
    }

    public StringProperty ageProperty()
    {
        return age;
    }

    public void setAge(String age)
    {
        this.age.set(age);
    }
}
