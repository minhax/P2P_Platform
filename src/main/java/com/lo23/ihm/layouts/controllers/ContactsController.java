package com.lo23.ihm.layouts.controllers;

import javafx.beans.property.ListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ContactsController {

    @FXML
    private AnchorPane contactsPane;

    @FXML
    private ListView<String> contactsListView;

    @FXML
    private Button refreshConnectedUsersButton;

    private List<String> userList;

    private ListProperty<String> userListProperty ;


    /**
     * Initialise les utilisateurs connectés
     * @param location URL
     * @param resources ResourceBundle
     */
    public void initialize(URL location, ResourceBundle resources)
    {
        //refreshConnectedUsers();
        binding();
        userListProperty.set(FXCollections.observableArrayList(userList));
        // TODO Auto-generated method stub

    }

    /**
     * Actualisation des utilisateurs connectés via un bouton
     */
    @FXML
    public void OnRefreshConnectedUsersClicked()
    {
        try
        {
            //refreshConnectedUsers();
            //userListProperty.set(FXCollections.observableArrayList(userList));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    /**
     * Binding des éléments du contrôleur aux éléments du modèle
     */
    private void binding()
    {
        this.contactsListView.itemsProperty().bind(userListProperty);
    }

}
