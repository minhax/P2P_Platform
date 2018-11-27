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
    /**
     * DataManagerClient parent, sur lequel appeler les fonctions privées de Data.
     */
    private DataManagerClient host;

    /**
     * Constructeur de l'objet.
     * Est en accès package-private pour empêcher l'instanciation hors du groupe Data.
     * @param host DataManagerClient parent de cette API
     */
    DataClientToCommApi (DataManagerClient host)
    {
        this.host = host;
    }

    @Override
    public void receiveFilePart(FileHandler fileHandler, long blocNumber, byte[] data) {
        this.host.storeNewFilePart(fileHandler, blocNumber, data);
    }

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
    public void notifyOtherUserUpdatedAccountToAll(UserStats newlyModifiedUser)
    {
        this.host.getSessionInfos().mergeUserIntoLoggedUsers(newlyModifiedUser);
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
