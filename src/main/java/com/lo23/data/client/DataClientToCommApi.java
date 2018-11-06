package com.lo23.data.client;

import com.lo23.common.Comment;
import com.lo23.common.Rating;
import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.interfaces.data.DataClientToComm;
import com.lo23.common.user.User;
import com.lo23.common.user.UserIdentity;
import com.lo23.common.user.UserStats;

/**
 * Objet qui implémente l'API de Data pour Comm.
 */
public class DataClientToCommApi implements DataClientToComm
{

    @Override
    public void sentFileChanges(Comment comment, FileHandler file)
    {

    }

    @Override
    public void requestFileLocToServer(FileHandler file, UserIdentity user)
    {

    }

    @Override
    public void login(UserStats user, String ip)
    {

    }

    @Override
    public void requestLogout(User user, String ip)
    {

    }

    @Override
    public void sendFilesChanges(FileHandler file)
    {

    }

    @Override
    public void sendUserChanges(UserIdentity user)
    {

    }

    @Override
    public void sendFileChanges(Rating rating, FileHandler file)
    {

    }

    @Override
    public void sendFileChanges(FileHandler file, User User)
    {

    }

    @Override
    public void sendFileChanges(Comment comment, FileHandler file)
    {

    }

    @Override
    public void sendNewFileSource(FileHandler file, UserIdentity user)
    {

    }

    @Override
    public void uploadFile(FileHandler file, UserIdentity user)
    {

    }

    @Override
    public void askDownload(UserIdentity user, FileHandler file)
    {

    }
}
