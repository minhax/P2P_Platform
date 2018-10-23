package java.common.filehandler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires du FileHandlerStats
 */
class FileHandlerStatsTest
{
    private FileHandlerStats fh;

    /**
     * On initialise un FileHandlerStats de test avant chaque test.
     */
    @BeforeEach
    void createFileHandlerForTests()
    {
        this.fh = new FileHandlerStats("azertyuiop", "My File", 10, "pdf", 1,
                "This is my file");
    }

    /**
     * Test du constructeur.
     */
    @Test
    void testConstructor()
    {
        assertEquals(0, this.fh.getNbDownloads());
    }

    /**
     * Test de l'incrémentation du compteur de téléchargements.
     */
    @Test
    void shouldIncrementNbDownloads()
    {
        assertEquals(1, this.fh.incNbDownloads());
    }
}