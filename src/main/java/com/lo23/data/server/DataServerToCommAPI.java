package com.lo23.data.server;

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
        // TODO Dire à tous les utilisateurs connectés qu'il y a un nouvel utilisateur
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
        this.manager.connections.disconnectUser(user);
        //TODO Dire à tous les clients que user s'est déconnecté
    }

    @Override
    public void removeFileSource(FileHandler file, User user)
    {
        this.manager.connections.removeFileSourceFromDirectory(user, file);
    }

    @Override
    public void updateUserChanges(UserIdentity user)
    {

    }

    @Override
    public void addNewFileToServer(FileHandlerInfos file, UserIdentity user)
    {

    }

    @Override
    public List<UserIdentity> requestFileLocationServer(FileHandler file)
    {
        return null;
    }

    @Override
    public void updateFileChanges(FileHandlerInfos file)
    {

    }
}
