package com.lo23.ihm.layouts.controllers;

import java.net.URL;
import java.util.*;

import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.interfaces.data.DataClientToIhm;
import com.lo23.data.client.DataManagerClient;
import com.lo23.ihm.layouts.models.AvailableFilesListCell;
import com.lo23.ihm.layouts.models.DownloadingFilesListCell;
import com.lo23.ihm.layouts.models.MyFilesListCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.lo23.common.user.UserIdentity;
import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class MainController implements Initializable {
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

    @FXML
    private ListView listViewAvailableFiles;

    @FXML
    private ListView listViewMyFiles;

    @FXML
    private ListView listViewDownloading;
    
    @FXML
    private Label fileInfosName;
    
    @FXML
    private Label fileInfosType;
    
    @FXML
    private Label fileInfosDescription;
    
    @FXML
    private Label fileInfosSize;

    //gestion fenÃªtre contacts en ligne
    private List<UserIdentity> connectedUsers = new ArrayList<UserIdentity>();

    private List<String> userList = new ArrayList<String>();

    private ListProperty<String> userListProperty = new SimpleListProperty<String>();

    //pour test
    private UserIdentity user;

    private Timer refreshTimer;
    private int period = 10000;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<FileHandler> data = FXCollections.observableArrayList();
        data.addAll(new FileHandler("hash1", "document 1", 15152, "document", 16),
                new FileHandler("hash2", "document 2", 1554, "document2", 32),
                new FileHandler("hash3", "document 3", 15152, "document3", 64));

        listViewAvailableFiles.setCellFactory(new Callback<ListView<FileHandler>, ListCell<FileHandler>>() {
            @Override
            public ListCell<FileHandler> call(ListView<FileHandler> listView) {
                return new AvailableFilesListCell();
            }
        });
        
        
        listViewMyFiles.setCellFactory(new Callback<ListView<FileHandler>, ListCell<FileHandler>>() {
            @Override
            public ListCell<FileHandler> call(ListView<FileHandler> listView) {
                return new MyFilesListCell();
            }
        });
        listViewDownloading.setCellFactory(new Callback<ListView<FileHandler>, ListCell<FileHandler>>() {
            @Override
            public ListCell<FileHandler> call(ListView<FileHandler> listView) {
                return new DownloadingFilesListCell();
            }
        });

        listViewAvailableFiles.setItems(data);
        listViewMyFiles.setItems(data);
        listViewDownloading.setItems(data);
        

        //pour test
        user = new UserIdentity("login", "PrÃ©nom", "Nom", 21);
        connectedUsers.add(user);


        refreshTimer = new Timer();

        refreshTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                Platform.runLater(() -> refreshContactsWindow());
            }
        }, 0, period);

        //refreshContactsWindow();
        binding();
    }
    
    
/***  
 * Impossible d'accéder aux caractéristiques des items des listView. Il faut trouver une solution sinon pas possible d'afficher à gauche.
 * Peut-être créer la listViex différemment.
 * Les 3 méthodes ci-dessous permettent de set les labels de la partie gauche de main lors du click sur un fichier. Peut être moyen d'éviter
 * la redondance des 3 méthodes mais pas trouvé.   
***/
    
    @FXML
    void handleMouseClkickAvailableFiles(MouseEvent event) {
    
//	    fileInfosName.setText(listViewAvailableFiles.getSelectionModel().getSelectedItem());
//	    fileInfosType.setText(listViewAvailableFiles.getSelectionModel().getSelectedItem());
//	    fileInfosDescription.setText(listViewAvailableFiles.getSelectionModel().getSelectedItem());
//	    fileInfosSize.setText(listViewAvailableFiles.getSelectionModel().getSelectedItem());
	    
    }

    @FXML
    void handleMouseClkickDownloadingFiles(MouseEvent event) {
//	    fileInfosName.setText(listViewAvailableFiles.getSelectionModel().getSelectedItem());
//	    fileInfosType.setText(listViewAvailableFiles.getSelectionModel().getSelectedItem());
//	    fileInfosDescription.setText(listViewAvailableFiles.getSelectionModel().getSelectedItem());
//	    fileInfosSize.setText(listViewAvailableFiles.getSelectionModel().getSelectedItem());
    }

    @FXML
    void handleMouseClkickMyFiles(MouseEvent event) {
//	    fileInfosName.setText(listViewAvailableFiles.getSelectionModel().getSelectedItem());
//	    fileInfosType.setText(listViewAvailableFiles.getSelectionModel().getSelectedItem());
//	    fileInfosDescription.setText(listViewAvailableFiles.getSelectionModel().getSelectedItem());
//	    fileInfosSize.setText(listViewAvailableFiles.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void OnRefreshConnectedUsersClicked() {
        try {
            refreshContactsWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void OnServerParametersButtonClicked() {
    }


    @FXML
    public void OnDisconnectButtonClicked() { //TODO renvoyer sur la fenetre de connection --> V4
       DataManagerClient.getInstance().getDataClientToIhmApi().requestLogout();
        ((Stage) this.mainHBox.getScene().getWindow()).close();
    }

    private void binding() {
        this.contactsListView.itemsProperty().bind(userListProperty);
    }

    private void refreshContactsWindow() {
        //dÃ©commenter Ã  l'intÃ©gration
        DataClientToIhm api= DataManagerClient.getInstance().getDataClientToIhmApi();
        connectedUsers = api.requestConnectedUsers();
        if(connectedUsers!=null) {

            Iterator it = connectedUsers.listIterator();
            UserIdentity currentUser = new UserIdentity();
            userList.clear();

            while (it.hasNext()) {
                currentUser = (UserIdentity) it.next();
                userList.add(currentUser.getFirstName() + " " + currentUser.getLastName());
            }

            userListProperty.set(FXCollections.observableArrayList(userList));
        }
    }


    @FXML
    public void OnUpdateUserButtonClicked() {
        try {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getClassLoader().getResource("updateProfileLayout.fxml"));
            Parent root = fxmlloader.load();
            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setOpacity(1);
            stage.setTitle("Ã‰dition du compte");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void OnAddDocumentButtonClicked(){
        // Ouvre le fenÃªtre d'ajout d'un fichier

        try {

            FXMLLoader fxmlloader = new FXMLLoader(getClass().getClassLoader().getResource("fenetrePartageLayout.fxml"));
            Parent root = fxmlloader.load();
            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setOpacity(1);
            stage.setTitle("Ajout d'un Fichier");
            stage.setScene(new Scene(root));
            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
