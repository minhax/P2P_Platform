package com.lo23.ihm.layouts.models;

import com.lo23.common.filehandler.FileHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class AvailableFilesListCell extends ListCell<FileHandler>  {
    HBox hbox = new HBox();
    Label titre = new Label("_");
    Label taille = new Label("_");
    Button addNote = new Button("Noter");
    Button addComment = new Button("Commenter");
    FileHandler lastItem;

    public AvailableFilesListCell() {
        super();
        hbox.getChildren().addAll(titre, taille, addNote, addComment);
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


