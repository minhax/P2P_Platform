package com.lo23.data.client;

import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.data.DataClientToComm;
import com.lo23.common.user.User;
import com.lo23.common.user.UserIdentity;
import com.lo23.common.user.UserStats;

import java.util.*;

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
    public void notifyOtherUserDisconnectedToAll(UserStats newlyDisconnectedUser)
    {
        System.out.println("newlyDisconnectedUser = " + newlyDisconnectedUser);
        System.out.println("----- DECONNEXION COTE CLIENT -------");
        System.out.println("Nb de connectés avant la déconnexion : " + this.host.getSessionInfos().getOtherLoggedUsers().size());
        this.host.removeConnectedUser(newlyDisconnectedUser);
        this.host.getSessionInfos().getOtherLoggedUsers().remove(newlyDisconnectedUser);
        System.out.println("S'est déconnecté l'utilisateur : " + newlyDisconnectedUser.getLogin());
        System.out.println("Nb de connectés après la déconnexion : " + this.host.getSessionInfos().getOtherLoggedUsers().size());
        System.out.println("Nb de fichier proposés après la déconnexion côté client : " + this.host.getSessionInfos().getDirectory().getProposedFiles().size());
        System.out.println("----- FIN DECONNEXION COTE CLIENT -------");
    }

    @Override
    public void notifyOtherUserConnectedToAll(HashMap<UserIdentity, Vector<FileHandlerInfos>> liste) {

        Vector<UserStats> connectedUsers = this.host.getSessionInfos().getOtherLoggedUsers();
        System.out.println("Liste de com.size = " + liste.size());
        System.out.println("----- CONNEXION COTE CLIENT -------");

        if (liste == null || liste.isEmpty())
            System.out.println("Liste vide renvoyée par Comm ");

        // mergeUserIntoLoggedUsers s'occupe d'insérer chaque utilisateur
        // connecté dans les infos de session s'ils n'y apparaissent pas déjà
        for (UserIdentity user : liste.keySet()){
            System.out.println("Est connecté l'utilisateur : " + user.getLogin());
            this.host.getSessionInfos().mergeUserIntoLoggedUsers(user);

            if (liste.get(user) == null)
                liste.put(user, new Vector<>());

            Iterator it = liste.get(user).iterator();


            while(it.hasNext())
            {
                FileHandlerInfos f = (FileHandlerInfos) it.next();
                System.out.println(user.getLogin() + " a le fichier " + f.getTitle());
            }
        }




        System.out.println("Taille connectedUsers post-connexion = " + this.host.getSessionInfos().getOtherLoggedUsers().size());
        // MAJ des fichiers proposés dans le DirectoryUserFiles côté Session sur le client
        for(UserIdentity user : liste.keySet()){
            Vector<FileHandlerInfos> proposedFiles = liste.get(user);

            //Fix temporaire, normalement il faudrait que à partir du
            if(proposedFiles==null)
                proposedFiles = new Vector<>();

            Iterator it = proposedFiles.iterator();

            while(it.hasNext()){
                FileHandlerInfos file = (FileHandlerInfos) it.next();
                this.host.getSessionInfos().getDirectory().addProposedFile(user, file);
            }
        }
        System.out.println("----- FIN CONNEXION COTE CLIENT -------");
    }

        //System.out.println("newlyConnectedUser = " + newlyConnectedUser.getId() + newlyConnectedUser.getFirstName());
/*
        this.host.getSessionInfos().mergeUserIntoLoggedUsers(newlyConnectedUser);
        for(int i = 0; i < files.size(); i++){
            this.host.getSessionInfos().getDirectory().addProposedFile(newlyConnectedUser, files.get(i));
        }
//        this.host.getIHMToDataClientAPI()

        System.out.println("ConnectedUsers" + this.host.getSessionInfos().getOtherLoggedUsers().size());

        this.host.getSessionInfos().getOtherLoggedUsers().forEach(e ->
                {
                    System.out.println("First name : " + e.getFirstName());
                    System.out.println("ID : " + e.getId());
                }
                    );
         */

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
