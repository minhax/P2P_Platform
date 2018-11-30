package com.lo23.ihm.layouts.models;

import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.interfaces.*;
import com.lo23.common.data.client.DataManagerClient;
import com.lo23.data.client.DataManagerClient;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;


public class MyFilesListCell extends ListCell<FileHandler>  {
    HBox hbox = new HBox();
    Label label = new Label("_");
    Button addNote = new Button("Noter");
    Button addComment = new Button("Commenter");
    Button del = new Button("Supprimer");
    FileHandler lastItem;

    public MyFilesListCell() {
        super();
        hbox.getChildren().addAll(label, addNote, addComment, del);
        addNote.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Ajout d'une note pour l'objet : " + lastItem);
            }
        });

        addComment.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Ajout d'un commentaire pour l'objet : " + lastItem);
            }
        });

        del.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Suppression de l'objet : " + lastItem);
                DataManagerClient dm = DataManagerClient.getInstance();
                dm.getDataClientToIhmApi().requestMakeFileUnavailable(lastItem);
                updateItem(lastItem,true);
            }
        });
    }

    @Override
    protected void updateItem(FileHandler item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);  // No text in label of super class
        if (empty) {
            lastItem = null;
            System.out.println("Deleting Graphics : " + item);
            setGraphic(null);
        } else {
            lastItem = item;
            label.setText(item!=null ? item.getTitle() : "<null>");
            setGraphic(hbox);
        }
    }
}


