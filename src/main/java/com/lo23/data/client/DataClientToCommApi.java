package com.lo23.data.client;

import com.lo23.common.Comment;
import com.lo23.common.Rating;
import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.data.DataClientToComm;
import com.lo23.common.user.User;
import com.lo23.common.user.UserIdentity;
import com.lo23.common.user.UserStats;

import java.util.List;

/**
 * Objet qui implémente l'API de Data pour Comm.
 */
public class DataClientToCommApi implements DataClientToComm
{


    @Override
    public void receiveFileLocations(List<UserIdentity> sources)
    {

    }

    @Override
    public FileHandlerInfos requestFileToDownload(UserIdentity userWhoRequestedFile, FileHandler fileToDownload)
    {
        return null;
    }

    @Override
    public void mergeFileParts(FileHandlerInfos file)
    {

    }

    @Override
    public void notifyNewSharedFileToAll(FileHandler newSharedFile)
    {

    }

    @Override
    public void notifyNewSourceToAll(FileHandler existingFile, UserIdentity newSource)
    {

    }

    @Override
    public void notifyUpdatedSharedFileToAll(FileHandler modifiedFile)
    {

    }

    @Override
    public void notifyOtherUserUpdatedAccountToAll(UserIdentity newlyModifiedUser)
    {

    }

    @Override
    public void notifyOtherUserDisconnectedToAll(User newlyDisconnectedUser, List<FileHandlerInfos> files)
    {

    }

    @Override
    public void notifyOtherUserConnectedToAll(UserIdentity newlyConnectedUser, List<FileHandlerInfos> files)
    {

    }
}
