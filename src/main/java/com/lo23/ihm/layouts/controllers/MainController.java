package com.lo23.ihm.layouts.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import com.lo23.common.interfaces.data.DataClientToIhm;
import com.lo23.common.user.User;
import com.lo23.common.user.UserIdentity;
import com.lo23.data.client.DataManagerClient;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainController implements Initializable{

	
	@FXML
    private HBox mainHBox;

    @FXML
    private AnchorPane leftPane;

    @FXML
    private VBox middleVBox;

    @FXML
    private AnchorPane mainTopPane;

    @FXML
    private HBox mainTopHBox;

    @FXML
    private TextField researchTextField;

    @FXML
    private ComboBox<?> chooseResearchBox;

    @FXML
    private TabPane mainTabPane;

    @FXML
    private Tab myFilesTab;

    @FXML
    private AnchorPane myFilesPane;

    @FXML
    private Button addDocumentButton;

    @FXML
    private Tab availableFilesTab;

    @FXML
    private AnchorPane availableFilesPane;

    @FXML
    private Tab downloadsTab;

    @FXML
    private AnchorPane downloadsPane;

    @FXML
    private AnchorPane mainBottomPane;

    @FXML
    private Button serverParametersButton;

    @FXML
    private VBox rightVBox;

    @FXML
    private HBox rightTopHBox;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label onlineUsersLabel;

    @FXML
    private AnchorPane onlineUsersPane;

    @FXML
    private ListView<String> contactsListView;

    @FXML
    private TextField researchUserTextField;

    @FXML
    private Button disconnectButton;

    //gestion fenêtre contacts en ligne
    private List<UserIdentity> connectedUsers = new ArrayList<UserIdentity>();

    private List<String> userList = new ArrayList<String>();

    private ListProperty<String> userListProperty = new SimpleListProperty<String>();

    //pour test
    private UserIdentity user;

	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	    //pour test
        user = new UserIdentity("login", "Prénom", "Nom", 21);
        connectedUsers.add(user);

        refreshContactsWindow();
        binding();
	}

    @FXML
    public void OnRefreshConnectedUsersClicked()
    {
        try
        {
            refreshContactsWindow();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

	@FXML
	public void OnServerParametersButtonClicked(){

    }

    @FXML
    public void OnUpdateUserButtonClicked(){

    }

    @FXML
    public void OnDisconnectButtonClicked(){

    }

    private void binding()
    {
        this.contactsListView.itemsProperty().bind(userListProperty);
    }

    private void refreshContactsWindow()
    {
        //décommenter à l'intégration
        //DataClientToIhm api= DataManagerClient.getInstance().getDataClientToIhmApi();
        //connectedUsers = api.requestConnectedUsers();

        Iterator it = connectedUsers.listIterator();
        UserIdentity currentUser = new UserIdentity();

        while(it.hasNext())
        {
            currentUser = (UserIdentity) it.next();
            userList.add(currentUser.getFirstName() + " " + currentUser.getLastName());
        }

        userListProperty.set(FXCollections.observableArrayList(userList));
    }

}
