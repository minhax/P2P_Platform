package com.lo23.common.interfaces.data;

import com.lo23.common.Comment;
import com.lo23.common.Rating;
import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.user.User;
import com.lo23.common.user.UserIdentity;
import com.lo23.common.user.UserStats;

public interface DataClientToComm
{
    /**
     * Envoie à DataClient les sources d'un fichier
     * @param sources sources du fichier
     */
    void receiveFileLoc(Vector<UserIdentity> sources);

    /**
     *
     * @param user
     * @param file
     * @return
     */
    FileHandlerInfos getFile(UserIdentity user, FileHandler file);

    /**
     *
     * @param file
     */
    void saveFile(FileHandlerInfos file);

    /**
     * Envoie à DataClient le fichier devant
     * être partagé
     * @param file fichier à partager
     */
    void sendFileInfo(FileHandler file);


}
