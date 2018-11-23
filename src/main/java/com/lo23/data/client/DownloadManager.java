package com.lo23.data.client;

import com.lo23.common.filehandler.FileHandler;

import java.io.File;
import java.util.Vector;

public class DownloadManager
{
    private Vector<FileHandler> inQueue;
    private Vector<DownloadHandler> inProgress;

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
}
