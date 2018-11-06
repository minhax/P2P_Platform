package com.lo23.common.layouts.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.lo23.common.layouts.models.CreateAccountModel;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
		//IhmToDataClient.requestRegisterForm(loginTextField.getText(),passwordField.getText(),firstnameTextField.getText(),loginTextField.getText(),ageTextField.getText());
		System.out.println(loginTextField.getText() +passwordField.getText()+firstnameTextField.getText()+loginTextField.getText()+ageTextField.getText());
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
