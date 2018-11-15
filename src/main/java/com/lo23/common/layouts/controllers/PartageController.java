package com.lo23.common.layouts.controllers;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

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
        
        Stage stage = (Stage) ajouterFichierButton.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Ajouter fichier");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        
    }

    @FXML
    public void OnAnnulerButtonClicked (){
        Stage stage = (Stage) annulerButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void OnEnregistrerButtonClicked (){
        System.out.println("Enregistrer Fichier Button Clicked!");
    }

}
