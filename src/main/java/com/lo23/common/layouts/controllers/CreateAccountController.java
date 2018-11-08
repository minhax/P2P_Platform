package com.lo23.common.layouts.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.lo23.common.interfaces.ihm.IhmToDataClient;
import com.lo23.common.layouts.models.CreateAccountModel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CreateAccountController implements Initializable {
    @FXML
    private TextField lastnameTextField, firstnameTextField, loginTextField, ageTextField;
    
    @FXML
    private PasswordField passwordField;

	private CreateAccountModel model;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		model=new CreateAccountModel();
		binding();
		// TODO Auto-generated method stub
		
	}
		@FXML
	public void OnCreateAccountClicked()
	{	// A decommenté et verifier les champs pendant l'integration
		/*IhmToDataClient api = new IhmToDataClientApi();
		api.createAccount(loginTextField.getText(),passwordField.getText(),firstnameTextField.getText(),lastnameTextField.getText(),Integer.parseInt(ageTextField.getText()));
		*/System.out.println(loginTextField.getText() +passwordField.getText()+firstnameTextField.getText()+lastnameTextField.getText()+ageTextField.getText());

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


	private void binding()
	{
		this.lastnameTextField.textProperty().bindBidirectional(this.model.lastNameProperty());
		this.passwordField.textProperty().bindBidirectional(this.model.passwordProperty());
		this.firstnameTextField.textProperty().bindBidirectional(this.model.firstNameProperty());
		this.loginTextField.textProperty().bindBidirectional(this.model.loginProperty());
		this.ageTextField.textProperty().bindBidirectional(this.model.ageProperty());
	}

}
