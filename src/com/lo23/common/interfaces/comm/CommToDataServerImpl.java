package com.lo23.common.interfaces.comm;
import user.*;
import filehandler.*;

public class CommToDataServerImpl implements CommToDataServer {


    /* constructeurs / implémentation du singleton */
    private static final CommToDataServerImpl COMM_TO_DATA_SERVER_API= new CommToDataServerImpl();
    private CommToDataServerImpl(){}

    /* accesseur */
    protected static CommToDataServerImpl getAPI() {return COMM_TO_DATA_SERVER_API;}



    /* implémentation des méthodes */

    //public void addNewFileSource(fileHandler file, UserIdentity user)

    //public void addNewConnectedUser(UserStats user);


   // public void updateUserChanges(UserIdentity user);


   // public void requestFileLoc(FileHandler file, UserIdentity user);


    //public void removeFileSource(FileHandler file, UserIdentity user);

    //public void deleteDisconnectedUser(User user);


    //public void deleteDisconnectedUsedFiles(User user);


    //public void updateFileChanges(String champ, FileHandler file);

    //public void addNewFileToServer(FileHandler file, UserId user);


}
