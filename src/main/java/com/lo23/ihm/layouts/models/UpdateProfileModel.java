package com.lo23.ihm.layouts.models;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UpdateProfileModel {
	private StringProperty passwordUpdate, nameUpdate, lastnameUpdate,ageUpdate;
	
	public UpdateProfileModel()
    {
        this.passwordUpdate=new SimpleStringProperty();
        this.nameUpdate=new SimpleStringProperty();
        this.lastnameUpdate=new SimpleStringProperty();
        this.ageUpdate=new SimpleStringProperty();
    }

    public String getPasswordUpdate() {
        return passwordUpdate.get();
    }

    public StringProperty passwordUpdateProperty() {
        return passwordUpdate;
    }

    public void setPasswordUpdate(String password) {
        this.passwordUpdate.set(password);
    }

    public String getNameUpdate() {
        return nameUpdate.get();
    }

    public StringProperty nameUpdateProperty() {
        return nameUpdate;
    }

    public void setNameUpdate(String firstName) {
        this.nameUpdate.set(firstName);
    }

    public String getLastnameUpdate() {
        return lastnameUpdate.get();
    }

    public StringProperty lastnameUpdateProperty() {
        return lastnameUpdate;
    }

    public void setLastnameUpdate(String lastName) {
        this.lastnameUpdate.set(lastName);
    }

    public String getAge() {
        return ageUpdate.get();
    }

    public StringProperty ageUpdateProperty() {
        return ageUpdate;
    }

    public void setAgeUpdate(String age) {
        this.ageUpdate.set(age);
    }
}
