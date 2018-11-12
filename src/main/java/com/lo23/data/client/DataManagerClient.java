package com.lo23.data.client;

import com.lo23.common.interfaces.comm.CommToDataClient;
import com.lo23.common.interfaces.data.DataClientToComm;
import com.lo23.common.interfaces.ihm.IhmToDataClient;
import com.lo23.common.user.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Stream;

import static com.lo23.data.Const.FILEPATH_ACCOUNTS;

/**
 * Manager Data client
 */
public class DataManagerClient
{
    /**
     * API de DataClient pour IHM
     */
    private DataClientToIhmApi dataClientToIhmApi;
    /**
     * API de DataClient pour Comm
     */
    private DataClientToCommApi dataClientToCommApi;
    //private IhmToDataClientApi ihmToDataClientApi;
    //private CommToDataClientApi commToDataClientApi;
    /**
     * Session courante
     */
    private Session sessionInfos;
    private UploadManager uploadManager;
    private DownloadManager downloadManager;

    /**
     * Constructeur de DataManagerClient
     */
    public DataManagerClient()
    {
        super();
        this.dataClientToCommApi = new DataClientToCommApi();
        this.dataClientToIhmApi = new DataClientToIhmApi(this);
    }

    public DataClientToComm getDataClientToComm(){
        return this.dataClientToCommApi;
    }

    /**
     * Connecte l'utilisateur au serveur après avoir vérifié ses identifiants
     * @param login login de l'utilisateur
     * @param password password de l'utilisateur
     */
    public void login(String login, String password)
    {
        File[] listOfUserFiles = new File("files/accounts").listFiles();

        String hashedPassword = hashPassword(password);
        
        // Etude de chaque fichier utilisateur
        for (File userFile : listOfUserFiles)
        {
            if (userFile.isFile())
            {
                try
                {
                    FileInputStream fileIn = new FileInputStream(userFile.getName());
                    ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                    Object obj = objectIn.readObject();
                    UserAccount comparisonAccount = (UserAccount) obj;
                    if(comparisonAccount.getLogin().equals(login))
                    {
                        if(comparisonAccount.checkPassword(hashedPassword))
                        {
                            UserStats userToConnect = (UserStats) comparisonAccount;

                            User currUser = (User) comparisonAccount;
                            sessionInfos.setCurrentUser(currUser);

                            // FIXME Pas la bonne interface appelée
//                            dataClientToCommApi.login(userToConnect, serverIP);
                        }
                    }
                }
                catch(IOException | ClassNotFoundException e)
                {
                    e.printStackTrace();
                }
            }
        }
        //TODO return error code saying that we found login but password didn't match
    }

    /**
     * Envoie une demande de déconnexion d'un utilisateur
     * @param user utilisateur qui se déconnecte
     * @param ip adresse IP du serveur
     */
    public void logout(User user, String ip)
    {
        User userToLogout = sessionInfos.getCurrentUser();
        // TODO send logout message to com
        /*try
        {
        requestLogout(userToLogout);
        }*/
        // TODO voir exceptions avec comm
        //catch()


        //TODO return to user logout successful
    }
    /**
     * Récupère l'API de DataClient pour IHM.
     * @return Référence vers l'API de DataClient pour IHM
     */
    public DataClientToIhmApi getDataClientToIhmApi ()
    {
        return this.dataClientToIhmApi;
    }

    /**
     * Sauvegarde un profil nouvellement créé en local
     * @param user descripteur d'utilisateur
     * @return vrai si sauvegarde avec succès
     */
    boolean saveUserInfo(UserAccount user)
    {
        boolean registerSuccess = true;
        try
        {
            // Création du flux vers le nouveau fichier
            FileOutputStream fileOut =
                    new FileOutputStream(
                            FILEPATH_ACCOUNTS+user.getLogin()+"_"+user.getId()+".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            // Sérialisation de l'utilisateur dans son fichier
            out.writeObject(user);
            out.close();
            fileOut.close();
        }
        catch (IOException i)
        {
            i.printStackTrace();
            registerSuccess = false;
        }

        return registerSuccess;
    }

    /**
     * Lit un fichier et le désérialise sous la forme d'un UserAccount, si compatible.
     * @param path Chemin du fichier à ouvrir (doit être un objet UserAccount sérialisé)
     * @return Objet UserAccount
     */
    private UserAccount openAccountFromFile (String path)
    {
        UserAccount account = null;
        try
        {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream((path)));
            account = (UserAccount) in.readObject();
            in.close();
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return account;
    }

    /**
     * Retourne le hash d'un mot de passe.
     * Utilise l'algorithme MD5 sans salage.
     * @param passwordToHash Mot de passe à hasher
     * @return Hash du mot de passe
     */
    String hashPassword (String passwordToHash)
    {
        try
        {
            // Crée une instance de la classe MessageDigest avec l'algorithme MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            // On donne à l'algorithme le mot de passe sous forme d'octets
            md.update(passwordToHash.getBytes());
            // On récupère le résultat de l'algorithme
            byte[] bytes = md.digest();
            // On convertit le résultat de l'algorithme en héxadécimal
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes)
            {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Vérifie l'existence d'un compte utilisateur sérialisé, dans le dossier adapté : {@link com.lo23.data.Const#FILEPATH_ACCOUNTS}
     * @param username Nom d'utilisateur du compte à vérifier
     * @return vrai si le compte existe, faux sinon
     */
    public boolean accountExists(String username) {
        long numberOfMatchingFiles = 0;

        //Itère sur les fichiers de FILEPATH_ACCOUNTS et compte le nombre de fichiers qui ont "username_" en début de nom.
        try (Stream<Path> paths = Files.walk(Paths.get(FILEPATH_ACCOUNTS))) {
            numberOfMatchingFiles = paths
                    .filter(Files::isRegularFile)
                    .map(Path::toString)
                    .map(s -> s.substring(FILEPATH_ACCOUNTS.length()))
                    .filter(path -> path.matches("^(" + username + "_)(.)*$"))
                    .count();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Si au moins un fichier correspond, on retourne vrai
        return numberOfMatchingFiles > 0;
    }
    
}