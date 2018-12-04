package com.lo23.ihm.layouts.controllers;

import java.net.URL;
import java.util.ResourceBundle;


import com.lo23.common.exceptions.DataException;
import com.lo23.common.interfaces.data.DataClientToIhm;
import com.lo23.data.client.DataManagerClient;
import com.lo23.ihm.layouts.models.CreateAccountModel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CreateAccountController implements Initializable {
    @FXML
    private TextField lastnameTextField, firstnameTextField, loginTextField, ageTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label emptyFieldLabel,ageErrorLabel;

    private CreateAccountModel model;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = new CreateAccountModel();
        emptyFieldLabel.setVisible(false);
        ageErrorLabel.setVisible(false);
        binding();
        // TODO Auto-generated method stub

    }

    @FXML
    public void OnCreateAccountClicked() {
        if (loginTextField.getText() == null || passwordField.getText() == null || firstnameTextField.getText() == null || lastnameTextField.getText() == null || ageTextField.getText() == null)
        {
            emptyFieldLabel.setVisible(true);
        }
        else if (loginTextField.getText() == null || passwordField.getText().isEmpty() || firstnameTextField.getText().isEmpty() || lastnameTextField.getText().isEmpty() || ageTextField.getText().isEmpty())
        {
            emptyFieldLabel.setVisible(true);
        }
        else if(!ageTextField.getText().matches("\\d+"))
        {
            ageErrorLabel.setVisible(true);
        }
        else {
            // A decommenté et verifier les champs pendant l'integration
            DataClientToIhm api= DataManagerClient.getInstance().getDataClientToIhmApi();

            try{
                api.createAccount(loginTextField.getText(),passwordField.getText(),firstnameTextField.getText(),lastnameTextField.getText(),Integer.parseInt(ageTextField.getText()));
                api.requestCheckCredentials(loginTextField.getText(),passwordField.getText());
            }
            catch(DataException de){
                de.printStackTrace();
                // TODO génrer l'exception qui signifie que la création de compte a échoué.
            }
            System.out.println(loginTextField.getText() + passwordField.getText() + firstnameTextField.getText() + lastnameTextField.getText() + ageTextField.getText());

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
        }
    }


    private void binding() {
        this.lastnameTextField.textProperty().bindBidirectional(this.model.lastNameProperty());
        this.passwordField.textProperty().bindBidirectional(this.model.passwordProperty());
        this.firstnameTextField.textProperty().bindBidirectional(this.model.firstNameProperty());
        this.loginTextField.textProperty().bindBidirectional(this.model.loginProperty());
        this.ageTextField.textProperty().bindBidirectional(this.model.ageProperty());
    }

}
