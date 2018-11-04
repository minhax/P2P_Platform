package com.lo23.data.client;

import com.lo23.common.interfaces.data.DataClientToIhm;
import com.lo23.common.user.UserAccount;

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
     */
    public void createAccount (String login, String password, String firstname, String lastname, int age)
    { // TODO: définir quel retour pour IHM (void d'après diagramme de séquence)
        String hashedPassword = host.hashPassword(password);
        // TODO: try...catch des erreurs ci dessous
        UserAccount user = new UserAccount(login, firstname, lastname, age, hashedPassword);
        if (!host.saveUserInfo(user))
        {
            // TODO: Lever une exception ?
            System.err.println("Erreur lors de la création du compte");
        }
        // Connecter l'utilisateur
    }
}
