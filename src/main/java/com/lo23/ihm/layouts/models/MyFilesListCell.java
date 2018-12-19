package com.lo23.ihm.layouts.models;

import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.interfaces.data.DataClientToIhm;
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
import com.lo23.ihm.layouts.controllers.ratingController;
import com.lo23.ihm.layouts.controllers.CommentController;


public class MyFilesListCell extends ListCell<FileHandler>  {
    HBox hbox = new HBox();
    Label label = new Label("_");
    Button addNote = new Button("Noter");
    Button addComment = new Button("Commenter");
    Button del = new Button("Supprimer");
    FileHandler lastItem;
    private DataClientToIhm api;

    public MyFilesListCell(DataClientToIhm dataAPI) {
        super();
        api=dataAPI;
        hbox.getChildren().addAll(label, addNote, addComment, del);
        addNote.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getClassLoader().getResource("ratingLayout.fxml"));
                    Parent root = loader.load();

                    ratingController controller = loader.getController();
                    controller.setFile(lastItem);

                    Stage stage = new Stage();

                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setOpacity(1);
                    stage.setTitle("Notation d'un fichier");
                    stage.setScene(new Scene(root));
                    stage.showAndWait();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        addComment.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	try {

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getClassLoader().getResource("commentLayout.fxml"));
                    Parent root = loader.load();

                    CommentController controller = loader.getController();
                    controller.setFile(lastItem);

                    Stage stage = new Stage();

                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setOpacity(1);
                    stage.setTitle("Commentaire d'un fichier");
                    stage.setScene(new Scene(root));
                    stage.showAndWait();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        del.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Suppression de l'objet : " + lastItem);
                api.requestMakeFileUnavailable(lastItem);
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


