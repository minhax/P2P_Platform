package com.lo23.ihm.layouts.models;

import com.lo23.common.filehandler.FileHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;

public class DownloadingFilesListCell extends ListCell<FileHandler> {

    HBox hbox = new HBox();
    Label titre = new Label("_");
    Label taille = new Label("_");
    Button addNote = new Button("Noter");
    Button addComment = new Button("Commenter");
    FileHandler lastItem;

    //TODO : ajouter une barre de progression (et analyser comment la mettre à jour dynamiquement)

    public DownloadingFilesListCell() {
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
            //Mettre à jour la barre de progression
            setGraphic(hbox);
        }
    }
}
