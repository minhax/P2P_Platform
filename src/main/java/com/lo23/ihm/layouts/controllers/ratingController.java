package com.lo23.ihm.layouts.controllers;

import com.lo23.common.filehandler.FileHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

import java.net.URL;
import java.util.ResourceBundle;

public class ratingController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Rating rating;

    @FXML
    private Label ratingLabel;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    private FileHandler file;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        rating.ratingProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {

                ratingLabel.setText("Rating : "+ t1.toString());
            }
        });

    }

    public void setFile(FileHandler file){
        this.file = file;
    }

    public FileHandler getFile(){
        return this.file;
    }

    public void onOkButtonClicked (){

        // Enlever pour envoyer a data
        // requestRateFile(rating.getRating(), getFile());

        System.out.println("The File "+getFile()+" has a rating of "+rating.getRating());

        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();

    }

    public void onCancelButtonClicked (){

        // Ferme la fenêtre, ne fait rien avec les données saisies
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

}