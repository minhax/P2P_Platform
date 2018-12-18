package com.lo23.ihm.layouts.controllers;

import java.net.URL;
import java.util.ResourceBundle;


import com.lo23.common.exceptions.DataException;
import com.lo23.common.interfaces.data.DataClientToIhm;
import com.lo23.ihm.layouts.models.CreateAccountModel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CreateAccountController implements Initializable {
    @FXML
    private TextField lastnameTextField, firstnameTextField, loginTextField, ageTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label emptyFieldLabel, ageErrorLabel;

    @FXML
    private AnchorPane accountFormPane;

    private CreateAccountModel model;

    private DataClientToIhm api;


    public CreateAccountController(DataClientToIhm dataAPI){
        api=dataAPI;
    }

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
        if (loginTextField.getText() == null || passwordField.getText() == null || firstnameTextField.getText() == null || lastnameTextField.getText() == null || ageTextField.getText() == null) {
            emptyFieldLabel.setVisible(true);
        } else if (loginTextField.getText() == null || passwordField.getText().isEmpty() || firstnameTextField.getText().isEmpty() || lastnameTextField.getText().isEmpty() || ageTextField.getText().isEmpty()) {
            emptyFieldLabel.setVisible(true);
        } else if (!ageTextField.getText().matches("\\d+")) {
            ageErrorLabel.setVisible(true);
        } else {
            // A decommenté et verifier les champs pendant l'integration

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
                /*FXMLLoader fxmlloader = new FXMLLoader(getClass().getClassLoader().getResource("mainLayout.fxml"));
                Parent root = fxmlloader.load();
                Stage stage = (Stage) accountFormPane.getScene().getWindow();
                stage.setTitle("Fenêtre principale");
                stage.setScene(new Scene(root));*/

                FXMLLoader fxmlLoader = new FXMLLoader();
                // TODO: déclarer le controller de IHM
                MainController controller = new MainController(api); // EXEMPLE
                fxmlLoader.setController(controller);
                // controller.setDataClientToIhmApi(dataManagerClient.getDataClientToIhm());
                fxmlLoader.setLocation(getClass().getClassLoader().getResource("mainLayout.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = (Stage) accountFormPane.getScene().getWindow();
                stage.setTitle("Fenêtre principale");
                stage.setScene(new Scene(root));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void OnBackToCoClicked() {
    	try {
            //FXMLLoader fxmlloader = new FXMLLoader(getClass().getClassLoader().getResource("mainLayout.fxml"));
            FXMLLoader fxmlLoader = new FXMLLoader();
            // TODO: déclarer le controller de IHM
            ConnectionController connectionController = new ConnectionController(api); // EXEMPLE
            fxmlLoader.setController(connectionController);
            // controller.setDataClientToIhmApi(dataManagerClient.getDataClientToIhm());
            fxmlLoader.setLocation(getClass().getClassLoader().getResource("connectionLayout.fxml"));


            Parent root = fxmlLoader.load();
            Stage stage = (Stage) accountFormPane.getScene().getWindow();
            stage.setTitle("Fenêtre principale");
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
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
