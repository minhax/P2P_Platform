package com.lo23.common.layouts.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.lo23.common.layouts.models.ConnectionModel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConnectionController implements Initializable{
	
	@FXML
	private AnchorPane connectionPane;
	
	@FXML
	private PasswordField passwordField;
	
	@FXML
	private TextField userNameTextField;
	
	@FXML
	private TextField serverChoiceTextField;
	
	@FXML
	private TextField portTextField;
	
	@FXML
	private Button createAccountButton;

	private ConnectionModel model;
	private boolean authorizeConnection;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		model = new ConnectionModel();
		binding();
		// TODO Auto-generated method stub
		
	}

	@FXML
	public void OnConnectClicked(ActionEvent event)
	{
		//A décommenter à l'integration, la routine de connection n'est pas encore bien implémenter
		//authorizeConnection= IhmToDataClient.checkAccount(this.userNameTextField.getText(),this.passwordField.getText());
		try
		{
			FXMLLoader fxmlloader = new FXMLLoader(getClass().getClassLoader().getResource("com/lo23/common/layouts/mainLayout.fxml"));
			Parent root = fxmlloader.load();
			Stage stage = new Stage();
			
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setOpacity(1);
			stage.setTitle("Fenêtre principale");
			stage.setScene(new Scene(root));
			stage.showAndWait();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@FXML
	public void OnCreateAccountLoaderClicked(ActionEvent event)
	{
		try
		{
			FXMLLoader fxmlloader = new FXMLLoader(getClass().getClassLoader().getResource("com/lo23/common/layouts/createAccountLayout.fxml"));
			Parent root = fxmlloader.load();
			Stage stage = new Stage();
			
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setOpacity(1);
			stage.setTitle("Creation de compte");
			stage.setScene(new Scene(root));
			stage.showAndWait();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private void binding()
	{
		this.userNameTextField.textProperty().bindBidirectional(this.model.userProperty());
		this.passwordField.textProperty().bindBidirectional(this.model.passwordProperty());
		this.serverChoiceTextField.textProperty().bindBidirectional(this.model.serverProperty());
		this.portTextField.textProperty().bindBidirectional(this.model.portProperty());
	}
}
