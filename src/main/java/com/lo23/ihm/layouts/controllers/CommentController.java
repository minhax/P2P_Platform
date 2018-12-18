package com.lo23.ihm.layouts.controllers;

import com.lo23.common.filehandler.FileHandler;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import com.lo23.common.Comment;
import com.lo23.common.interfaces.data.DataClientToIhm;

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

    private DataClientToIhm api;


    public CommentController(DataClientToIhm dataAPI){
        api=dataAPI;
    }
    @FXML
    void Validate() {
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

