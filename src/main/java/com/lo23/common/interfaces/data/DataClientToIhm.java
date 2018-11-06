package com.lo23.common.interfaces.data;

import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.user.UserIdentity;

import java.util.Vector;

public interface DataClientToIhm
{
    /**
     * Demande à DataClient la source d'un fichier
     * @param file fichier en question
     * @param user // TODO
     */
    public void requestFileLocation(FileHandler file, UserIdentity user);



}
