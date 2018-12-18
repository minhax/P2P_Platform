package com.lo23.ihm.layouts.controllers;

import java.net.URL;
import java.util.*;
import java.util.regex.Pattern;

import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.data.DataClientToIhm;
import com.lo23.ihm.layouts.models.AvailableFilesListCell;
import com.lo23.ihm.layouts.models.DownloadingFilesListCell;
import com.lo23.ihm.layouts.models.MyFilesListCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.lo23.common.user.UserIdentity;
import com.lo23.data.client.DataManagerClient;

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

    //pour test actualisation des utilisateurs en ligne
    private UserIdentity user;

    private Timer refreshTimer;
    private int period = 10000;
    private DataClientToIhm api;


    //gestion recherche de fichier
    private ObservableList<String> choices = FXCollections.observableArrayList();
    private List<FileHandlerInfos> researchResults = new ArrayList<FileHandlerInfos>();

    /**
     * Instancie la classe MainController
     * @param dataAPI instance de DataClientToIhm
     */
    public MainController(DataClientToIhm dataAPI)
    {
        api=dataAPI;
    }

    /**
     * Initialisation de la fenêtre Main
     * @param location URL
     * @param resources ResourceBundle
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
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


        //recherche d'un fichier
        researchTextField.setOnKeyReleased(new EventHandler<KeyEvent>()
        {
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
                if(researchTextField.getText().isEmpty() || researchTextField.getText()==null)
                {
                    listViewAvailableFiles.setItems(dataTest);
                }
            }
        });

        // définition de la structure d'une cellule de fichier disponible
        listViewAvailableFiles.setCellFactory(new Callback<ListView<FileHandler>, ListCell<FileHandler>>()
        {
            @Override
            public ListCell<FileHandler> call(ListView<FileHandler> listView)
            {
                return new AvailableFilesListCell();
            }
        });

        // définition de la structure d'une cellule de fichier dans Mes Documents
        listViewMyFiles.setCellFactory(new Callback<ListView<FileHandler>, ListCell<FileHandler>>()
        {
            @Override
            public ListCell<FileHandler> call(ListView<FileHandler> listView)
            {
                return new MyFilesListCell(api);
            }
        });

        //définition de la structure d'une cellule de fichier dans l'inglet téléchargements
        listViewDownloading.setCellFactory(new Callback<ListView<FileHandler>, ListCell<FileHandler>>()
        {
            @Override
            public ListCell<FileHandler> call(ListView<FileHandler> listView)
            {
                return new DownloadingFilesListCell();
            }
        });





        //pour test l'actualisation des utilisateurs en ligne
        user = new UserIdentity("login", "Prénom", "Nom", 21);
        connectedUsers.add(user);

        refreshTimer = new Timer();

        refreshTimer.scheduleAtFixedRate(new TimerTask()
        {
            @Override
            public void run()
            {

                Platform.runLater(() -> refreshContactsWindow());
            }
        }, 0, period);

        //refreshContactsWindow();
        binding();
    }

    /**
     * Actualisation des utilisateurs connectés via un bouton
     */
    @FXML
    public void OnRefreshConnectedUsersClicked()
    {
        try
        {
            refreshContactsWindow();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    /**
     * Affichage des fichiers dans l'onglet Mes Documents
     */
    @FXML
    public void OnChangeToTabMyFiles()
    {
        // TODO :  Gérer les fichiers ici
        ObservableList<FileHandler> data = getMyFiles();
        listViewMyFiles.setItems(data);
    }


    /**
     * Affichage des fichiers disponibles dans l'onglet correspondant
     */
    @FXML
    public void OnChangeToTabAvailable()
    {
        // TODO :  Gérer les fichiers ici
        ObservableList<FileHandler> data = getFilesSharedByOthers();
        listViewAvailableFiles.setItems(data);
    }

    /**
     * Affichage des fichiers en téléchargements dans l'onglet correspondant
     */
    @FXML
    public void OnChangeToTabDownloading()
    {
        // TODO :  Gérer les fichiers ici
        ObservableList<FileHandler> data = getDownloadingFiles();
        listViewDownloading.setItems(data);
    }

    /**
     * Changement des paramètres du serveur via un bouton
     */
    @FXML
    public void OnServerParametersButtonClicked()
    {
        String new_ip = changeServerIpAdressTextField.getText();
        Pattern pat = Pattern.compile("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
        if(pat.matcher(new_ip).matches())
            if(api.requestConnectionToServer(new_ip))
            {
                //TODO : ne pas fermer, mais plutôt réactualiser toute la page ? À réétudier quand l'application restera sur la même fenêtre

                ((Stage) this.mainHBox.getScene().getWindow()).close();
                try
                {
                    FXMLLoader fxmlloader = new FXMLLoader(getClass().getClassLoader().getResource("mainLayout.fxml"));
                    Parent root = fxmlloader.load();
                    Stage stage = new Stage();

                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setOpacity(1);
                    stage.setTitle("Fenêtre principale");
                    stage.setScene(new Scene(root));
                    stage.showAndWait();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else {
                incorrectIP.setVisible(true);
            }
         else {
            incorrectIP.setVisible(true);
        }
    }

    /**
     * Déconnexion via un bouton
     */
    @FXML
    public void OnDisconnectButtonClicked()
    {
        try
        {
            //FXMLLoader fxmlloader = new FXMLLoader(getClass().getClassLoader().getResource("connectionLayout.fxml"));
            FXMLLoader fxmlLoader = new FXMLLoader();
            // TODO: déclarer le controller de IHM
            ConnectionController controller = new ConnectionController(api); // EXEMPLE
            fxmlLoader.setController(controller);
            // controller.setDataClientToIhmApi(dataManagerClient.getDataClientToIhm());
            fxmlLoader.setLocation(getClass().getClassLoader().getResource("connectionLayout.fxml"));

            Parent root = fxmlLoader.load();
            Stage stage = (Stage) mainHBox.getScene().getWindow();
            stage.setTitle("Édition du compte");
            stage.setScene(new Scene(root));
        }
        catch (Exception e)
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

    /**
     * Affichage des utilisateurs connectés
     */
    private void refreshContactsWindow()
    {
        connectedUsers = api.requestConnectedUsers();
        if(connectedUsers!=null)
        {
            Iterator it = connectedUsers.listIterator();
            UserIdentity currentUser = new UserIdentity();
            userList.clear();

            while (it.hasNext())
            {
                currentUser = (UserIdentity) it.next();
                userList.add(currentUser.getFirstName() + " " + currentUser.getLastName());
            }

            userListProperty.set(FXCollections.observableArrayList(userList));
        }
    }


    /**
     * Accès à la page de modification du profil via un bouton
     */
    @FXML
    public void OnUpdateUserButtonClicked()
    {
        try
        {
            /*FXMLLoader fxmlLoader = new FXMLLoader();
            UpdateProfileController controller = new UpdateProfileController(api); // EXEMPLE
            fxmlLoader.setController(controller);
            fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("updateProfileLayout.fxml"));*/

            FXMLLoader fxmlLoader = new FXMLLoader();
            // TODO: déclarer le controller de IHM
            UpdateProfileController controller = new UpdateProfileController(api); // EXEMPLE
            fxmlLoader.setController(controller);
            // controller.setDataClientToIhmApi(dataManagerClient.getDataClientToIhm());
            fxmlLoader.setLocation(getClass().getClassLoader().getResource("updateProfileLayout.fxml"));

            Parent root = fxmlLoader.load();
            Stage stage = (Stage) mainHBox.getScene().getWindow();
            stage.setTitle("Édition du compte");
            stage.setScene(new Scene(root));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    /**
     * Ajout d'un document via un bouton dans l'onglet Mes Documents
     */
    @FXML
    public void OnAddDocumentButtonClicked()
    {
        // Ouvre le fenêtre d'ajout d'un fichier
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            PartageController controller = new PartageController(api); // EXEMPLE
            fxmlLoader.setController(controller);
            //fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("fenetrePartageLayout.fxml"));
            fxmlLoader.setLocation(getClass().getClassLoader().getResource("fenetrePartageLayout.fxml"));


            Parent root = fxmlLoader.load();
            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setOpacity(1);
            stage.setTitle("Ajout d'un Fichier");
            stage.setScene(new Scene(root));
            stage.showAndWait();
            // TODO :  Gérer les fichiers ici
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        ObservableList<FileHandler> data = getMyFiles();
        listViewMyFiles.setItems(data);

    }

    /**
     * Récupération des fichiers de l'onglet Mes Documents
     * @return ObservableList<FileHandler>
     */
    private ObservableList<FileHandler> getMyFiles()
    {
        List<FileHandler> fhsharedbyme = api.requestFilesSharedByMe();

        ObservableList<FileHandler> data = FXCollections.observableArrayList();
        if(fhsharedbyme != null && !fhsharedbyme.isEmpty())
        {
            data.addAll(fhsharedbyme);
        }
        return data;
    }

    /**
     * Récupération des fichiers disponibles
     * @return ObservableList<FileHandler>
     */
    private ObservableList<FileHandler> getFilesSharedByOthers()
    {
        List<FileHandler> fhsharedbyothers = api.requestFilesSharedByOthers();

        ObservableList<FileHandler> data = FXCollections.observableArrayList();
        if(fhsharedbyothers != null && !fhsharedbyothers.isEmpty())
        {
            data.addAll(fhsharedbyothers);
        }
        return data;
    }

    /**
     * Récupération des fichiers de l'onglet Téléchargements
     * @return ObservableList<FileHandler>
     */
    private ObservableList<FileHandler> getDownloadingFiles()
    {
        List<FileHandler> in_queue = api.requestInQueueFiles();

        ObservableList<FileHandler> data = FXCollections.observableArrayList();
        if(in_queue != null && !in_queue.isEmpty())
        {
            data.addAll(in_queue);
        }

        List<FileHandler> downloading = api.requestInProgressFiles();
        if(downloading != null && !downloading.isEmpty())
        {
            data.addAll(downloading);
        }
        return data;
    }

    /**
     * Recherche un fichier
     * @param searchItem String saisie par l'utilisateur
     * @param searchMethod Méthode de recherche choisie (Nom, Auteur, Tag)
     */
    private void researchFile(String searchItem, String searchMethod)
    {

        researchResults = api.requestSearchFile(searchItem);

        ObservableList<FileHandlerInfos> donnees = FXCollections.observableArrayList(researchResults);

        listViewAvailableFiles.setItems(donnees);
    }

    /**
     * Retourne la liste des utilisateurs connectés
     * @return List<UserIdentity>
     */
    public List<UserIdentity> getConnectedUsers()
    {
    	return this.connectedUsers;
    }

    public ObservableList<DownloadingFilesListCell> getCurrentlyShowingDownloadingFiles()
    {
        return listViewDownloading.getItems();
    }

}
