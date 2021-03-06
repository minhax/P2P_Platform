package com.lo23.data.client;

import com.lo23.common.Comment;
import com.lo23.common.Rating;
import com.lo23.common.exceptions.DataException;
import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.data.DataClientToIhm;
import com.lo23.common.user.User;
import com.lo23.common.user.UserAccount;
import com.lo23.common.user.UserIdentity;
import com.lo23.data.Const;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Vector;

/**
 * Objet qui implémente l'API de Data pour IHM.
 */
public class DataClientToIhmApi implements DataClientToIhm
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
    DataClientToIhmApi (DataManagerClient host)
    {
        this.host = host;
    }

    @Override
    public void createAccount (String login, String password, String firstname, String lastname, int age)
            throws DataException
    {
        String hashedPassword = host.hashPassword(password);
        UserAccount user = new UserAccount(login, firstname, lastname, age, hashedPassword);
        if (!host.saveUserInfo(user))
            throw new DataException("Error while creating account");
        if (!host.login(login, password))
            throw new DataException("Error while connecting user");
    }

    @Override
    public UserIdentity requestOtherUserInfo(User otherUser)
    {
        return null;
    }

    @Override
    public void requestShareNewFile(String pathOnDisk, String title, String description)
            throws DataException
    {
        FileHandlerInfos filehandler = host.getUploadManager().prepareToShare(pathOnDisk, title, description);
        if (filehandler == null)
            throw new DataException("Error while sharing file");

        UserAccount currUser = host.getSessionInfos().getCurrentUser();
        if (currUser == null)
            throw new DataException("Error while accessing current user");
        // On ajoute le handler aux fichiers proposés par l'utilisateur
        currUser.addProposedFile(filehandler);
        System.out.println("Hash :" + filehandler.getHash());
        // TODO: prévenir le serveur qu'un fichier est proposé
        host.getCommToDataClientApi().requestUploadFile(filehandler, currUser);
    }

    @Override
    public void requestRateFile(Rating rating, FileHandlerInfos ratedFile) throws DataException
    {
        if(rating == null)
        {
            throw new DataException("Rating object is null");
        }
        else if(ratedFile == null)
        {
            throw new DataException("File to rate is null");
        }
        else
        {
            // Ajout de la note et notification au serveur
            this.host.addRatingToFile(rating, ratedFile);
        }
    }

    @Override
    public void requestCommentFile(Comment comment, FileHandlerInfos commentedFile) throws DataException
    {
        if (commentedFile == null)
            throw new DataException("File to comment is null");
        if (comment == null)
            throw new DataException("Added comment object is null");

        this.host.addCommentToFile(comment, commentedFile);
    }

    @Override
    public void requestUpdateFileInfo(FileHandlerInfos modifiedFile)
    {
        this.host.updateFileInfo(modifiedFile);
    }

    @Override
    public UserAccount requestAccountInfos()
    {
        return null;
    }

    @Override
    public void requestSubmitUserChanges(String login, String password, String firstname, String lastname, int age)
    {

        //UserAccount modifiedUser = new UserAccount(login, firstname, lastname, age, password);
        this.host.changeUserInfos(login, host.hashPassword(password), firstname, lastname, age);
    }

    @Override
    public void requestMakeFileUnavailable(FileHandler file)
    {
        // Vérification de l'existence du fichier sur le disque
        String hash = file.getHash();
        File folder = new File("files/fileparts");
        File[] listOfParts = folder.listFiles();

        for(int i = 0; i < listOfParts.length; i++)
        {
            // Signifie que le fichier existe bien
            if(listOfParts[i].getName().matches(hash)){
                //host.makeLocalFileUnavailable(file);
            }
        }

        // Pas besoin d'appeler de méthode ici, elle est appelée dans la méthode
        // makeLocalFileUnavailable
        // TODO appel à la méthode de comm qui rend le fichier indispo
        // Où est-ce qu'on trouve user d'ici ?
        // host.getDataClientToComm().removeUserAsSourceFile(file, user)
    }

    @Override
    public boolean requestLogout()
    {
        return host.logout();
    }

    @Override
    public boolean requestCheckCredentials(String login, String password)
    {
        return this.host.login(login, password);
    }

    public boolean requestConnectionToServer(String serverIp)
    {
        return this.host.serverLogin(serverIp);
    }

    @Override
    public List<FileHandlerInfos> requestFilesSharedByMe()
    {
        return this.host.getSessionInfos().getCurrentUser().getProposedFiles();
    }

    @Override
    public List<FileHandler> requestFilesSharedByOthers()
    {
        return null;
    }

    @Override
    public List<UserIdentity> requestSearchUser(String searchTerm)
    {
        return null;
    }

    @Override
    public List<FileHandlerInfos> requestSearchFile(String searchTerm)
    {
        Vector<FileHandlerInfos> returnedFiles = new Vector<>();
        // On parcourt tous les fichiers disponibles 
        for (FileHandlerInfos fhi :
                this.host.getSessionInfos().getDirectory().getProposedFiles())
        {
            if (fhi.getTitle().contains(searchTerm) || fhi.getDesc().contains(searchTerm) ){
                returnedFiles.add(fhi);
            }
        }
        return null;
    }

    @Override
    public void requestImportAccountData(String pathToAccountFile)
    {

    }

    @Override
    public File requestExportAccountData(User profileToExport)
    {
        return null;
    }

    @Override
    public List<UserIdentity> requestConnectedUsers()
    {
        return null;
    }

    @Override
    public void requestFileDownload(FileHandler fileToDownload)
    {
        if (fileToDownload != null)
        {
            //this.host.downloadFile(fileToDownload);
        }
    };

    @Override
    public Vector<FileHandler> requestInQueueFiles(){
        return this.host.getDownloadManager().getInQueue();
    }

    @Override
    public Vector<FileHandler> requestInProgressFiles(){
        return this.host.getDownloadManager().getInProgress();
    }
}
