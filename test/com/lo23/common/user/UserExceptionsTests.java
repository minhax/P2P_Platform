package java.common.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserExceptionsTests {
    @Test
    void shouldThrowExceptionDueToIncorrectLogin()
    {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new User(""));
    }

    @Test
    void shouldThrowExceptionDueToIncorrectFirstName()
    {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new UserIdentity("login","", "lastName", 50));
    }

    @Test
    void shouldThrowExceptionDueToIncorrectLastName()
    {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new UserIdentity("login","firstName", "", 50));
    }

    @Test
    void shouldThrowExceptionDueToIncorrectAge()
    {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new UserIdentity("login","firstName", "lastName", -50));
    }

    @Test
    void shouldThrowExceptionDueToIncorrectPassword()
    {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new UserAccount("login","firstName", "lastName", 50, ""));
    }

    @Test
    void ShouldThrowExceptionDueToNegativeUploadedFiles()
    {
        UserAccount user = new UserAccount("login", "firstName", "lastName", 50, "password");
        Assertions.assertThrows(IllegalStateException.class, () -> user.decrementNbFilesUploaded());
    }
}
