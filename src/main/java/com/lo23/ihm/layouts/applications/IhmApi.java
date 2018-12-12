package com.lo23.ihm.layouts.applications;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.interfaces.ihm.IhmToDataClient;
import com.lo23.common.user.UserIdentity;
import com.lo23.ihm.layouts.controllers.MainController;

import java.util.List;

public class IhmApi implements IhmToDataClient {

    private MainController controller;
    public IhmApi(MainController controller) {
        this.controller = controller;
    }

    @Override
    public void UpdateAvailableFiles(FileHandlerInfos fileInfo, UserIdentity user) {

    }

    @Override
    public void sendUpdates(UserIdentity userInfo, List<FileHandlerInfos> fileInfo) {

    }

    @Override
    public void addFileSource(FileHandlerInfos fileInfo, UserIdentity user) {

    }

    @Override
    public UserIdentity updateFileDownload(UserIdentity userInfo) {
        return null;
    }

    @Override
    public void showPercentageComplete(float percentage) {

    }
}
