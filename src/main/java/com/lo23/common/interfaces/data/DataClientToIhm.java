package com.lo23.common.interfaces.data;

import com.lo23.common.exceptions.DataException;
import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.user.UserIdentity;

import java.util.Vector;

public interface DataClientToIhm
{

    /**
     * Crée un compte à partir des informations de base.
     * @param login Login de l'utilisateur
     * @param password Mot de passe (en clair) de l'utilisateur
     * @param firstname Prénom de l'utilisateur
     * @param lastname Nom de l'utilisateur
     * @param age Age de l'utilisateur
     * @throws DataException Exception lors de la création du compte
     */
    void createAccount (String login, String password, String firstname, String lastname, int age) throws DataException;

    /**
     * Demande à DataClient la source d'un fichier
     * @param file fichier en question
     * @param user // TODO
     */
    public void requestFileLocation(FileHandler file, UserIdentity user);




}
