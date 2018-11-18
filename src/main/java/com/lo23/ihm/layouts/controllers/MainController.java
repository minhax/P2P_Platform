package com.lo23.ihm.layouts.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.lo23.common.filehandler.FileHandler;
import com.lo23.ihm.layouts.models.AvailableFilesListCell;
import com.lo23.ihm.layouts.models.DownloadingFilesListCell;
import com.lo23.ihm.layouts.models.MyFilesListCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

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
    private TextField researchUserTextField;

    @FXML
    private Button disconnectButton;

    @FXML
    private ListView listViewAvailableFiles;

    @FXML
    private ListView listViewMyFiles;

    @FXML
    private ListView listViewDownloading;
	
	
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

}
