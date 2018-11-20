package com.lo23.app;

import com.lo23.communication.CommunicationManager.Client.CommunicationManagerClient;
import com.lo23.data.client.DataManagerClient;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends Application {
    public static void main (String[] args)
    {
        System.out.println("*** LO23 SWAG CLIENT APPLICATION ***");

        // On instancie les Manager côté serveur
        DataManagerClient dataManagerClient = DataManagerClient.getInstance();
        CommunicationManagerClient commManagerClient = CommunicationManagerClient.getInstance();

        // On partage les APIs entre les Manager
        // TODO: partager les API

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
