package com.lo23.data.client;

import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.interfaces.comm.CommToDataClient;
import com.lo23.common.user.User;
import com.lo23.common.user.UserIdentity;
import com.lo23.data.Const;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Vector;

class DownloadManager
{
    /**
     * File des fichiers en attente
     */
    private Vector<FileHandler> inQueue;
    /**
     * Liste des fichiers in progress
     */
    private Vector<FileHandler> inProgress;
    /**
     * API de Comm pour DataClient
     */
    private CommToDataClient commToDataClientAPI;
    /**
     * Manager de DataClient
     */
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

    /**
     * Constructeur de DownloadManager
     */
    DownloadManager(){
        this.inQueue = new Vector<FileHandler>();
        this.inProgress = new Vector<FileHandler>();
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
        File filePart = new File("files/fileparts" + file.getHash() + "part" + part);
        try(FileInputStream fileIn = new FileInputStream(filePart)){
            byte[] data = new byte[Const.FILEPART_SIZE];
            data = Files.readAllBytes(filePart.toPath());
            // TODO send filePart to comm
            // this.getCommToDataClientAPI();
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

    /**
     * Enregistre une nouvelle partie d'un fichier
     * @param fileHandler descripteur du fichier
     * @param blocNumber numéro du bloc
     * @param data données contenues dans le bloc
     */
    void storeNewFilePart(FileHandler fileHandler, long blocNumber, byte[] data) {
        // TODO : store the fileParts, and check if it's completed or not
        long nbBlocks = fileHandler.getNbBlocks();
        // Check how many parts exists
        File dir = new File("files/fileparts");
        File[] files = dir.listFiles((d, name) -> name.startsWith(fileHandler.getHash()));
        long existingPartsNumber = files.length;

        try (FileOutputStream fos = new FileOutputStream("/files/fileparts/" + fileHandler.getHash() + "." + blocNumber)) {
            fos.write(data);
        } catch (IOException e) {
            System.out.println("Error when storing file part in disk");
            e.printStackTrace();
        }

        // All parts collected
        if (existingPartsNumber == nbBlocks) {
            this.mergeFileparts(fileHandler);
        } else if (existingPartsNumber > nbBlocks) {
            throw new RuntimeException("Error in DownloadManager : received too many parts for file : " + fileHandler.getTitle());
        }
    }

    /**
     * Fusionne les parties d'un fichier
     * @param fileToBuild descripteur du fichier à reconstituer
     */
    void mergeFileparts (FileHandler fileToBuild)
    {

        String title = fileToBuild.getTitle().replaceAll("\\W+", "_");

        try(FileOutputStream fileBuilt = new FileOutputStream("files/downloads/" + title + "." + fileToBuild.getType());) {
            byte[] segment = new byte[Const.FILEPART_SIZE]; // Tableau d'octets de la taille d'un filepart
            int bytesRead;
            for (int i = 0; i < fileToBuild.getNbBlocks(); i++)
            {
                try(FileInputStream filepart = new FileInputStream("files/fileparts/" + fileToBuild.getHash() + ".part" + i)){
                    bytesRead = filepart.read(segment);
                    fileBuilt.write(segment, 0, bytesRead);
                }

            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

    }
}
