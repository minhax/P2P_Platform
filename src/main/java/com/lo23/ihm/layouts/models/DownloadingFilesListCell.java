package com.lo23.ihm.layouts.models;

import com.lo23.common.filehandler.FileHandler;
import com.lo23.ihm.layouts.controllers.ratingController;
import com.lo23.ihm.layouts.controllers.CommentController;
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

public class DownloadingFilesListCell extends ListCell<FileHandler> {

    private HBox hbox = new HBox();
    private Label titre = new Label("_");
    private Label taille = new Label("_");
    private Label pourcentage = new Label("0%");
    private Button addNote = new Button("Noter");
    private Button addComment = new Button("Commenter");
    private FileHandler lastItem;

    //TODO : ajouter une barre de progression (et analyser comment la mettre à jour dynamiquement)

    /**
     * Instancie la classe DownloadingFilesListCell
     */
    public DownloadingFilesListCell()
    {
        super();
        hbox.getChildren().addAll(titre, taille, pourcentage, addNote, addComment);
        addNote.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                try
                {

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

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        });

        //ajout d'un commentaire
        addComment.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
            	try
                {
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

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Actualisation d'un fichier dans l'onglet téléchargements
     * @param item FileHandler
     * @param empty booléen
     */
    @Override
    protected void updateItem(FileHandler item, boolean empty)
    {
        super.updateItem(item, empty);
        setText(null);  // No text in label of super class
        if (empty)
        {
            lastItem = null;
            setGraphic(null);
        }
        else {
            lastItem = item;
            taille.setText(item!=null ? "taille : " + item.getSize() : "taille : <null>");
            titre.setText(item!=null ? item.getTitle() : "<null>");
            setGraphic(hbox);
        }
    }

    /**
     * Actualisation du pourcentage de téléchargement
     * @param item FileHandler
     * @param pour float
     */
    public void updatePourcentage(FileHandler item, float pour)
    {
        lastItem = item;
        //Mettre à jour la barre de progression
        pourcentage.setText(String.format("%s",pour) + "%");
        setGraphic(hbox);
    }

    /**
     *
     * @param file
     * @return
     */
    public boolean checkEqualFile(FileHandler file) {
        return lastItem.getHash().equals(file.getHash());
    }
}
