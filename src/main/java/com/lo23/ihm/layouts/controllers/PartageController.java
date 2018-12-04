package com.lo23.ihm.layouts.controllers;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

import com.lo23.common.interfaces.data.DataClientToIhm;
import com.lo23.common.user.UserAccount;
import com.lo23.data.client.DataManagerClient;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.lang.Object.*;
//import org.controlsfx.control.Notifications;

//import com.lo23.common.interfaces.data;

import javafx.stage.Stage;
import javafx.stage.FileChooser;

import java.io.File;

public class PartageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ajouterFichierButton;

    @FXML
    private Button annulerButton;

    @FXML
    private TextField categorieFichier;

    @FXML
    private TextField tailleFichier;

    @FXML
    private TextArea informationsFichier;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private TextField sourceFichier;

    @FXML
    private TextField tagsFichier;

    @FXML
    private TextField nomFichier;

    @FXML
    private Button enregistrerButton;

    // Fichier selectionné dans la fonction OnAjouterFichierButtonClicked()
    //  et partagé avec Data dans la fonction OnEnregistrerButtonClicked().
    public File selectedFile;


    @FXML
    void initialize() {
        assert ajouterFichierButton != null : "fx:id=\"ajouterFichierButton\" was not injected: check your FXML file 'fenetrePartageLayout.fxml'.";
        assert annulerButton != null : "fx:id=\"annulerButton\" was not injected: check your FXML file 'fenetrePartageLayout.fxml'.";
        assert categorieFichier != null : "fx:id=\"categorieFichier\" was not injected: check your FXML file 'fenetrePartageLayout.fxml'.";
        assert tailleFichier != null : "fx:id=\"tailleFichier\" was not injected: check your FXML file 'fenetrePartageLayout.fxml'.";
        assert informationsFichier != null : "fx:id=\"informationsFichier\" was not injected: check your FXML file 'fenetrePartageLayout.fxml'.";
        assert mainPane != null : "fx:id=\"mainPane\" was not injected: check your FXML file 'fenetrePartageLayout.fxml'.";
        assert sourceFichier != null : "fx:id=\"sourceFichier\" was not injected: check your FXML file 'fenetrePartageLayout.fxml'.";
        assert tagsFichier != null : "fx:id=\"tagsFichier\" was not injected: check your FXML file 'fenetrePartageLayout.fxml'.";
        assert nomFichier != null : "fx:id=\"nomFichier\" was not injected: check your FXML file 'fenetrePartageLayout.fxml'.";
        assert enregistrerButton != null : "fx:id=\"enregistrerButton\" was not injected: check your FXML file 'fenetrePartageLayout.fxml'.";

    }

    @FXML
    public void OnAjouterFichierButtonClicked (){

        // Utilise le FileChooser pour trouver le fichier que l'utilisateur veut uploader
        Stage stage = (Stage) ajouterFichierButton.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Ajouter fichier");
        fileChooser.getExtensionFilters().addAll();
        selectedFile = fileChooser.showOpenDialog(stage);
        DataClientToIhm api= DataManagerClient.getInstance().getDataClientToIhmApi();
        UserAccount userAccount = api.requestAccountInfos();
        //sourceFichier.setText(userAccount.getLogin());
        nomFichier.setText(selectedFile.getName());
        tailleFichier.setText(humanReadableByteCount(selectedFile.length(),true));



    }

    @FXML
    public void OnAnnulerButtonClicked (){

        // Ferme la fenêtre, ne fait rien avec les données saisies
        Stage stage = (Stage) annulerButton.getScene().getWindow();
        stage.close();

    }

    @FXML
    public void OnEnregistrerButtonClicked (){



        if (selectedFile == null) {

            //Notifications.create().title("Input non valide").text("Vous n'avez pas selectionné un fichier.").showWarning();

        } else { // Envoye à Data les informations données par l'utilisateur

            String pathOnDisk = selectedFile.getPath();
            String title = nomFichier.getText();
            String description = informationsFichier.getText();
            DataClientToIhm api= DataManagerClient.getInstance().getDataClientToIhmApi();
            try {
                api.requestShareNewFile(pathOnDisk, title, description);
            }
            catch(Exception e)
            {

            }

            System.out.println(pathOnDisk);
            System.out.println(title);
            System.out.println(description);

            Stage stage = (Stage) enregistrerButton.getScene().getWindow();
            stage.close();

        }


    }

    // Prends la taille du fichier en bytes et donne un string avec la taille du fichier et ses unités en SI ou Binaire
    public static String humanReadableByteCount(long bytes, boolean si) {

        int unit = si ? 1000 : 1024;
        if (bytes < unit) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);

    }

}