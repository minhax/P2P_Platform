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

    private ConnectionsManager connections;

    public DataServerToCommAPI(ConnectionsManager manager)
    {
        this.connections = manager;
    }

    @Override
    public void addNewConnectedUser(UserStats user)
    {
        this.connections.connectUser(user);
    }

    @Override
    public void addNewUserFiles(UserStats user, List<FileHandlerInfos> files)
    {
        Iterator<FileHandlerInfos> iterator = files.iterator();
        FileHandlerInfos file;
        while(iterator.hasNext())
        {
            this.connections.addFileToDirectory(user, iterator.next());
        }
    }

    @Override
    public void deleteDisconnectedUser(UserIdentity user)
    {
        this.connections.disconnectUser(user);
    }

    @Override
    public FileHandler removeFileSource(FileHandler file, User user)
    {
        this.connections.removeFileSourceFromDirectory(user, file);
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
