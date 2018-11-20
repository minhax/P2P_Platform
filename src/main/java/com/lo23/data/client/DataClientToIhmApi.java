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

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

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
    public void requestFileLocation(FileHandler fileToDownload)
    {

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

        // TODO: prévenir le serveur qu'un fichier est proposé
    }

    @Override
    public void requestRateFile(Rating rating, FileHandler ratedFile)
    {

    }

    @Override
    public void requestCommentFile(Comment comment, FileHandler commentedFile)
    {

    }

    @Override
    public void requestUpdateFileInfo(FileHandler modifiedFile)
    {

    }

    @Override
    public UserAccount requestAccountInfos()
    {
        return null;
    }

    @Override
    public void requestSubmitUserChanges(UserAccount modifiedUser)
    {

    }

    @Override
    public void requestMakeFileUnavailable(FileHandler file)
    {
        // Vérification de l'existence du fichier sur le disque
        String hash = file.getHash();
        File folder = new File("files/fileparts");
        File[] listOfParts = folder.listFiles();

        for(int i = 0; i < listOfParts.length; i++){
            // Signifie que le fichier existe bien
            if(listOfParts[i].getName().matches(hash)){
                host.makeLocalFileUnavailable(file);
            }
        }

        // Pas besoin d'appeler de méthode ici, elle est appelée dans la méthode
        // makeLocalFileUnavailable
        // TODO appel à la méthode de comm qui rend le fichier indispo
        // Où est-ce qu'on trouve user d'ici ?
        //host.getDataClientToComm().removeUserAsSourceFile(file, user)
    }

    @Override
    public boolean requestLogout()
    {
        // TODO change logout method to match interface
        return false;
    }

    @Override
    public boolean requestCheckCredentials(String login, String password)
    {
        return this.host.login(login, password);
    }

    @Override
    public List<FileHandler> requestFilesSharedByMe()
    {
        return null;
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

}
