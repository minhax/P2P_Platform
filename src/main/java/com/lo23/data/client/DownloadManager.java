package com.lo23.data.client;

import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.interfaces.comm.CommToDataClient;
import com.lo23.common.user.User;
import com.lo23.common.user.UserIdentity;
import com.lo23.data.Const;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Vector;

class DownloadManager
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
    void download(FileHandler fileToDownload)
    {
        long nbBlocks = fileToDownload.getNbBlocks();

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

    /**
     * Fonction qui permet d'obtenir le filePart numéro "part" du fichier
     * "file" pour l'envoyer à userAsking.
     * @param userAsking l'utilisateur qui demande le fichier
     * @param userSource l'utilisateur qui fournit le filePart
     * @param file les métadonnées du ficheir en question
     * @param part l'index de la partie du fichier.
     */
    void getFilePart(User userAsking, User userSource, FileHandler file, long part){
        try{
            byte[] data = new byte[Const.FILEPART_SIZE];
            File filePart = new File("files/fileparts" + file.getHash() + "part" + part);
            FileInputStream fileIn = new FileInputStream(filePart);
            data = Files.readAllBytes(filePart.toPath());
            // TODO send filePart to comm
            // this.getCommToDataClientAPI();
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   /**
     * Méthode de reprise sur erreur dans le cas ou comm ne peut pas nous fournir
     * le filePart dans le temps imparti
     * @param userAsking l'utilisateur qui demande le filePart
     * @param userSource l'utilisateur qui n'a pas réussi le filePart
     * @param fileToDownload le fichier à télécharger
     * @param part la partie du fichier à télécharger
     */
    void requestRetryGetFilePart(User userAsking, User userSource, FileHandler fileToDownload, long part) {
        Vector<UserIdentity> sources = this
                .getDataManagerClient()
                .getSessionInfos()
                .getDirectory()
                .getUsersThatProposeFile(fileToDownload);

        sources.remove(userSource);

        // TODO send  query to comm again
    }

   void storeNewFilePart(FileHandler fileHandler, long blocNumber, byte[] data) {
        // TODO : store the fileParts, and check if it's completed or not
        long nbBlocks = fileHandler.getNbBlocks();

        // check how many parts exist
        //todo regex and check number of existing parts, and remove hardcore
        long existingPartsNumber = 0;

        //all parts collected
        if (existingPartsNumber == nbBlocks) {
            this.mergeFileparts(fileHandler);
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

    void mergeFileparts (FileHandler fileToBuild)
    {
        try {
            byte[] segment = new byte[Const.FILEPART_SIZE]; // Tableau d'octets de la taille d'un filepart
            String title = fileToBuild.getTitle().replaceAll("\\W+", "_");
            FileOutputStream fileBuilt = new FileOutputStream("files/downloads/" + title + "." + fileToBuild.getType());
            int bytesRead;
            for (int i = 0; i < fileToBuild.getNbBlocks(); i++)
            {
                FileInputStream filepart = new FileInputStream("files/fileparts/" + fileToBuild.getHash() + ".part" + i);
                bytesRead = filepart.read(segment);
                fileBuilt.write(segment, 0, bytesRead);
                filepart.close();
            }
            fileBuilt.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
