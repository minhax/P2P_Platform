package com.lo23.common.interfaces.ihm;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.user.User;
import com.lo23.common.user.UserAccount;
import com.lo23.common.user.UserIdentity;

import java.util.List;

public interface IhmToDataClient {

    /**
     *
     * @return
     */
    public static List<FileHandlerInfos> getAvailableFiles()
    {
        return null;
    }

    public static List<FileHandlerInfos> getUploadedFiles()
    {
        return null;
    }

    public static List<UserIdentity> regreshConnectedUsers()
    {
        return null;
    }

    public static registerForm requestRegisterForm()
    {
        return null;
    }

    public static UserAccount requestAccountData(User user)
    {
        return null;
    }

}
