package com.lo23.data.client;

import com.lo23.common.user.UserAccount;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * API data client utilisable par les modules IHM et Comm
 */
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
     * Sauvegarde un profil nouvellement créé en local
     * @param user descripteur d'utilisateur
     * @return vrai si sauvegarde avec succès
     */
    public boolean saveUserInfo(UserAccount user)
    { // TODO A METTRE EN PRIVE APRES TESTS
        boolean correct = true;
        try
        {
            // Création du flux vers le nouveau fichier
            FileOutputStream fileOut =
                    new FileOutputStream(
                            "files/accounts/"+user.getLogin()+"_"+user.getId()+".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            // Sérialisation de l'utilisateur dans son fichier
            out.writeObject(user);
            out.close();
            fileOut.close();
        }
        catch (IOException i)
        {
            i.printStackTrace();
            correct = false;
        }

        return correct;
    }
}