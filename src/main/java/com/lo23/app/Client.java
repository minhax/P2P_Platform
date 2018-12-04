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
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
            throws Exception
    {
        // TODO Auto-generated method stub
        // On instancie les Manager côté client
        DataManagerClient dataManagerClient = new DataManagerClient();
        CommunicationManagerClient commManagerClient = CommunicationManagerClient.getInstance();

        // On échange les API
        dataManagerClient.setCommToDataClientAPI(commManagerClient.getCommInterface());
        commManagerClient.setDataInterface(dataManagerClient.getDataClientToCommApi());
        // TODO: partager l'API avec IHM

        // Code de gestion du FXML
        FXMLLoader fxmlLoader = new FXMLLoader();
        // TODO: déclarer le controller de IHM
        // Controller controller = new Controller(); // EXEMPLE
        fxmlLoader.setController(controller);
        // controller.setDataClientToIhmApi(dataManagerClient.getDataClientToIhm());
        fxmlLoader.setLocation(getClass().getResource("connectionLayout.fxml"));

        Parent root = fxmlLoader.load();
        primaryStage.setTitle("My Application");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
