import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests the Main class.
 */
public class MainTest {
    @Test void smokeTest() {
        Main main = new Main();

        assertEquals("Hello World!", main.main());
    }
}
