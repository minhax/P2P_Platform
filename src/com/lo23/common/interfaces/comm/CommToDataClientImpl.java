package com.lo23.common.interfaces;

import users.*;
import java.util.UUID;
import fileHandler.*;
import communicationManager.*;

public interface CommToDataClient
{


    //public void searchUser(UserIdentity user);

    public void sendConnectedUserUpdate(UserIdentity user, List<FileHandler> files){


        return void;
    }

    public void sendDisconnectedUserUpdate(UserIdentity user, List<FileHandler> files){
        return void;
    }


    // public void getFile(UserIdentity user, FileHandler file);


    // public void sendFile(FileHandlerInfo fileInfo);


    //public void saveFile(FileHandlerInfo fileInfo)


   // public void receiveFileLoc(List<UserIdentity> users);


    // public void saveFileChanges(FileHandler file, User user);

    //public void sendUpdatedAccount(UserIndentity user);


    //public void sendFileInfo(FileHandler file, UserId user);

    //public void sendUpdatedFile(FileHandler file);


    //public void sendNewFileSource(FileHandler file, UserId user);

   // public void UploadFile(FileHandler file, UserId user);

}