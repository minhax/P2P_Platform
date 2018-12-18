package com.lo23.data.client;

import com.lo23.common.exceptions.DataException;
import com.lo23.common.interfaces.data.DataClientToIhm;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires du DataManagerClient.
 */
class DataManagerClientTest
{
    private DataManagerClient dm;
    private String userLogin = "test";
    private String userPassword = "test";

    @BeforeAll
    static void backupFiles ()
    {
        // TODO: gérer les NullExceptionPointer qui peuvent apparaître
        try {
            Path accountsBackupDir = Paths.get("./files/backup/accounts");
            Files.createDirectories(accountsBackupDir);
            Path filepartsBackupDir = Paths.get("./files/backup/fileparts");
            Files.createDirectories(filepartsBackupDir);

            File accountsDirectory = new File("./files/accounts");
            File filepartsDirectory = new File("./files/fileparts");

            for(File account : accountsDirectory.listFiles())
            {
                Files.move(Paths.get(account.getPath()), Paths.get("files/backup/accounts/" + account.getName()));
            }
            for(File filepart : filepartsDirectory.listFiles())
            {
                Files.move(Paths.get(filepart.getPath()), Paths.get("files/backup/fileparts/" + filepart.getName()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeAll
    static void createTestFile ()
    {
        String content = "A\nB\nC\nD\nE\nF\nG\nH\nI\nJ\nK\nL\nM\nN\nO\nP\nQ\nR\nS\nT\nU\nV\nW\nX\nY\nZ\n";
        File testfile = new File("./files/TESTFILE.txt");
        try
        {
            FileOutputStream out = new FileOutputStream(testfile);
            out.write(content.getBytes());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * On initialise un DataManagerClient de test avant chaque test.
     */
    @BeforeEach
    void createDataManagerClient ()
    {
        //this.dm = DataManagerClient.getInstance();
    }

    /**
     * On teste si une erreur est levée lors de la création de compte.
     */
    @Test
    void shouldCreateAccount ()
    {
        // TODO: tester les méthodes, pas l'API
        DataClientToIhm api = dm.getDataClientToIhmApi();
        assertDoesNotThrow(() ->
                api.createAccount(userLogin, userPassword, "PrénomTest", "NomTest", 99)
        );
    }

    /**
     * On teste si une erreur est levée lors du ârtage de fichier.
     */
    @Test
    void shouldShareFile ()
    {
        // TODO: tester les méthodes, pas l'API
        DataClientToIhm api = dm.getDataClientToIhmApi();
        api.requestCheckCredentials(userLogin, userPassword);
        assertDoesNotThrow(() ->
                api.requestShareNewFile("files/TESTFILE.txt", "Fichier de test",
                        "Description du fichier de test")
        );
    }

    @AfterAll
    static void restoreFilesDirectory ()
    {
        // TODO: gérer les NullExceptionPointer qui peuvent apparaître
        try {
            File accountsDirectory = new File("./files/accounts");
            File filepartsDirectory = new File("./files/fileparts");
            File accBackupDirectory = new File("./files/backup/accounts");
            File filepBackupDirectory = new File("./files/backup/fileparts");

            for(File account : accountsDirectory.listFiles())
            {
                account.delete();
            }
            for(File filepart : filepartsDirectory.listFiles())
            {
                filepart.delete();
            }
            for(File accountBackup : accBackupDirectory.listFiles())
            {
                Files.move(Paths.get(accountBackup.getPath()),
                        Paths.get("files/accounts/" + accountBackup.getName()));
            }
            for(File filepartBackup : filepBackupDirectory.listFiles())
            {
                Files.move(Paths.get(filepartBackup.getPath()),
                        Paths.get("files/fileparts/" + filepartBackup.getName()));
            }
            accBackupDirectory.delete();
            filepBackupDirectory.delete();
            File backupDirectory = new File("./files/backup");
            backupDirectory.delete();

            File testfile = new File("./files/TESTFILE.txt");
            testfile.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}