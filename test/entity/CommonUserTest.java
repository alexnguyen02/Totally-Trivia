package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class CommonUserTest {

    private CommonUser user;

    @BeforeEach
    public void init() {
        LocalDateTime ldt = LocalDateTime.of(2024, 05, 24, 12, 12, 12);
        user = new CommonUser("Furiosa", "RememberMe", ldt, 45, "Red");
    }

    @Test
    public void getNameTest() {
        assertEquals("Furiosa", user.getName());
    }

    @Test
    public void getPasswordTest() {
        assertEquals("RememberMe", user.getPassword());
    }

    @Test
    public void getCreationTimeTest() {
        LocalDateTime ldt = LocalDateTime.of(2024, 05, 24, 12, 12, 12);
        assertEquals(ldt, user.getCreationTime());
    }

    @Test
    public void getPointsTest() {
        assertEquals(new Integer(45), user.getPoints());
    }

    @Test
    public void getColourSchemeTest() {
        assertEquals("Red", user.getColourScheme());
    }

    @Test
    public void setPointsTest() {
        user.setPoints(100);
        assertEquals(new Integer(100), user.getPoints());
    }

    @Test
    public void setColourSchemeTest() {
        user.setColourScheme("Orange");
        assertEquals("Orange", user.getColourScheme());
    }

    @Test
    public void copyUserTest() {
        User new_user = new CommonUser("MadMax", "bloodbag", LocalDateTime.now(), 150, "Red");
        user.copyUser(new_user);
        assertEquals(user.getName(), new_user.getName());
        assertEquals(user.getPassword(), new_user.getPassword());
        assertEquals(user.getCreationTime(), new_user.getCreationTime());
        assertEquals(user.getPoints(), new_user.getPoints());
        assertEquals(user.getColourScheme(), new_user.getColourScheme());
        assertNotEquals(user, new_user);
    }
}