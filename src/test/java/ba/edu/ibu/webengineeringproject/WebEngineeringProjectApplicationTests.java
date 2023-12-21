package ba.edu.ibu.webengineeringproject;

import ba.edu.ibu.webengineeringproject.core.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WebEngineeringProjectApplicationTests {

    @Test

    void contextLoads() {
        String[] actual = {"Hello", "JUnit"};
        String[] expected = {"Hello", "JUnit"};
        assertArrayEquals(actual, expected);

        assertTrue(2 > 1);
        assertFalse(2 > 3);

        User u = null;
        assertNull(u);

        u = new User();
        assertNotNull(u);

        int[] array = {1, 2, 3};
        Exception e = assertThrows(IndexOutOfBoundsException.class, () -> {
            int i = array[3];
        });
        assertEquals("Index 3 out of bounds for length 3", e.getMessage());
    }

}
