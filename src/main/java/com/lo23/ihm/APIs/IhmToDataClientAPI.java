package com.lo23.ihm.APIs;

import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.ihm.IhmToDataClient;
import com.lo23.common.user.UserIdentity;
import com.lo23.ihm.layouts.controllers.MainController;
import com.lo23.ihm.layouts.models.DownloadingFilesListCell;
import javafx.collections.ObservableList;

import java.util.List;

public class IhmToDataClientAPI implements IhmToDataClient {

    private MainController controller;

    public IhmToDataClientAPI(){

    }
    
    public IhmToDataClientAPI(MainController controller) {
        this.controller = controller;
    }

     public void  setControllerAPI(MainController controller){
        this.controller=controller;
    }

    @Override
    public UserIdentity updateFileDownload(UserIdentity userInfo)
    { //Comprend pas trop a quoi ça sert
    	
        return userInfo;
        
    }

    @Override
    public void showPercentageComplete(FileHandler file, float percentage)
    {
    	
    	//A ajouter à la structure des fichiers en download
        ObservableList<DownloadingFilesListCell> items = controller.getCurrentlyShowingDownloadingFiles();

        for (DownloadingFilesListCell item : items) {
            if(item.checkEqualFile(file)) {
                item.updatePourcentage(file, percentage);
                return;
            }
        }
    }

	@Override
	public void UpdateConnectedUsers(UserIdentity user)
    {
		
		controller.getConnectedUsers().add(user);
		
	}

    @Override
    public void updateAvailableFiles(FileHandlerInfos fileInfo, UserIdentity user) {
        // TODO
    }

    @Override
    public void sendUpdates(UserIdentity userInfo, List<FileHandlerInfos> fileInfo)
    {
        // TODO
    }
}
