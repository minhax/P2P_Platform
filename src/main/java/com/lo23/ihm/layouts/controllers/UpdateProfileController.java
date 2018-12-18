package com.lo23.ihm.layouts.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.lo23.common.interfaces.data.DataClientToIhm;
import com.lo23.common.interfaces.ihm.IhmToDataClient;
import com.lo23.data.client.DataManagerClient;
import com.lo23.ihm.layouts.models.CreateAccountModel;
import com.lo23.ihm.layouts.models.UpdateProfileModel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class UpdateProfileController implements Initializable {

    @FXML
    private TextField passwordUpdateField, familynameUpdateField, nameUpdateField, ageUpdateField, passwordConfirmationUpdateField;

    @FXML
    private Button validateUpdateButton, previousButton;
    
    @FXML
    private Label errorUpdateLabel;
    
    @FXML
    private AnchorPane updateUserPane;
    
    private UpdateProfileModel model;

    private DataClientToIhm api;

    private WindowsLoader wl;

    public UpdateProfileController(DataClientToIhm dataAPI){
        api=dataAPI;
        wl= new WindowsLoader();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = new UpdateProfileModel();
        errorUpdateLabel.setVisible(false);
        binding();

    }

    @FXML
    public void Validate() {
    	if (passwordUpdateField.getText() == null || familynameUpdateField.getText() == null
                || nameUpdateField.getText() == null || ageUpdateField.getText() == null)
        {
            errorUpdateLabel.setVisible(true);
        }
    	else if (!passwordUpdateField.getText().equals(passwordConfirmationUpdateField.getText()) ) {
    		errorUpdateLabel.setVisible(true);
    	}
    	else {
            // Integration data
			api.requestSubmitUserChanges(null ,passwordUpdateField.getText(),nameUpdateField.getText(),familynameUpdateField.getText(),Integer.parseInt(ageUpdateField.getText()));
            //Erreur methode inexistante
            System.out.println(passwordUpdateField.getText() + nameUpdateField.getText() + familynameUpdateField.getText() + ageUpdateField.getText());

            try {
                /*FXMLLoader fxmlloader = new FXMLLoader(getClass().getClassLoader().getResource("mainLayout.fxml"));
                Parent root = fxmlloader.load();
                Stage stage = new Stage();

                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setOpacity(1);
                stage.setTitle("Fenêtre principale");
                stage.setScene(new Scene(root));
                stage.showAndWait();*/
                Stage stage = (Stage) errorUpdateLabel.getScene().getWindow();
                stage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private void binding() {
        this.familynameUpdateField.textProperty().bindBidirectional(this.model.lastnameUpdateProperty());
        this.passwordUpdateField.textProperty().bindBidirectional(this.model.passwordUpdateProperty());
        this.nameUpdateField.textProperty().bindBidirectional(this.model.nameUpdateProperty());
        this.ageUpdateField.textProperty().bindBidirectional(this.model.ageUpdateProperty());
    }

    @FXML
    void Previous() {
        wl.MainLoader(updateUserPane.getScene(), api);
    }
}
