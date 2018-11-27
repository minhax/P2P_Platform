package com.lo23.data.client;

import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.interfaces.comm.CommToDataClient;
import com.lo23.common.user.User;
import com.lo23.common.user.UserIdentity;
import com.lo23.data.Const;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Vector;

public class DownloadManager
{
    private Vector<FileHandler> inQueue;
    private Vector<DownloadHandler> inProgress;
    private CommToDataClient commToDataClientAPI;
    private DataManagerClient dataManagerClient;

    public DataManagerClient getDataManagerClient() {
        return dataManagerClient;
    }

    public void setDataManagerClient(DataManagerClient dataManagerClient) {
        this.dataManagerClient = dataManagerClient;
    }

    public CommToDataClient getCommToDataClientAPI() {
        return commToDataClientAPI;
    }

    public void setCommToDataClientAPI(CommToDataClient commToDataClientAPI) {
        this.commToDataClientAPI = commToDataClientAPI;
    }

    DownloadManager(){
        this.inQueue = new Vector<FileHandler>();
        this.inProgress = new Vector<DownloadHandler>();
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

    public Vector<DownloadHandler> getInProgress() {
        return this.inProgress;
    }

    /**
     * Demande le téléchargement d'un fichier par blocs en demandant à chaque
     * source le même nombre de blocs.
     * @param fileToDownload le fichier à télécharger.
     */
    public void download(FileHandler fileToDownload)
    {
        long nbBlocks = (long) Math.ceil(fileToDownload.getSize() / Const.FILEPART_SIZE);

        Vector<UserIdentity> sources = this
                .getDataManagerClient()
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

    public void storeNewFilePart(FileHandler fileHandler, long blocNumber, byte[] data) {
        // TODO : store the fileParts, and check if it's completed or not
        long nbBlocks = (long) Math.ceil(fileHandler.getSize() / Const.FILEPART_SIZE);

        // check how many parts exist
        //todo regex and check number of existing parts, and remove hardcore
        long existingPartsNumber = 0;

        //all parts collected
        if (existingPartsNumber == nbBlocks) {

        } else if (existingPartsNumber < nbBlocks) {
            // Store the part in the disk
            try (FileOutputStream fos = new FileOutputStream("/files/fileparts/" + fileHandler.getHash() + "." + blocNumber)) {
                fos.write(data);
            } catch (IOException e) {
                System.out.println("Error when storing file part in disk");
                e.printStackTrace();
            }

        } else {
            throw new RuntimeException("Error in DownloadManager : received too many parts for file : " + fileHandler.getTitle());
        }


    }
}
