package com.lo23.common.layouts.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.lo23.common.layouts.models.CreateAccountModel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class updateProfileController {

    @FXML
    private TextField loginUpdateField, passwordUpdateField, familynameUpdateField, nameUpdateField, birthdateUpdateField, passwordConfirmationUpdateField;

    @FXML
    private Button avatarUpdateButton, validateUpdateButton, previousButton;
    
    @FXML
    private Label errorUpdateLabel;
    
    private UpdateAccountModel model;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = new UpdateAccountModel();
        errorUpdateLabel.setVisible(false);
        binding();

    }

    @FXML
    public void Validate() {
    	if (loginUpdateField.getText() == null || passwordUpdateField.getText() == null || familynameUpdateField.getText() == null || nameUpdateField.getText() == null || birthdateUpdateField.getText() == null)
        {
            errorUpdateLabel.setVisible(true);
        }
    	else if (passwordUpdateField.getText() != passwordConfirmationUpdateField.getText()) {
    		errorUpdateLabel.setVisible(true);
    	}
    	else {
            // A decommenté et verifier les champs pendant l'integration
			/*IhmToDataClient api = new IhmToDataClientApi();
			api.createAccount(loginTextField.getText(),passwordField.getText(),firstnameTextField.getText(),lastnameTextField.getText(),Integer.parseInt(ageTextField.getText()));
			*/
            System.out.println(loginUpdateField.getText() + passwordUpdateField.getText() + nameUpdateField.getText() + familynameUpdateField.getText() + birthdateUpdateField.getText());

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
        this.familynameUpdateField.textProperty().bindBidirectional(this.model.lastNameProperty());
        this.passwordUpdateField.textProperty().bindBidirectional(this.model.passwordProperty());
        this.nameUpdateField.textProperty().bindBidirectional(this.model.firstNameProperty());
        this.loginUpdateField.textProperty().bindBidirectional(this.model.loginProperty());
        this.birthdateUpdateField.textProperty().bindBidirectional(this.model.ageProperty());
    }
}
/*    @FXML
    void Previous(ActionEvent event) {

    }

    @FXML
    void updateAvatar(ActionEvent event) {

    }

}*/
