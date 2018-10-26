package com.lo23.common.interfaces.ihm;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.user.User;
import com.lo23.common.user.UserAccount;
import com.lo23.common.user.UserIdentity;

import java.util.List;

public interface IhmToDataClient {

    /**
     * Demande à Data les champs que l'utilisateur devra renseigner
     * @return registerForm
     */
    public registerForm requestRegisterForm();

    /**
     * IHM envoie les informations de l'utilisateur à Data pour la création de compte en local
     * @param username nom d'utilisateur
     * @param password mot de passe
     * @param serverIP adresse du serveur
     * @param userInfo informations de l'utilisateur
     */
    public void requestRegisterForm(String username, String password, String serverIP, String userInfo);

    /**
     * IHM envoie les informations de l'utilisateur à Data pour les vérifications pour connexion
     * @param username nom d'utilisateur
     * @param password mot de passe
     * @param serverIP adresse du serveur
     * @param userInfo informations de l'utilisateur
     */
    public void checkAccount(String username, String password, String serverIP, String userInfo);

    /**
     * IHM transmet à Data la demande de déconnexion
     */
    public void logout();

    /**
     * IHM récupère les informations du compte pour l'exportation
     * @param user informations du compte
     * @return UserAccount
     */
    public UserAccount requestAccountData(User user);

    /**
     * IHM envoie les informations du compte pour l'importation
     * @param user
     */
    public void storeDataAccount(UserAccount user);

    /**
     * IHM récupère les informations du compte pour la modification
     * @return UserAccount
     */
    public UserAccount requestAccountInfo();

    /**
     * IHM envoie à Data les données du compte modifié
     * @param user : nouvelles données d'utilisateur (UserAccount)
     */
    public void submitUserChanges(UserAccount user);



}
