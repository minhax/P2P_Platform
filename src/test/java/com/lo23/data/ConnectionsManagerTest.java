package com.lo23.data;

import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.user.UserIdentity;
import com.lo23.data.server.ConnectionsManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConnectionsManagerTest
{
    private ConnectionsManager connectionsManager;
    private UserIdentity user1;
    private UserIdentity user2;

    @BeforeEach
    void createConnectionsManagerAndUsersForTests()
    {
        this.user1 = new UserIdentity("login1", "user", "one", 20);
        this.user2 = new UserIdentity("login2", "user", "two", 50);
        this.user3 = new UserIdentity("login3", "user", "three", 70);
        this.connectionsManager = new ConnectionsManager();
        this.connectionsManager.connectUser(user1);
        this.connectionsManager.connectUser(user2);
    }

    /*
    @Test
    void testGetterShouldReturnFiles1And2()
    {
        assertTrue(this.directory.getProposedFiles().contains(file1)
                && this.directory.getProposedFiles().contains(file2));
    }
    @Test
    void testGetUsersThatProposeFile()
    {
        assertTrue(this.directory.getUsersThatProposeFile(file1).contains(user1)
                &&this.directory.getUsersThatProposeFile(file1).contains(user2));
    }

    @Test
    void testGetFilesProposedByUser()
    {
        assertTrue(this.directory.getFilesProposedByUser(user1).contains(file1)
                &&this.directory.getFilesProposedByUser(user1).contains(file2));
    }

    @Test
    void shouldRemoveUser1()
    {
        this.directory.removeUser(user1);
        boolean b1 = this.directory.getProposedFiles().contains(file1);
        boolean b2 = !(this.directory.getProposedFiles().contains(file2));
        boolean b3 = !this.directory.getUsersThatProposeFile(file1).contains(user1);

        assertTrue(b1 && b2 && b3);
    }

    @Test
    void shouldRemoveFile1FromUser1()
    {
        this.directory.removeProposedFile(user1, file1);
        boolean b1 = this.directory.getProposedFiles().contains(file1);
        boolean b2 = !this.directory.getUsersThatProposeFile(file1).contains(user1);

        assertTrue(b1 && b2);
    }

    @Test
    void shouldAddFile3ProposedByUser2(){
        this.directory.addProposedFile(user2, file3);
        boolean b1 = this.directory.getProposedFiles().contains(file3);
        boolean b2 = this.directory.getFilesProposedByUser(user2).contains(file3);
        boolean b3 = this.directory.getUsersThatProposeFile(file3).contains(user2);

        assertTrue(b1 && b2 && b3);
    }
    */
}
