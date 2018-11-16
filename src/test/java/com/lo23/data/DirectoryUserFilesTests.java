package com.lo23.data;

import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.filehandler.FileHandlerInfos;
import com.lo23.common.user.UserIdentity;
import com.lo23.data.server.DirectoryUserFiles;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DirectoryUserFilesTests
{
    private DirectoryUserFiles directory;
    private UserIdentity user1;
    private UserIdentity user2;
    private FileHandlerInfos file1;
    private FileHandlerInfos file2;
    private FileHandlerInfos file3;

//    @BeforeAll
//    void createUsersAndFilesForTests(){
//
//    }

    @BeforeEach
    void createDirectoryForTests()
    {
        this.user1 = new UserIdentity("login1", "user", "one", 20);
        this.user2 = new UserIdentity("login2", "user", "two", 50);
        this.file1 = new FileHandlerInfos("hash1", "title1", 1, "type1", 1, "description 1");
        this.file2 = new FileHandlerInfos("hash2", "title2", 2, "type2", 2, "description 2");
        this.file3 = new FileHandlerInfos("hash3", "title3", 3, "type3", 3, "description 3");
        this.directory = new DirectoryUserFiles();
        this.directory.addProposedFile(user1, file1);
        this.directory.addProposedFile(user2, file1);
        this.directory.addProposedFile(user1, file2);
    }

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
}
