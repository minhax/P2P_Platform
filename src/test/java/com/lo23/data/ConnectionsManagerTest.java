package com.lo23.data;

import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.user.User;
import com.lo23.common.user.UserIdentity;
import com.lo23.common.user.UserStats;
import com.lo23.data.server.ConnectionsManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConnectionsManagerTest
{
    private ConnectionsManager connectionsManager;
    private UserIdentity user1;
    private UserIdentity user2;
    private UserStats userStats1;
    private UserStats userStats2;
    private FileHandlerInfos file1;
    private FileHandlerInfos file2;

    @BeforeEach
    void createConnectionsManagerAndUsersForTests()
    {
        this.user1 = new UserIdentity("login1", "user", "one", 20);
        this.user2 = new UserIdentity("login2", "user", "two", 50);
        this.userStats1 = new UserStats(this.user1.getLogin(), this.user1.getFirstName(), this.user1.getLastName(), this.user1.getAge());
        this.userStats2 = new UserStats(this.user2.getLogin(), this.user2.getFirstName(), this.user2.getLastName(), this.user2.getAge());
        this.connectionsManager = new ConnectionsManager();
        this.connectionsManager.connectUser(user1);
        this.connectionsManager.connectUser(user2);
        this.file1 = new FileHandlerInfos("hash1", "title1", 1, "type1", 1, "description 1");
        this.file2 = new FileHandlerInfos("hash2", "title2", 2, "type2", 2, "description 2");
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

    @Test
    void test2UsersAddingADifferentFileEachShouldMakeDirectorySize2()
    {
        this.connectionsManager.addFileToDirectory(userStats1, file1);
        this.connectionsManager.addFileToDirectory(userStats1, file2);
        assertTrue(this.connectionsManager.getProposedFiles().size()==2);
    }

    @Test
    void test2UsersAddingTheSameFileShouldMakeDirectorySize1()
    {
        this.connectionsManager.addFileToDirectory(userStats1, file1);
        this.connectionsManager.addFileToDirectory(userStats2, file1);
        assertTrue(this.connectionsManager.getProposedFiles().size()==1);
    }

    @Test
    void test1UserAddingAlreadyProposedFileShouldThrowException()
    {
        this.connectionsManager.addFileToDirectory(userStats1, file1);
        assertThrows(IllegalStateException.class, () ->
        {
            this.connectionsManager.addFileToDirectory(userStats1, file1);
        });
    }
}
