package com.lo23.common.user;

import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.filehandler.FileHandlerInfos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test unitaires permettant de tester les classes liées aux utilisateurs
 */
public class UserTests
{
    private UserAccount user;

    /**
     * On initialise un User de test avant chaque test.
     */
    @BeforeEach
    void createUserForTests()
    {
        this.user = new UserAccount(
                "login",
                "First",
                "Last",
                50,
                "password"
        );
    }

    /**
     * Permet de tester les constructeurs de différentes classes
     */
    @Test
    void testConstructors()
    {
        assertTrue(
                this.user.getAge() == 50
                        && this.user.getFirstName().equals("First")
                        && this.user.getLastName().equals("Last")
                        && this.user.getLogin().equals("login")
                        && this.user.checkPassword("password")
        );
    }

    /**
     * Teste le changement de prénom
     */
    @Test
    void ShouldChangeFirstName()
    {
        this.user.setFirstName("Other");
        assertEquals("Other", this.user.getFirstName());
    }

    /**
     * Teste le changement de nom de famille
     */
    @Test
    void ShouldChangeLastName()
    {
        this.user.setLastName("Other");
        assertEquals("Other", this.user.getLastName());
    }

    /**
     * Teste le changment d'age
     */
    @Test
    void ShouldChangeAge()
    {
        this.user.setAge(20);
        assertEquals(20, this.user.getAge());
    }

    /**
     * Teste la correspondance du mot de passe
     */
    @Test
    void ShouldCheckPassword()
    {
        assertTrue(this.user.checkPassword("password"));
    }

    /**
     * Teste l'incrémentation du nombre de fichiers téléchargés
     */
    @Test
    void ShouldIncrementNbFilesDownloaded()
    {
        int nbFiles = this.user.getNbFilesDownloaded();
        this.user.incrementNbFilesDownloaded();
        assertEquals(nbFiles + 1, this.user.getNbFilesDownloaded());
    }

    /**
     * Teste l'incrémentation du nombre de fichiers téléversés
     */
    @Test
    void ShouldIncrementNbFilesUploaded()
    {
        int nbFiles = this.user.getNbFilesUploaded();
        this.user.incrementNbFilesUploaded();
        assertEquals(nbFiles + 1, this.user.getNbFilesUploaded());
    }

    /**
     * Teste la décrémentation du nombre de fichiers téléversés
     */
    @Test
    void ShouldDecrementNbFilesUploaded()
    {
        int nbFiles = this.user.getNbFilesUploaded();
        this.user.incrementNbFilesUploaded();
        this.user.decrementNbFilesUploaded();
        assertEquals(nbFiles, this.user.getNbFilesUploaded());
    }

    /**
     * Teste que les id générés sont différents
     */
    @Test
    void ShouldNotBeTheSameId()
    {
        UUID testID = UUID.randomUUID();
        assertNotEquals(testID, this.user.getId());
    }

    /**
     * Teste l'ajout d'un fichier proposé
     */
    @Test
    void ShouldAddProposedFile()
    {
        FileHandlerInfos fh = new FileHandlerInfos("hash", "titre", 512, "pdf", 3,
                "description");
        this.user.addProposedFile(fh);
        assertTrue(this.user.getProposedFiles().contains(fh));
    }


    /**
     * Teste la suppression d'un fichier proposé
     */
    @Test
    void ShouldRemoveProposedFile()
    {
        FileHandlerInfos fh = new FileHandlerInfos("hash", "titre", 512, "pdf", 3,
                "description");
        this.user.addProposedFile(fh);
        this.user.removeProposedFile(fh);
        assertFalse(this.user.getProposedFiles().contains(fh));
    }

    /**
     * Teste la mise à jour de l'IP du dernier serveur
     */
    @Test
    void ShouldUpdateAndGetLastServerIP()
    {
        this.user.setLastConnectionServerIP("127.0.0.1");
        assertEquals("127.0.0.1", this.user.getLastConnectionServerIP());
    }
}