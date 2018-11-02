package com.lo23.common.interfaces;

import users.*;
import java.util.UUID;
import fileHandler.*;
import communicationManager.*;

public interface CommToDataClient
{

	/**
     * Demande à Data des informations sur un utilisateur.
     * @param user utilisateur que l'on recherche
     */
	public void searchUser(UserIdentity user);


	/**
	 * Envoie les informations relatives à un utilisateur connecté
	 * @param user utilisateur connecté
	 * @param files liste des fichiers qu'il met à disposition du réseau
	 */
	public void sendConnectedUserUpdate(UserIdentity user, List<FileHandler> files);

	/**
	 * Envoie les informations relatives à un utilisateur déconnecté
	 * @param user utilisateur que l'on recherche
	 * @param files liste des fichiers qu'il mettait à disposition du réseau
	 */
	public void sendDisconnectedUserUpdate(UserIdentity user, List<FileHandler> files);

	/**
	 * Permet de récupérer un fichier
	 * @param user utilisateur qui demande le fichier
	 * @param file fichier que l'on veut récupérer
	 */
	public void getFile(UserIdentity user, FileHandler file);

	/**
	 * Envoie un fichier
	 * @param fileInfo fichier à transmettre
	 */
	public void sendFile(FileHandlerInfo fileInfo);

	/**
	 * Enregistre un fichier
	 * @param fileInfo fichier à enregistrer
	 */
	public void saveFile(FileHandlerInfo fileInfo)

	/**
     * Recevoir la liste des utilisateurs disposant d'un fichier
	 * @param users liste des utilisateurs qui disposent du fichier
	 */
	public void receiveFileLoc(List<UserIdentity> users);

	/**
	 * Envoie les modifications d'un fichier
	 * @param file fichier qui est modifié
	 * @param user utilisateur qui a modifié
	 */
	public void saveFileChanges(FileHandler file, User user);

	/**
	 * Envoie les modifications d'un compte utilisateur
	 * @param user utilisateur dont on modifie le compte
	 */
	public void sendUpdatedAccount(UserIndentity user);

	/**
	 * Envoie les informations concernant un fichier
	 * @param file fichier dont on transmet les informations
	 * @param user utilisateur qui fournit les informations
	 */
	public void sendFileInfo(FileHandler file, UserId user);

	/**
	 * Envoie un fichier qui a été mis à jour
	 * @param file fichier mis à jour
	 */
	public void sendUpdatedFile(FileHandler file);

	/**
	 * Envoie des informations relatives à un nouveau noeud proposant un fichier au téléchargement
	 * @param file Fichier disponible
	 * @param user utilisateur mettant à disposition le fichier
	 */
	public void sendNewFileSource(FileHandler file, UserId user);

	/**
	 * Permet d'uploader un fichier
	 * @param file fichier à uploader
	 * @param user Utilisateur qui souhaite uplaoder
	 */
	public void UploadFile(FileHandler file, UserId user);

}
