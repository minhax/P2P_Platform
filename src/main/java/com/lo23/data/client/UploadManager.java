package com.lo23.data.client;

import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.data.Const;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Classe permettant de gérer l'upload de fichiers par un utilisateur.
 */
class UploadManager
{
    /**
     * Prépare un fichier donné par l'utilisateur avant de prévenir le serveur que l'on est une source.
     * @param path Chemin du fichier sur le disque
     * @param title Titre du fichier
     * @param desc Description du fichier
     * @return Handler avec les métadonnées du fichier
     */
    FileHandlerInfos prepareToShare (String path, String title, String desc) {
        File fileToShare = new File(path);
        // On récupère le hash du contenu du fichier
        String hash = hashFile(fileToShare);
        // On calcule le nombre de blocks du fichier selon sa taille
        // Nombre de blocks = taille / taille d'un block
        int sizeOfFile = (int) ((fileToShare.length() / Const.FILEPART_SIZE) +
                ((fileToShare.length() % Const.FILEPART_SIZE) >0 ? 1 : 0));
        // On récupère l'extension du fichier
        String extension = "";
        int i = fileToShare.getName().lastIndexOf('.');
        if (i > 0) {
            extension = fileToShare.getName().substring(i+1);
        }
        // On instancie le handler associé
        FileHandlerInfos handler = new FileHandlerInfos(hash, title, fileToShare.length(), extension, sizeOfFile, desc);
        // On découpe le fichier en plusieurs parties pour le téléchargement
        segmentFile(path, handler);
        return handler;
    }

    /**
     * Découpe un fichier en plusieurs parties
     * @param path Chemin du fichier sur le disque
     * @param handler Handler de métadonnées du fichier
     */
    void segmentFile (String path, FileHandler handler)
    {
        try(FileInputStream toSplit = new FileInputStream(path))
        {
            byte[] segment = new byte[Const.FILEPART_SIZE]; // Tableau d'octets de la taille d'un filepart
            int part = 0; // Numéro de la partie actuelle
            int bytesRead;
            while ((bytesRead = toSplit.read(segment)) != -1) { // Tant qu'on  lit des octets dans le fichier source
                // On crée le fichier .part
                try(FileOutputStream filepart = new FileOutputStream("files/fileparts/" +
                        handler.getHash() + ".part" + part)){
                    // On écrit le contenu au format binaire
                    filepart.write(segment, 0, bytesRead);
                    filepart.close();
                    part++;
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Calcule le hash d'un fichier
     * @param fileToHash Fichier à hasher
     * @return Hash MD5 du contenu du fichier
     */
    private String hashFile (File fileToHash)
    {
        try(FileInputStream inputStream = new FileInputStream(fileToHash))
        {
            MessageDigest digest = MessageDigest.getInstance("MD5"); // On prévient que l'on utilise l'algorithme MD5

            // On crée un tableau d'octets pour lire le fichier par blocs
            byte[] byteArray = new byte[1024];
            int bytesCount = 0;
            // On lit les données du fichier et on les donne au digest
            while ((bytesCount = inputStream.read(byteArray)) != -1) {
                digest.update(byteArray, 0, bytesCount);
            }
            inputStream.close();

            // On récupère les octets du hash
            byte[] bytes = digest.digest();
            // Le tableau d'octets est au format décimal, on le convertit en hexadécimal
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            // On retourne le hash complet
            return sb.toString();
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
