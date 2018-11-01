package com.lo23.common.layouts.controllers;

import java.net.URL;
import java.util.ResourceBundle;

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
	private Button connectButton;
	
	@FXML
	private Button createAccountButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void connection(ActionEvent event)
	{
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
	
	public void createAccountLoader(ActionEvent event)
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
}
