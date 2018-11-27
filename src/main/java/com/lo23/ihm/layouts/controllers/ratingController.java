package com.lo23.ihm.layouts.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.Rating;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import org.controlsfx.control.Rating;


public class ratingController implements Initializable{

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

    
    @FXML
    void onCancelButtonClicked(ActionEvent event) {

        // Ferme la fenêtre, ne fait rien avec les données saisies
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    	
    }

    @FXML
    void onOkButtonClicked(ActionEvent event) {

    	//System.out.println(rating.getRating());
    	//requestRateFile(Rating rating, FileHandlerInfos ratedFile) throws DataException;
    	
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
        
    }
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		rating.ratingProperty().addListener(new ChangeListener<Number>() {
			
            @Override 
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
              
            	ratingLabel.setText("Note : "+ t1.toString());
            	
            }
        });
        		
	}

    
}
