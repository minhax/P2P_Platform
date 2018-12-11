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
import java.util.Vector;

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
    public void notifyNewSharedFileToAll(FileHandlerInfos newSharedFile, UserIdentity source)
    {
        this.host.getSessionInfos().getDirectory().addProposedFile(source, newSharedFile);
    }

    @Override
    public void notifyNewSourceToAll(FileHandler existingFile, UserIdentity newSource)
    {

    }

    @Override
    public void notifyUpdatedSharedFileToAll(FileHandlerInfos modifiedFile, User user)
    {
        //TODO modifier dans le directory le fichier concerné
    }

    @Override
    public void notifyOtherUserUpdatedAccountToAll(UserIdentity newlyModifiedUser)
    {
        this.host.getSessionInfos().mergeUserIntoLoggedUsers(newlyModifiedUser);
    }

    @Override

    public void notifyOtherUserDisconnectedToAll(User newlyDisconnectedUser)
    {
        this.host.removeConnectedUser(newlyDisconnectedUser);
    }

    @Override
    public void notifyOtherUserConnectedToAll(UserStats newlyConnectedUser, List<FileHandlerInfos> files)
    {
        this.host.getSessionInfos().mergeUserIntoLoggedUsers(newlyConnectedUser);
        for(int i = 0; i < files.size(); i++){
            this.host.getSessionInfos().getDirectory().addProposedFile(newlyConnectedUser, files.get(i));
        }
    }

    @Override
    public void getFilePart(User userAsking, User userSource, FileHandler file, long part)
    {
        this.host.getDownloadManager().getFilePart(userAsking, userSource, file, part);
    }

    @Override
    public void notifyAskForFilePartAgain(User source, FileHandler file, long part){

        Vector<UserIdentity> sources = this
                .host
                .getSessionInfos()
                .getDirectory()
                .getUsersThatProposeFile(file);

        int indexToRemove = -1;

        for(int i = 0; i < sources.size(); i++){
            if(sources.elementAt(i).getId().equals(source.getId())){
                indexToRemove = i;
            }
        }
        sources.remove(indexToRemove);

        int chosenSourceIndex = (int) Math.random() * indexToRemove;
        // TODO demander le FilePart à comm'.
    }
}
