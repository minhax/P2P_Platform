package com.lo23.ihm.layouts.controllers;
import java.net.URL;
import java.util.ResourceBundle;

import com.lo23.common.filehandler.FileHandler;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class CommentController {

	@FXML
    private TextArea commentArea;

    @FXML
    private Button validateCommentButton;
    
    private String comment;
    
    private FileHandler file;
    
    public void setFile(FileHandler file){
        this.file = file;
    }

    public FileHandler getFile(){
        return this.file;
    }
    
    
    @FXML
    void Validate() {
    	comment = commentArea.getText();
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

