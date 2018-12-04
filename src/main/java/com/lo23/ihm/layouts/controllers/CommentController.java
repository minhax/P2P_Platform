package com.lo23.ihm.layouts.controllers;
import java.net.URL;
import java.util.ResourceBundle;

import com.lo23.common.filehandler.FileHandler;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import com.lo23.common.Comment;
import com.lo23.common.interfaces.data.DataClientToIhm;
import com.lo23.data.client.DataManagerClient;

public class CommentController {

	@FXML
    private TextArea commentArea;

    @FXML
    private Button validateCommentButton;
    
    private Comment comment;
    
    private FileHandler file;
    
    public void setFile(FileHandler file){
        this.file = file;
    }

    public FileHandler getFile(){
        return this.file;
    }
    
    
    @FXML
    void Validate() {
        DataClientToIhm api = DataManagerClient.getInstance().getDataClientToIhmApi();
        this.comment = new Comment(commentArea.getText(), api.requestAccountInfos());
        // A décommenter pour data
        // api.requestCommentFile(this.comment, getFile());
    	System.out.println(comment);
    	// send to Data 
    	try {
    	Stage stage = (Stage) validateCommentButton.getScene().getWindow();
        stage.close();
    	}
    	catch(Exception e)
        {
            e.printStackTrace();
        }
    	
    }

}

