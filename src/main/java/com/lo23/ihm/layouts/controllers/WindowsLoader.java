package com.lo23.ihm.layouts.controllers;

import com.lo23.common.interfaces.data.DataClientToIhm;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

public class WindowsLoader {

    public void MainLoader(Scene scene, DataClientToIhm api) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            MainController controller = new MainController(api);
            fxmlLoader.setController(controller);
            fxmlLoader.setLocation(getClass().getClassLoader().getResource("mainLayout.fxml"));

            Parent root = fxmlLoader.load();
            Stage stage = (Stage) scene.getWindow();
            stage.setTitle("Fenêtre principale");
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ConnectionLoader(Scene scene, DataClientToIhm api) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            ConnectionController controller = new ConnectionController(api);
            fxmlLoader.setController(controller);
            fxmlLoader.setLocation(getClass().getClassLoader().getResource("connectionLayout.fxml"));

            Parent root = fxmlLoader.load();
            Stage stage = (Stage) scene.getWindow();
            stage.setTitle("Édition du compte");
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void CreateAccountLoader(Scene scene, DataClientToIhm api) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            // TODO: déclarer le controller de IHM
            CreateAccountController controller = new CreateAccountController(api);
            fxmlLoader.setController(controller);
            fxmlLoader.setLocation(getClass().getClassLoader().getResource("createAccountLayout.fxml"));

            Parent root = fxmlLoader.load();
            Stage stage = (Stage) scene.getWindow();
            stage.setTitle("Creation de compte");
            stage.setScene(new Scene(root));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void UpdateProfileLoader(Scene scene, DataClientToIhm api) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            UpdateProfileController controller = new UpdateProfileController(api);
            fxmlLoader.setController(controller);
            fxmlLoader.setLocation(getClass().getClassLoader().getResource("updateProfileLayout.fxml"));

            Parent root = fxmlLoader.load();
            Stage stage = (Stage) scene.getWindow();
            stage.setTitle("Édition du compte");
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
