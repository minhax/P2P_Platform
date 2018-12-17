package com.lo23.data.server;

import com.lo23.common.Comment;
import com.lo23.common.Rating;
import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.common.user.User;
import com.lo23.common.user.UserIdentity;
import com.lo23.common.user.UserStats;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class DataServerToCommAPI implements DataServerToComm
{
    private DataManagerServer manager;

    public DataServerToCommAPI(DataManagerServer managerServer)
    {
        this.manager = managerServer;
    }

    @Override
    public HashMap<UserIdentity, Vector<FileHandlerInfos>> addNewConnectedUser(UserStats user)
    {
        this.manager.connections.connectUser(user);
        return this.requestUserFiles();
    }

    @Override
    public HashMap<UserIdentity, Vector<FileHandlerInfos>> requestUserFiles(){
        return this.manager.getDirectory().getUserFiles();
    }

    @Override
    public void addNewUserFiles(List<FileHandlerInfos> filesSharedByUser, UserStats user)
    {
        Iterator<FileHandlerInfos> iterator = filesSharedByUser.iterator();
        FileHandlerInfos file;
        while(iterator.hasNext())
        {
            this.manager.connections.addFileToDirectory(user, iterator.next());
        }
    }

    @Override
    public UserStats removeDisconnectedUser(UserStats user)
    {
        // this.manager.commToDataApi.removeDisconnectedUser(user, this.manager.connections.getUserFiles(user));
        this.manager.connections.disconnectUser(user);
        // TODO renvoi des éléments qu'il faut à savoir les users encore connectés + les fichiers dispo
        return user;
    }

    @Override
    public void removeFileSource(FileHandler file, User user)
    {
        this.manager.connections.removeFileSourceFromDirectory(user, file);
        System.out.println("[DATA] Suppression du fichier" + file.getHash() + "coté serveur");
    }

    @Override
    public void updateUserChanges(UserIdentity user)
    {
        UserIdentity u = this.manager.connections.getDirectory().getUser(user.getId());
        u.setAge(user.getAge());
        u.setFirstName(user.getFirstName());
        u.setLastName(user.getLastName());
    }

    @Override
    public void addNewFileToServer(FileHandlerInfos file, UserIdentity user)
    {
        this.manager.connections.addFileToDirectory(user, file);
    }

    @Override
    public List<UserIdentity> requestFileLocationServer(FileHandler file)
    {
        return null;
    }

    @Override
    public void updateFileWithNewComment(FileHandlerInfos file, Comment newComment, User user)
    {
      //  this.manager.connections.
        //TODO : merge newComment into the FileHandlerInfos, and when updating the dictionary, merge the previous and new FileHandlerInfos
    }

    @Override
    public void updateFileWithNewRating(FileHandlerInfos file, Rating newRating, User user)
    {
        //TODO : merge newRating into the FileHandlerInfos, and when updating the dictionary, merge the previous and new FileHandlerInfos
    }

}
