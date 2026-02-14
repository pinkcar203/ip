package mickey;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for Mickey class
 * Focuses on GUI response generation and command type tracking
 */
public class TestMickey {
    private Mickey mickey;

    @BeforeEach
    public void setUp() {
        // create fresh Mickey instance with test data file before each test
        mickey = new Mickey("./src/test/resources/test_mickey.txt");
    }

    @Test
    public void testGetResponseWithTodoCommand() {
        // Test: adding a todo should return success message and track command type
        String response = mickey.getResponse("todo buy groceries");

        assertTrue(response.contains("Added"));
        assertTrue(response.contains("buy groceries"));
        assertEquals("todo", mickey.getLastCommandType());
    }


    @Test
    public void testGetResponseWithListCommand() {
        // Test: list command should show tasks or empty message
        mickey.getResponse("todo test task 1");
        mickey.getResponse("todo test task 2");

        String response = mickey.getResponse("list");

        assertTrue(response.contains("test task 1"));
        assertTrue(response.contains("test task 2"));
        assertEquals("list", mickey.getLastCommandType());
    }


    @Test
    public void testGetResponseWithDeleteCommand() {
        // Test: deleting a task should succeed and update count
        mickey.getResponse("todo task to delete");
        String response = mickey.getResponse("delete 1");

        assertTrue(response.contains("deleted") || response.contains("removed"));
        assertEquals("delete", mickey.getLastCommandType());
    }
}
