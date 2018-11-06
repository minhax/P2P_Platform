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
    public void addNewUserFiles(UserStats user, List<FileHandlerInfos> files)
    {
        Iterator<FileHandlerInfos> iterator = files.iterator();
        FileHandlerInfos file;
        while(iterator.hasNext())
        {
            this.manager.connections.addFileToDirectory(user, iterator.next());
        }
    }

    @Override
    public void deleteDisconnectedUser(UserIdentity user)
    {
        this.manager.connections.disconnectUser(user);
        //TODO Dire à tous les clients que user s'est déconnecté
    }

    @Override
    public FileHandler removeFileSource(FileHandler file, User user)
    {
        this.manager.connections.removeFileSourceFromDirectory(user, file);
        return file;
    }

    @Override
    public UserIdentity updateUserChanges(UserIdentity user)
    {
        return null;
    }

    @Override
    public FileHandlerInfos addNewFileToServer(FileHandlerInfos file, UserIdentity user)
    {
        return null;
    }

    @Override
    public List<UserIdentity> requestFileLocServer(FileHandler file, UserIdentity user)
    {
        return null;
    }

    @Override
    public FileHandler updateFileChanges(FileHandlerInfos file)
    {
        return null;
    }
}
