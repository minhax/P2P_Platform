package com.lo23.ihm.layouts.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.lo23.ihm.layouts.models.CreateAccountModel;
import com.lo23.ihm.layouts.models.UpdateProfileModel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class UpdateProfileController implements Initializable {

    @FXML
    private TextField loginUpdateField, passwordUpdateField, familynameUpdateField, nameUpdateField, ageUpdateField, passwordConfirmationUpdateField;

    @FXML
    private Button validateUpdateButton, previousButton;
    
    @FXML
    private Label errorUpdateLabel;
    
    private UpdateProfileModel model;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = new UpdateProfileModel();
        errorUpdateLabel.setVisible(false);
        binding();

    }

    @FXML
    public void Validate() {
    	if (loginUpdateField.getText() == null || passwordUpdateField.getText() == null || familynameUpdateField.getText() == null || nameUpdateField.getText() == null || ageUpdateField.getText() == null)
        {
            errorUpdateLabel.setVisible(true);
        }
    	else if (passwordUpdateField.getText() != passwordConfirmationUpdateField.getText()) {
    		errorUpdateLabel.setVisible(true);
    	}
    	else {
            // Integration data
			/*IhmToDataClient api = new IhmToDataClientApi();
			api.updateAccount(loginUpdateField.getText(),passwordUpdateField.getText(),nameTextField.getText(),familynameUpdateField.getText(),Integer.parseInt(birthdateUpdateField.getText()));
			*/
            System.out.println(loginUpdateField.getText() + passwordUpdateField.getText() + nameUpdateField.getText() + familynameUpdateField.getText() + ageUpdateField.getText());

            try {
                FXMLLoader fxmlloader = new FXMLLoader(getClass().getClassLoader().getResource("com/lo23/common/layouts/mainLayout.fxml"));
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
        }
    }
    
    private void binding() {
        this.familynameUpdateField.textProperty().bindBidirectional(this.model.lastnameUpdateProperty());
        this.passwordUpdateField.textProperty().bindBidirectional(this.model.passwordUpdateProperty());
        this.nameUpdateField.textProperty().bindBidirectional(this.model.nameUpdateProperty());
        this.loginUpdateField.textProperty().bindBidirectional(this.model.loginUpdateProperty());
        this.ageUpdateField.textProperty().bindBidirectional(this.model.ageUpdateProperty());
    }

    @FXML
    void Previous() {
    	 try {
             FXMLLoader fxmlloader = new FXMLLoader(getClass().getClassLoader().getResource("com/lo23/common/layouts/mainLayout.fxml"));
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
    }
}
