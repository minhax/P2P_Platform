package java.common.filehandler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires de la classe FileHandler.
 */
class FileHandlerTest
{
    private FileHandler fh;

    /**
     * On initialise un FileHandler de test avant chaque test.
     */
    @BeforeEach
    void createFileHandlerForTests()
    {
        this.fh = new FileHandler("azertyuiop", "My File", 10, "pdf", 1);
    }

    /**
     * Test du constructeur de la classe.
     */
    @Test
    void testConstructor()
    {
        assertTrue(
                this.fh.getHash().equals("azertyuiop")
                        && this.fh.getTitle().equals("My File")
                        && this.fh.getSize() == 10
                        && this.fh.getType().equals("pdf")
                        && this.fh.getNbBlocks() == 1
        );
    }

    /**
     * Test des exceptions du constructeur en cas de paramètres illégaux.
     */
    @Test
    void testConstructorWithIllegalParams()
    {
        assertThrows(IllegalArgumentException.class, () ->
                new FileHandler("", "My File", 10, "pdf", 1));
        assertThrows(IllegalArgumentException.class, () ->
                new FileHandler("azertyuiop", "My File", -1, "pdf", 1));
        assertThrows(IllegalArgumentException.class, () ->
                new FileHandler("azertyuiop", "My File", 10, "pdf", -1));
    }

    /**
     * Test du setter de nom de fichier.
     */
    @Test
    void shouldChangeTitle()
    {
        this.fh.setTitle("My New Shiny Title");
        assertEquals("My New Shiny Title", this.fh.getTitle());
    }
}