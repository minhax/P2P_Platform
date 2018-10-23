package java.com.lo23.common.filehandler;

import java.common.Comment;
import java.common.Rating;
import java.common.Tag;
import java.common.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires du FileHandlerInfos.
 */
class FileHandlerInfosTest
{
    private FileHandlerInfos fh;

    /**
     * On initialise un FileHandlerInfos de test avant chaque test.
     */
    @BeforeEach
    void createFileHandlerForTests()
    {
        this.fh = new FileHandlerInfos("azertyuiop", "My File", 10, "pdf", 1,
                "This is my file");
    }

    /**
     * Test du constructeur.
     */
    @Test
    void testConstructor()
    {
        assertEquals("This is my file", this.fh.getDesc());
    }

    /**
     * Test du setter de description du fichier.
     */
    @Test
    void shouldChangeDesc()
    {
        String newDesc = "This is my new desc";
        this.fh.setDesc(newDesc);
        assertEquals(newDesc, this.fh.getDesc());
    }

    /**
     * Test du getter de la liste des tags.
     */
    @Test
    void shouldGetTags()
    {
        Vector<Tag> vt = this.fh.getTags();
        assertEquals(0, vt.size());
    }

    /**
     * Test de l'ajout d'un tag au fichier.
     */
    @Test
    void shouldAddTag()
    {
        Tag t = new Tag("WIP");
        this.fh.addTag(t);
        Vector<Tag> vt = this.fh.getTags();
        assertEquals(1, vt.size());
        assertEquals("WIP", vt.get(0).getLabel());
    }

    /**
     * Test de la suppression d'un tag du fichier.
     */
    @Test
    void shouldRemoveTag()
    {
        Tag t = new Tag("WIP");
        this.fh.addTag(t);
        assertTrue(this.fh.removeTag(t));
        Vector<Tag> vt = this.fh.getTags();
        assertEquals(0, vt.size());
        assertFalse(this.fh.removeTag(t));
    }

    /**
     * Test du getter du tableau de notes.
     */
    @Test
    void shouldGetRatings()
    {
        Vector<Rating> vr = this.fh.getRatings();
        assertEquals(0, vr.size());
    }

    /**
     * Test de l'ajout d'une note.
     */
    @Test
    void shouldAddRating()
    {
        Rating r = new Rating(5, new User("testLogin"));
        this.fh.addRating(r);
        Vector<Rating> vr = this.fh.getRatings();
        assertEquals(1, vr.size());
        assertEquals(5, vr.get(0).getValue());
        assertEquals("testLogin", vr.get(0).getAuthor().getLogin());
    }

    /**
     * Test du retrait d'une note.
     */
    @Test
    void shouldRemoveRating()
    {
        Rating r = new Rating(5, new User("testLogin"));
        this.fh.addRating(r);
        assertTrue(this.fh.removeRating(r));
        Vector<Rating> vr = this.fh.getRatings();
        assertEquals(0, vr.size());
        assertFalse(this.fh.removeRating(r));
    }

    /**
     * Test du getter de tableau de commentaires.
     */
    @Test
    void shouldGetComments()
    {
        Vector<Comment> vc = this.fh.getComments();
        assertEquals(0, vc.size());
    }

    /**
     * Test de l'ajout d'un commentaire.
     */
    @Test
    void shouldAddComment()
    {
        Comment c = new Comment("My Comment", new User("testLogin"));
        this.fh.addComment(c);
        Vector<Comment> vc = this.fh.getComments();
        assertEquals(1, vc.size());
        assertEquals("My Comment", vc.get(0).getText());
        assertEquals("testLogin", vc.get(0).getAuthor().getLogin());
    }

    /**
     * Test du retrait d'un commentaire.
     */
    @Test
    void shouldRemoveComment()
    {
        Comment c = new Comment("My Comment", new User("testLogin"));
        this.fh.addComment(c);
        assertTrue(this.fh.removeComment(c));
        Vector<Comment> vc = this.fh.getComments();
        assertEquals(0, vc.size());
        assertFalse(this.fh.removeComment(c));
    }
}