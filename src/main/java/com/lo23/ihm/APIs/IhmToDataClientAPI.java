package com.lo23.ihm.APIs;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.ihm.IhmToDataClient;
import com.lo23.common.user.UserIdentity;
import com.lo23.ihm.layouts.controllers.MainController;

import java.util.List;

public class IhmToDataClientAPI implements IhmToDataClient {

    private MainController controller;
    
    public IhmToDataClientAPI(MainController controller) {
        this.controller = controller;
    }

    @Override
    public void UpdateAvailableFiles(FileHandlerInfos fileInfo, UserIdentity user) {
    	
    	// On affiche jamais l'ensemble des fihiers dispos.
    }

    @Override
    public void sendUpdates(UserIdentity userInfo, List<FileHandlerInfos> fileInfo) {

    	// Chargé au moment de la recherche par utilisateur.
    	
    }

    @Override
    public void addFileSource(FileHandlerInfos fileInfo, UserIdentity user) {

    	//On utilise uen liste de FileHandlerInfos pour la liste des fichiers dispo et il n'y a pas de liste de seeders dessus
    	//du coup pour l'instant pas possible. En plus ça sert pas à grand chose...
    	
    }

    @Override
    public UserIdentity updateFileDownload(UserIdentity userInfo) { //Comprend pas trop a quoi ça sert
    	
        return userInfo;
        
    }

    @Override
    public void showPercentageComplete(float percentage) {
    	
    	//A ajouter à la structure des fichiers en download
    	
    }

	@Override
	public void UpdateConnectedUsers(UserIdentity user) {
		
		controller.getConnectedUsers().add(user);
		
	}
}
