package com.lo23.data.server;

import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.user.User;
import com.lo23.common.user.UserIdentity;
import com.lo23.common.user.UserStats;
import com.lo23.data.server.ConnectionsManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void testConnectSameUserTwiceShouldThrowException()
    {
        assertThrows(IllegalStateException.class, () ->
        {
            this.connectionsManager.connectUser(user1);
        });
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
    void testGetterShouldReturnNoConnectedUsers()
    {
        this.connectionsManager.disconnectUser(user1);
        this.connectionsManager.disconnectUser(user2);
        assertTrue(this.connectionsManager.getConnectedUsers().size()==0);
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

    @Test
    void testRemovingOnlyDirectoryFileSourceShouldReturnEmptyDirectory()
    {
        this.connectionsManager.addFileToDirectory(userStats1, file1);
        this.connectionsManager.removeFileSourceFromDirectory(userStats1, file1);
        assertTrue(this.connectionsManager.getProposedFiles().size()==0);
    }

    @Test
    void testRemovingOneOnManySourcesShouldReturnNonEmptyDirectory()
    {
        this.connectionsManager.addFileToDirectory(userStats1, file1);
        this.connectionsManager.addFileToDirectory(userStats2, file1);
        this.connectionsManager.removeFileSourceFromDirectory(userStats1, file1);
        assertTrue(this.connectionsManager.getProposedFiles().size()==1);
    }

    @Test
    void testRemovingAOneSourceFileShouldReturnNonEmptyDirectory()
    {
        this.connectionsManager.addFileToDirectory(userStats1, file1);
        this.connectionsManager.addFileToDirectory(userStats2, file2);
        this.connectionsManager.removeFileSourceFromDirectory(userStats1, file1);
        assertTrue(this.connectionsManager.getProposedFiles().size()==1);
    }

    @Test
    void modificationsOnUserIdentityShouldBeSavedInConnectedUsers()
    {
        UserIdentity updatedUser1 = user1;
        updatedUser1.setAge(12);
        updatedUser1.setFirstName("Habitica");
        updatedUser1.setLastName("Master");
        this.connectionsManager.modifyConnectedUser(updatedUser1);
        assertTrue(this.connectionsManager.getConnectedUsers().get(0).getAge()==12 &&
                this.connectionsManager.getConnectedUsers().get(0).getFirstName().equals("Habitica") &&
                this.connectionsManager.getConnectedUsers().get(0).getLastName().equals("Master") &&
                this.connectionsManager.getConnectedUsers().get(0).getId()==user1.getId());
    }

    @Test
    void modificationsOnNonConnectedUserShouldThrowException()
    {
        UserIdentity nonConnectedUser = new UserIdentity("loginN", "PrenomN", "NomN", 8);
        assertThrows(IllegalStateException.class, () ->
        {
            this.connectionsManager.modifyConnectedUser(nonConnectedUser);
        });
    }

    @Test
    void shoulReturnDirectory(){
        DirectoryUserFiles d = this.connectionsManager.getDirectory();
        assertEquals(d, this.connectionsManager.getDirectory());
    }

    @Test
    void shouldNotDisconnectNotConnectedUser(){
        UserIdentity u = new UserIdentity("login", "First", "Last", 66);
        assertThrows(IllegalStateException.class, () ->
                this.connectionsManager.disconnectUser(u));
    }
    @Test
    void shouldNotDisconnectNullUser(){
        assertThrows(IllegalArgumentException.class, () ->
                this.connectionsManager.disconnectUser(null));
    }

    @Test
    void shouldReturnUserFiles(){
        this.connectionsManager.addFileToDirectory(user1, file1);
        this.connectionsManager.addFileToDirectory(user1, file2);
        assertTrue(this.connectionsManager.getUserFiles(user1).contains(file1) && this.connectionsManager.getUserFiles(user1).contains(file2));
    }

    @Test
    void shouldReturnUsersThatProposeFile(){
        this.connectionsManager.addFileToDirectory(user1, file1);
        this.connectionsManager.addFileToDirectory(user2, file1);
        assertTrue(this.connectionsManager.getUsersThatProposeFile(file1).contains(user1) && this.connectionsManager.getUsersThatProposeFile(file1).contains(user2));
    }

    @Test
    void shouldNotConnectNullUser(){
        assertThrows(IllegalArgumentException.class, () ->
                this.connectionsManager.connectUser(null));
    }
}
