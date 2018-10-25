package com.lo23.common.interfaces.comm;
import user.*;
import filehandler.*;
import communicationManager.*;

public class CommToDataServerImpl implements CommToDataServer {


    /* constructeurs + implémentation du singleton */
    private static final CommToDataServerImpl COMM_TO_DATA_SERVER_API= new CommToDataServerImpl();
    private CommToDataServerImpl(){}

    /* accesseur */
    protected static CommToDataServerImpl getAPI() {return COMM_TO_DATA_SERVER_API;}



    /* implémentation des méthodes */

    //public void addNewFileSource(fileHandler file, UserIdentity user)

    public void addNewConnectedUser(UserStats user) {
        //gestion d'excptions ?
        //on perd l'objet UserStats dans la bataille ?
        commManager=getUniqueComManager();
        commManager.requestUserConnection();

        return void;
    }


   // public void updateUserChanges(UserIdentity user);


    //public void requestFileLoc(FileHandler file, UserIdentity user){}


    //public void removeFileSource(FileHandler file, UserIdentity user);

    public void deleteDisconnectedUser(User user){

        userId=user.getId();
        commManager=getUniqueComManager();
        commManager.requestLogoutToServer(userId);

        return void;
    }

    public void deleteDisconnectedUserFiles (User user){
        userId=user.getId();
        //comment récup tous les fichiers concernés ? rôle de data ?
        //CommunicationManager.makeFileUnavailableToServer(userId, fileInfo);
        return void;
    }

    //public void updateFileChanges(String champ, FileHandler file);

    //public void addNewFileToServer(FileHandler file, UserId user);


}
