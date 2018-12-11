package com.lo23.app;

import com.lo23.communication.CommunicationManager.Client.CommunicationManagerClient;
import com.lo23.data.client.DataManagerClient;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class Client extends Application {
    public static void main (String[] args)
    {
        System.out.println("*** LO23 SWAG CLIENT APPLICATION ***");

        File accountsPath = new File("files/accounts");
        if(!accountsPath.exists()){
            accountsPath.mkdirs();
        }

        File usersPath = new File("files/fileparts");
        if(!usersPath.exists()){
            usersPath.mkdirs();
        }
        // On instancie les Manager côté serveur
        DataManagerClient dataManagerClient = DataManagerClient.getInstance();
        CommunicationManagerClient commManagerClient = CommunicationManagerClient.getInstance();

        dataManagerClient.setCommToDataClientAPI(commManagerClient.getCommInterface());
        commManagerClient.setDataInterface(dataManagerClient.getDataClientToComm());
        // TODO: partager l'API avec IHM sans Singleton

        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
            throws Exception
    {
        // TODO Auto-generated method stub
        try {
            Parent root = FXMLLoader.load((getClass().getClassLoader().getResource("connectionLayout.fxml")));

            primaryStage.setTitle("My Application");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
