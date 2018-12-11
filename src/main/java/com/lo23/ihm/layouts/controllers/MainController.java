package com.lo23.ihm.layouts.controllers;

import java.net.URL;
import java.util.*;
import java.util.regex.Pattern;

import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.data.DataClientToIhm;
import com.lo23.data.client.DataClientToIhmApi;
import com.lo23.data.client.DataManagerClient;
import com.lo23.ihm.layouts.models.AvailableFilesListCell;
import com.lo23.ihm.layouts.models.DownloadingFilesListCell;
import com.lo23.ihm.layouts.models.MyFilesListCell;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.lo23.common.user.UserIdentity;
import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    private ComboBox<String> chooseResearchBox;

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
    private TextField changeServerIpAdressTextField;

    @FXML
    private Label incorrectIP;

    //gestion fenêtre contacts en ligne
    private List<UserIdentity> connectedUsers = new ArrayList<UserIdentity>();

    private List<String> userList = new ArrayList<String>();

    private ListProperty<String> userListProperty = new SimpleListProperty<String>();

    //pour test
    private UserIdentity user;

    private Timer refreshTimer;
    private int period = 10000;
    private DataClientToIhm api;


    //gestion recherche de fichier
    private ObservableList<String> choices = FXCollections.observableArrayList();
    private List<FileHandlerInfos> researchResults = new ArrayList<FileHandlerInfos>();


    public MainController(DataClientToIhm dataAPI) {
        api=dataAPI;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        incorrectIP.setVisible(false);

        choices.addAll("Nom", "Auteur", "Tags");
        chooseResearchBox.setItems(choices);

        // TODO :  Gérer les fichiers ici
        ObservableList<FileHandler> data = getMyFiles();
        listViewMyFiles.setItems(data);

        ObservableList<FileHandler> dataTest = FXCollections.observableArrayList();
        dataTest.addAll(new FileHandler("hash1", "document 1", 15152, "document", 16),
                new FileHandler("hash2", "document 2", 1554, "document2", 32),
                new FileHandler("hash3", "document 3", 15152, "document3", 64));


        researchTextField.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                // la recherche se fait en appuyant sur la touche entrée
                //if(event.getCode().equals(KeyCode.ENTER)) {
                    researchResults.clear();
                    String searchItem = researchTextField.getText();
                    // choix de recherche pas dans la méthode de Data??
                    String searchMethod = chooseResearchBox.getValue();

                    researchFile(searchItem,searchMethod);
                    mainTabPane.getSelectionModel().select(availableFilesTab);
               // }

                // pour revenir à la liste de tous les fichiers disponibles (hors recherche) : touche backspace
                //else if(event.getCode().equals(KeyCode.BACK_SPACE)) {
                if(researchTextField.getText().isEmpty() || researchTextField.getText()==null){
                    listViewAvailableFiles.setItems(dataTest);
                }
            }
        });

        listViewAvailableFiles.setCellFactory(new Callback<ListView<FileHandler>, ListCell<FileHandler>>() {
            @Override
            public ListCell<FileHandler> call(ListView<FileHandler> listView) {
                return new AvailableFilesListCell();
            }
        });
        listViewMyFiles.setCellFactory(new Callback<ListView<FileHandler>, ListCell<FileHandler>>() {
            @Override
            public ListCell<FileHandler> call(ListView<FileHandler> listView) {
                return new MyFilesListCell(api);
            }
        });
        listViewDownloading.setCellFactory(new Callback<ListView<FileHandler>, ListCell<FileHandler>>() {
            @Override
            public ListCell<FileHandler> call(ListView<FileHandler> listView) {
                return new DownloadingFilesListCell();
            }
        });





        //pour test
        user = new UserIdentity("login", "Prénom", "Nom", 21);
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

    @FXML
    public void OnRefreshConnectedUsersClicked() {
        try {
            refreshContactsWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void OnChangeToTabMyFiles() {
        // TODO :  Gérer les fichiers ici
        ObservableList<FileHandler> data = getMyFiles();
        listViewMyFiles.setItems(data);
    }


    @FXML
    public void OnChangeToTabAvailable() {
        // TODO :  Gérer les fichiers ici
        ObservableList<FileHandler> data = getFilesSharedByOthers();
        listViewAvailableFiles.setItems(data);
    }

    @FXML
    public void OnChangeToTabDownloading() {
        // TODO :  Gérer les fichiers ici
        ObservableList<FileHandler> data = getDownloadingFiles();
        listViewDownloading.setItems(data);
    }

    @FXML
    public void OnServerParametersButtonClicked() {
        String new_ip = changeServerIpAdressTextField.getText();
        Pattern pat = Pattern.compile("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
        if(pat.matcher(new_ip).matches())
            if(api.requestConnectionToServer(new_ip)) {
                //TODO : ne pas fermer, mais plutôt réactualiser toute la page ? À réétudier quand l'application restera sur la même fenêtre

                ((Stage) this.mainHBox.getScene().getWindow()).close();
                try {
                    FXMLLoader fxmlloader = new FXMLLoader(getClass().getClassLoader().getResource("mainLayout.fxml"));
                    Parent root = fxmlloader.load();
                    Stage stage = new Stage();

                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setOpacity(1);
                    stage.setTitle("Fenêtre principale");
                    stage.setScene(new Scene(root));
                    stage.showAndWait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                incorrectIP.setVisible(true);
            }
         else {
            incorrectIP.setVisible(true);
        }
    }


    @FXML
    public void OnDisconnectButtonClicked() {
        try {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getClassLoader().getResource("connectionLayout.fxml"));
            Parent root = fxmlloader.load();
            Stage stage = (Stage) mainHBox.getScene().getWindow();
            stage.setTitle("Édition du compte");
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void binding() {
        this.contactsListView.itemsProperty().bind(userListProperty);
    }

    private void refreshContactsWindow() {
        //décommenter à l'intégration
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
            FXMLLoader fxmlLoader = new FXMLLoader();
            UpdateProfileController controller = new UpdateProfileController(api); // EXEMPLE
            fxmlLoader.setController(controller);
            fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("updateProfileLayout.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) mainHBox.getScene().getWindow();
            stage.setTitle("Édition du compte");
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void OnAddDocumentButtonClicked(){
        // Ouvre le fenêtre d'ajout d'un fichier

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            PartageController controller = new PartageController(api); // EXEMPLE
            fxmlLoader.setController(controller);
            fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("fenetrePartageLayout.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setOpacity(1);
            stage.setTitle("Ajout d'un Fichier");
            stage.setScene(new Scene(root));
            stage.showAndWait();
            // TODO :  Gérer les fichiers ici
        } catch (Exception e) {
            e.printStackTrace();
        }


        ObservableList<FileHandler> data = getMyFiles();
        listViewMyFiles.setItems(data);

    }


    private ObservableList<FileHandler> getMyFiles() {
        List<FileHandler> fhsharedbyme = api.requestFilesSharedByMe();

        ObservableList<FileHandler> data = FXCollections.observableArrayList();
        if(fhsharedbyme != null && !fhsharedbyme.isEmpty()) {
            data.addAll(fhsharedbyme);
        }
        return data;
    }

    private ObservableList<FileHandler> getFilesSharedByOthers() {
        List<FileHandler> fhsharedbyothers = api.requestFilesSharedByOthers();

        ObservableList<FileHandler> data = FXCollections.observableArrayList();
        if(fhsharedbyothers != null && !fhsharedbyothers.isEmpty()) {
            data.addAll(fhsharedbyothers);
        }
        return data;
    }

    private ObservableList<FileHandler> getDownloadingFiles() {
        List<FileHandler> in_queue = api.requestInQueueFiles();

        ObservableList<FileHandler> data = FXCollections.observableArrayList();
        if(in_queue != null && !in_queue.isEmpty()) {
            data.addAll(in_queue);
        }

        List<FileHandler> downloading = api.requestInProgressFiles();
        if(downloading != null && !downloading.isEmpty()) {
            data.addAll(downloading);
        }
        return data;
    }

    public void researchFile(String searchItem, String searchMethod)
    {
        //décommenter à l'integ
        //DataClientToIhm api= DataManagerClient.getInstance().getDataClientToIhmApi();
        //researchResults = api.requestSearchFile(searchItem);

        //pour test
        researchResults.add(new FileHandlerInfos("hash", "title", 200, "type", 1, "desc"));

        ObservableList<FileHandlerInfos> donnees = FXCollections.observableArrayList(researchResults);

        listViewAvailableFiles.setItems(donnees);
    }

}
