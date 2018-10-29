package com.lo23.data.client;

import com.lo23.common.user.UserAccount;
import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DataManagerClient
{
    /**
     * Constructeur de DataManagerClient
     */
    public DataManagerClient()
    {
        super();
    }

    /**
     * Connecte l'utilisateur au serveur après avoir vérifié ses identifiants
     * @param login login de l'utilisateur
     * @param password password de l'utilisateur
     */
    public void login(String login, String password)
    {
        // TODO hash password for comparison
        boolean loginSuccess = false;
        File[] listOfUserFiles = new File("files/accounts").listFiles();

        for (File userFile : listOfUserFiles) {
            if (userFile.isFile()) {
                try{
                    FileInputStream fileIn = new FileInputStream(userFile.getName());
                    ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                    Object obj = objectIn.readObject();
                    UserAccount comparisonAccount = (UserAccount) obj;
                    if(comparisonAccount.getLogin().equals(login)){
                        loginSuccess = true;
                        if(comparisonAccount.checkPassword(password)) {

                            // TODO send login message to comm

                        }
                    }
                }
                catch(IOException |ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        if(loginSuccess){
            //TODO return error code saying that we found login but password didn't match
        }
    }
}