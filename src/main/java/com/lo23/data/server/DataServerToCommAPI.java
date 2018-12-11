package com.lo23.data.server;

import com.lo23.common.Comment;
import com.lo23.common.Rating;
import com.lo23.common.exceptions.DataException;
import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.data.DataServerToComm;
import com.lo23.common.user.User;
import com.lo23.common.user.UserIdentity;
import com.lo23.common.user.UserStats;

import java.util.Iterator;
import java.util.List;
public class DataServerToCommAPI implements DataServerToComm
{
    private DataManagerServer manager;

    public DataServerToCommAPI(DataManagerServer managerServer)
    {
        this.manager = managerServer;
    }

    @Override
    public void addNewConnectedUser(UserStats user)
    {
        this.manager.connections.connectUser(user);
        // La partie Comm devrait notifier tous les clients de la nouvelle connexion
        this.manager.commToDataApi.sendConnectedUserToAll(user,
                this.manager.connections.getDirectory().getFilesProposedByUser(user));
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
    public void removeDisconnectedUser(User user)
    {
        // this.manager.commToDataApi.removeDisconnectedUser(user, this.manager.connections.getUserFiles(user));
        this.manager.connections.disconnectUser(user);
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
        List<UserIdentity> returnedUsers = this.manager.connections.getUsersThatProposeFile(file);
        return returnedUsers;
    }

    @Override
    public void updateFileWithNewComment(FileHandlerInfos file, Comment newComment, User user)
    {
        //TODO : merge newComment into the FileHandlerInfos, and when updating the dictionary, merge the previous and new FileHandlerInfos
    }

    @Override
    public void updateFileWithNewRating(FileHandlerInfos file, Rating newRating, User user)
    {
        //TODO : merge newRating into the FileHandlerInfos, and when updating the dictionary, merge the previous and new FileHandlerInfos
    }

    @Override
    public void addFileRating(Rating rating, FileHandlerInfos fileToRate) throws DataException
    {
        this.manager.connections.addRatingToFile(rating, fileToRate);
    };

    @Override
    public void addFileComment(Comment comment, FileHandlerInfos fileToComment) throws DataException
    {
        this.manager.connections.addCommentToFile(comment, fileToComment);
    }
}
