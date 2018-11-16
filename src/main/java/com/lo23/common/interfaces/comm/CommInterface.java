package com.lo23.common.interfaces.comm;


import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.user.UserIdentity;

import java.util.UUID;


public interface CommInterface 
{
	
	/**
     * Envoie à Data les informations concernant un utilisateur
     * @param ui informations sur l'utilisateur (UserIdentity)
     */
	public void sendUserInfo(UserIdentity ui);
	
	
	/**
     * Demande à Data les informations concernant un utilisateur
     * @param userid clef de l'utilisateur dont on recherche les infos
     */
	public void requestUserInfo(UUID userid);
	//peut-on récup les infos à partir de la clef ? A-t-on accès à la clef ?
	
	
	/**
     * Notifie Data de la nécessité d'effectuer une màj sur le client
     * @param 
     */
	public void requestUpdateClient();
	//comment (ou doit-on) mettre en paramètre l'info à actualiser ? 
	// ce qui peut être updaté : métadonnées, infos fichiers (y compris notes+commentaires), infos utilisateurs, liste des utilisateurs connectés, liste des fichiers proposés par un utilisateur
	
	
	/**
     * Notifie Data de la nécessité d'effectuer une màj sur le serveur central
     * @param 
     */
	public void requestUpdateServer();
	// comment mettre en paramètre l'info à actualiser?
	// peut être actualisé : métadonnées
	
	
	/**
     * Envoie à Data les informations concernant un fichier
     * @param fh contenus du fichier 
     * @param user Utilisateur modifiant le fichier
     */
	public void sendFileInfo (FileHandler fh, UserIdentity user);
	
	/**
     * Demande à Data les informations concernant un utilisateur
     * @param fileId clef identifiant un fichier
     */
	
	public void requestFileInfo(UUID fileId);
	//même question que pour requestUserInfo
}
