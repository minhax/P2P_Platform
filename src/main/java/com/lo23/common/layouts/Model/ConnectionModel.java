package com.lo23.common.layouts.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class ConnectionModel {


    private StringProperty user, password, server, port;

    public ConnectionModel()
    {
        this.user=new SimpleStringProperty();
        this.password=new SimpleStringProperty();
        this.server=new SimpleStringProperty();
        this.port=new SimpleStringProperty();
    }

    public String getUser() {
        return user.get();
    }

    public StringProperty userProperty() {
        return user;
    }

    public void setUser(String user) {
        this.user.set(user);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getServer() {
        return server.get();
    }

    public StringProperty serverProperty() {
        return server;
    }

    public void setServer(String server) {
        this.server.set(server);
    }

    public String getPort() {
        return port.get();
    }

    public StringProperty portProperty() {
        return port;
    }

    public void setPort(String port) {
        this.port.set(port);
    }
}


