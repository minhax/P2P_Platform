package com.lo23.data.client;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.user.User;
import com.lo23.common.user.UserIdentity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DataClientToCommApiTests {
    private DataClientToCommApi api;
    private DataManagerClient dataManagerClient;

    @BeforeEach
    void createElementsBeforeTests()
    {
        this.dataManagerClient = new DataManagerClient();
        this.api = new DataClientToCommApi(dataManagerClient);

    }

    /*
    @Test
    void connectingOneUserShouldWorkAlright()
    {
        UserIdentity u1 = new UserIdentity("login1", "prenom1", "nom1", 12);
        FileHandlerInfos f1 = new FileHandlerInfos("hash1", "titre1", 12, "type1", 2, "desc1");
        FileHandlerInfos f2 = new FileHandlerInfos("hash2", "titre2", 12, "type2", 3, "desc2");
        Vector<FileHandlerInfos> vecteurFiles = new Vector<>();
        vecteurFiles.add(f1);
        vecteurFiles.add(f2);
        HashMap<UserIdentity, Vector<FileHandlerInfos>> infos = new HashMap<>();
        infos.put(u1, vecteurFiles);
        this.api.notifyOtherUserConnectedToAll(infos);

        assertTrue(this.dataManagerClient.getSessionInfos().getDirectory().getProposedFiles().size()==2 &&
                this.dataManagerClient.getSessionInfos().getOtherLoggedUsers().size()==1);
    }
    */
}
