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
    public void addNewConnectedUser(UserStats user)
    {
        this.manager.connections.connectUser(user);
        System.out.println("CONNEXION COTE SERVEUR DE L'UTILISATEUR : " +  user.getLogin());
        System.out.println("NB DE CONNECTES DESORMAIS : " +  this.manager.connections.getConnectedUsers().size());
        System.out.println("DANS LE DIRECTORY : " + this.manager.connections.getDirectory().getUserFiles().keySet().size());
    }

    @Override
    public HashMap<UserIdentity, Vector<FileHandlerInfos>> requestUserFiles(UserIdentity user){
        HashMap<UserIdentity, Vector<FileHandlerInfos>> infos = new HashMap<>();
        infos.put(user, this.manager.getDirectory().getFilesProposedByUser(user));
        return infos;
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
        System.out.println("Nb de connectés avant la déconnexion : " + this.manager.connections.getConnectedUsers().size());
        System.out.println("DECONNEXION COTE SERVEUR DE L'UTILISATEUR : " +  user.getLogin());
        // this.manager.commToDataApi.removeDisconnectedUser(user, this.manager.connections.getUserFiles(user));
        this.manager.connections.disconnectUser(user);
        System.out.println("Nb de connectés après la déconnexion : " + this.manager.connections.getConnectedUsers().size());
        System.out.println("Nb de connectés après la déconnexion sur diretory: " + this.manager.connections.getConnectedUsers().size());
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
