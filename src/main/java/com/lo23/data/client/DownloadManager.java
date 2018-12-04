package com.lo23.data.client;

import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.interfaces.comm.CommToDataClient;
import com.lo23.common.user.User;
import com.lo23.common.user.UserIdentity;
import com.lo23.data.Const;

import java.io.*;
import java.nio.file.Files;
import java.util.Vector;

public class DownloadManager
{
    private Vector<FileHandler> inQueue;
    private Vector<FileHandler> inProgress;
    private DataManagerClient managerClient;

    DownloadManager (DataManagerClient host) {
        this.inQueue = new Vector<>();
        this.inProgress = new Vector<>();
        this.managerClient = host;
    }

    public Vector<FileHandler> getInQueue()
    {
        return this.inQueue;
    }

    public void addToQueue(FileHandler fileToAdd)
    {
        this.inQueue.add(fileToAdd);
    }

    public void removeFromQueue(FileHandler fileToRemove)
    {
        this.inQueue.remove(fileToRemove);
    }

    public Vector<FileHandler> getInProgress() {
        return this.inProgress;
    }

    /**
     * Demande le téléchargement d'un fichier par blocs en demandant à chaque
     * source le même nombre de blocs.
     * @param fileToDownload le fichier à télécharger.
     */
    public void splitDownload(FileHandler fileToDownload)
    {
        long nbBlocks = (long) Math.ceil((float)fileToDownload.getSize() / (float)Const.FILEPART_SIZE);

        Vector<UserIdentity> sources = this
                .managerClient
                .getSessionInfos()
                .getDirectory()
                .getUsersThatProposeFile(fileToDownload);

        long nbSources = (long) sources.size();
        /*
        On divise et rajoute 1, comme ça on a un nombre de blocs identiques
        pour les n-1 premières sources et le dernier en a moins.
         */
        long nbBlocksPerSource = nbBlocks / nbSources;
        long nbBlocksRest = nbBlocks % nbSources;

        for(int i = 0; i < nbSources;i++){
            long nbToSend = nbBlocksPerSource;
            if(nbBlocksRest > 0){
                nbToSend++;
            }
            // TODO demander à comm de demander à l'user i les blocs i
            // this.getCommToDataClientAPI();
        }
    }

    /**
     * Fonction qui permet d'obtenir le filePart numéro "part" du fichier
     * "file" pour l'envoyer à userAsking.
     * @param userAsking l'utilisateur qui demande le fichier
     * @param userSource l'utilisateur qui fournit le filePart
     * @param file les métadonnées du ficheir en question
     * @param part l'index de la partie du fichier.
     */
    public void getFilePart(User userAsking, User userSource, FileHandler file, long part){
        try{
            byte[] data = new byte[Const.FILEPART_SIZE];
            File filePart = new File("files/fileparts" + file.getHash() + "part" + part);
            FileInputStream fileIn = new FileInputStream(filePart);
            Files.readAllBytes(filePart.toPath());
            // TODO send filePart to comm
            // this.getCommToDataClientAPI();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
