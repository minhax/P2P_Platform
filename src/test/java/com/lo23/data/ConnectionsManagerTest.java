package com.lo23.data;

import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.user.UserIdentity;
import com.lo23.data.server.ConnectionsManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConnectionsManagerTest
{
    private ConnectionsManager connectionsManager;
    private UserIdentity user1;
    private UserIdentity user2;
    /*
    private FileHandlerInfos file1;
    private FileHandlerInfos file2;
    private FileHandlerInfos file3;
    */

    @BeforeEach
    void createConnectionsManagerAndUsersForTests()
    {
        this.user1 = new UserIdentity("login1", "user", "one", 20);
        this.user2 = new UserIdentity("login2", "user", "two", 50);
        this.connectionsManager = new ConnectionsManager();
        this.connectionsManager.connectUser(user1);
        this.connectionsManager.connectUser(user2);
        /*
        this.file1 = new FileHandlerInfos("hash1", "title1", 1, "type1", 1, "description 1");
        this.file2 = new FileHandlerInfos("hash2", "title2", 2, "type2", 2, "description 2");
        this.file3 = new FileHandlerInfos("hash3", "title3", 3, "type3", 3, "description 3");
        */
    }

    @Test
    void testGetterShouldReturnConnectedUsers1And2()
    {
        assertTrue(this.connectionsManager.getConnectedUsers().contains(user1)
                && this.connectionsManager.getConnectedUsers().contains(user2)
                && this.connectionsManager.getConnectedUsers().size()==2);
    }

    @Test
    void testGetterShouldReturnUser1Only()
    {
        this.connectionsManager.disconnectUser(user2);
        assertTrue(this.connectionsManager.getConnectedUsers().contains(user1)
                && !this.connectionsManager.getConnectedUsers().contains(user2)
                && this.connectionsManager.getConnectedUsers().size()==1);
    }
}
