package com.lo23.data.client;

import com.lo23.common.interfaces.data.DataClientToComm;
import com.lo23.common.user.UserAccount;
import com.lo23.common.user.UserStats;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DataManagerClient
{
    private DataClientToComm dataClientToComm;

    /**
     * Constructeur de DataManagerClient
     */
    public DataManagerClient()
    {
        super();
        this.dataClientToComm = new DataClientToComm(this);
    }

    public DataClientToComm getDataClientToComm(){
        return this.dataClientToComm;
    }

    /**
     * Connecte l'utilisateur au serveur après avoir vérifié ses identifiants
     * @param login login de l'utilisateur
     * @param password password de l'utilisateur
     */
    public void login(String login, String password)
    {
        // TODO hash password for comparison
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
                            // TODO get IP to connect to.
                            String serverIP  = "";

                            UserStats userToConnect = (UserStats) comparisonAccount;

                            dataClientToComm.login(userToConnect, serverIP);
                        }
                    }
                }
                catch(IOException |ClassNotFoundException e)
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
        // TODO send logout message to com
        // réutiliser variables user et ip utilisés dans login?
        // requestLogout(User user, String ip)


        //TODO return to user logout successful

    }
}