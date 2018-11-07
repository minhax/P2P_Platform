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

    /**
     * Crée un compte à partir des informations de base.
     * @param login Login de l'utilisateur
     * @param password Mot de passe (en clair) de l'utilisateur
     * @param firstname Prénom de l'utilisateur
     * @param lastname Nom de l'utilisateur
     * @param age Age de l'utilisateur
     * @throws DataException Exception lors de la création du compte
     */
    public void createAccount (String login, String password, String firstname, String lastname, int age) throws DataException
    { // TODO: définir quel retour pour IHM (void d'après diagramme de séquence)
        String hashedPassword = host.hashPassword(password);
        UserAccount user = new UserAccount(login, firstname, lastname, age, hashedPassword);
        if (!host.saveUserInfo(user))
        {
            throw new DataException("Error while creating account");
        }
        // Connecter l'utilisateur
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
    public void requestShareNewFile(FileHandler newFile)
    {

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

    }

    @Override
    public void requestLogout()
    {

    }

    @Override
    public boolean requestCheckCredentials(String login, String password)
    {
        return true;
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
