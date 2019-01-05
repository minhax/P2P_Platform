package com.lo23.ihm.layouts.models;

import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.interfaces.data.DataClientToIhm;
import com.lo23.ihm.layouts.controllers.CommentController;
import com.lo23.ihm.layouts.controllers.ratingController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AvailableFilesListCell extends ListCell<FileHandler>  {
    HBox hbox = new HBox();
    Label titre = new Label("_");
    Label taille = new Label("_");
    Button download = new Button("Télécharger");
    FileHandler lastItem;
    DataClientToIhm api;
    

    public AvailableFilesListCell(DataClientToIhm dataAPI) {
        super();
        api=dataAPI;
        hbox.getChildren().addAll(titre, taille, download);

        download.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Clic on Download");
                api.requestFileDownload(lastItem);
            }
        });
    }

    @Override
    protected void updateItem(FileHandler item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);  // No text in label of super class
        if (empty) {
            lastItem = null;
            setGraphic(null);
        } else {
            lastItem = item;
            titre.setText(item!=null ? item.getTitle() : "<null>");
            taille.setText(item!=null ? "taille : " + item.getSize() : "taille : <null>");
            setGraphic(hbox);
        }
    }
}


