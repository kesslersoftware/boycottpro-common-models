package com.boycottpro.utilities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Logger utility
 */
public class LoggerTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream standardOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void testErrorWithValidParameters() {
        Logger.error(42, "user123", "Test error message");

        String expectedOutput = "ERROR: at line: 42, user_id: user123, err= Test error message";
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    public void testErrorWithNullUserId() {
        Logger.error(25, null, "Null user error");

        String expectedOutput = "ERROR: at line: 25, user_id: unknown, err= Null user error";
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    public void testInfoWithValidParameters() {
        Logger.info(15, "user456");

        String expectedOutput = "INFO: at line: 15, user_id: user456";
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    public void testInfoWithNullUserId() {
        Logger.info(33, null);

        String expectedOutput = "INFO: at line: 33, user_id: unknown";
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    public void testLoggerCannotBeInstantiated() {
        // Verify that Logger constructor throws exception
        assertThrows(java.lang.reflect.InvocationTargetException.class, () -> {
            // Use reflection to access private constructor
            java.lang.reflect.Constructor<Logger> constructor =
                Logger.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        });
    }

    @Test
    public void testMultipleLogCalls() {
        Logger.error(10, "user1", "First error");
        Logger.info(20, "user2");
        Logger.error(30, "user3", "Second error");

        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("ERROR: at line: 10, user_id: user1, err= First error"));
        assertTrue(output.contains("INFO: at line: 20, user_id: user2"));
        assertTrue(output.contains("ERROR: at line: 30, user_id: user3, err= Second error"));
    }
}