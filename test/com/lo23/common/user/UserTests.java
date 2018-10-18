package com.lo23.common.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
}