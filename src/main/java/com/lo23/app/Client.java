package com.lo23.app;

import com.lo23.communication.CommunicationManager.Client.CommunicationManagerClient;
import com.lo23.communication.network.Serveur.ServerSock;
import com.lo23.data.Const;
import com.lo23.data.client.DataManagerClient;
import com.lo23.ihm.layouts.controllers.ConnectionController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends Application {
    public static void main (String[] args)
    {
        /*System.out.println("*** LO23 SWAG CLIENT APPLICATION ***");

        // On instancie les Manager côté serveur
        DataManagerClient dataManagerClient = DataManagerClient.getInstance();
        CommunicationManagerClient commManagerClient = CommunicationManagerClient.getInstance();
    
        dataManagerClient.setCommToDataClientAPI(commManagerClient.getCommInterface());
        commManagerClient.setDataInterface(dataManagerClient.getDataClientToComm());*/
        // TODO: partager l'API avec IHM sans Singleton
        /**
         * Ouverture d'un serveur socket sur le port 1029 pour le client, afin d'ecouter les messages entrants
         */
        /*ServerSock server = new ServerSock(Const.CLIENT_DEFAULT_PORT);
        server.start();
        launch(args);*/
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
        ConnectionController controller = new ConnectionController(dataManagerClient.getDataClientToIhmApi()); // EXEMPLE
        fxmlLoader.setController(controller);
        // controller.setDataClientToIhmApi(dataManagerClient.getDataClientToIhm());
        fxmlLoader.setLocation(getClass().getClassLoader().getResource("connectionLayout.fxml"));

        Parent root = fxmlLoader.load();
        primaryStage.setTitle("My Application");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
