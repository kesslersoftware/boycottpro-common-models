package com.boycottpro.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive test suite for ResponseMessage model class
 */
public class ResponseMessageTest {

    private ResponseMessage responseMessage;

    @BeforeEach
    public void setUp() {
        responseMessage = new ResponseMessage();
    }

    @Test
    public void testDefaultConstructor() {
        assertEquals(0, responseMessage.getStatus());
        assertNull(responseMessage.getMessage());
        assertNull(responseMessage.getDevMsg());
    }

    @Test
    public void testParameterizedConstructor() {
        ResponseMessage testResponse = new ResponseMessage(
            200,
            "Operation successful",
            "User created successfully with ID: USER123"
        );

        assertEquals(200, testResponse.getStatus());
        assertEquals("Operation successful", testResponse.getMessage());
        assertEquals("User created successfully with ID: USER123", testResponse.getDevMsg());
    }

    @Test
    public void testStatusGetterSetter() {
        responseMessage.setStatus(404);
        assertEquals(404, responseMessage.getStatus());
    }

    @Test
    public void testMessageGetterSetter() {
        String message = "Resource not found";
        responseMessage.setMessage(message);
        assertEquals(message, responseMessage.getMessage());
    }

    @Test
    public void testDevMsgGetterSetter() {
        String devMsg = "User with ID USER123 does not exist in database";
        responseMessage.setDevMsg(devMsg);
        assertEquals(devMsg, responseMessage.getDevMsg());
    }

    @Test
    public void testSuccessResponse() {
        responseMessage.setStatus(200);
        responseMessage.setMessage("Success");
        responseMessage.setDevMsg("Operation completed successfully");

        assertEquals(200, responseMessage.getStatus());
        assertEquals("Success", responseMessage.getMessage());
        assertEquals("Operation completed successfully", responseMessage.getDevMsg());
    }

    @Test
    public void testClientErrorResponse() {
        responseMessage.setStatus(400);
        responseMessage.setMessage("Bad Request");
        responseMessage.setDevMsg("Invalid input: missing required field 'email'");

        assertEquals(400, responseMessage.getStatus());
        assertEquals("Bad Request", responseMessage.getMessage());
        assertEquals("Invalid input: missing required field 'email'", responseMessage.getDevMsg());
    }

    @Test
    public void testServerErrorResponse() {
        responseMessage.setStatus(500);
        responseMessage.setMessage("Internal Server Error");
        responseMessage.setDevMsg("Database connection timeout after 30 seconds");

        assertEquals(500, responseMessage.getStatus());
        assertEquals("Internal Server Error", responseMessage.getMessage());
        assertEquals("Database connection timeout after 30 seconds", responseMessage.getDevMsg());
    }

    @Test
    public void testCommonHttpStatusCodes() {
        int[] statusCodes = {200, 201, 204, 400, 401, 403, 404, 409, 422, 500, 502, 503};

        for (int statusCode : statusCodes) {
            responseMessage.setStatus(statusCode);
            assertEquals(statusCode, responseMessage.getStatus());
        }
    }

    @Test
    public void testNullMessages() {
        responseMessage.setMessage(null);
        responseMessage.setDevMsg(null);

        assertNull(responseMessage.getMessage());
        assertNull(responseMessage.getDevMsg());
    }

    @Test
    public void testEmptyMessages() {
        responseMessage.setMessage("");
        responseMessage.setDevMsg("");

        assertEquals("", responseMessage.getMessage());
        assertEquals("", responseMessage.getDevMsg());
    }

    @Test
    public void testNegativeStatusCode() {
        responseMessage.setStatus(-1);
        assertEquals(-1, responseMessage.getStatus());
    }

    @Test
    public void testZeroStatusCode() {
        responseMessage.setStatus(0);
        assertEquals(0, responseMessage.getStatus());
    }

    @Test
    public void testLargeStatusCode() {
        responseMessage.setStatus(999);
        assertEquals(999, responseMessage.getStatus());
    }

    @Test
    public void testMaximumStatusCode() {
        responseMessage.setStatus(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, responseMessage.getStatus());
    }

    @Test
    public void testLongMessages() {
        String longMessage = "A".repeat(1000);
        String longDevMsg = "B".repeat(2000);

        responseMessage.setMessage(longMessage);
        responseMessage.setDevMsg(longDevMsg);

        assertEquals(longMessage, responseMessage.getMessage());
        assertEquals(longDevMsg, responseMessage.getDevMsg());
        assertEquals(1000, responseMessage.getMessage().length());
        assertEquals(2000, responseMessage.getDevMsg().length());
    }

    @Test
    public void testSpecialCharactersInMessages() {
        String specialMessage = "Error: Invalid JSON format! @#$%^&*()";
        String specialDevMsg = "Stack trace: NullPointerException at line 42\n\tCaused by: Database timeout";

        responseMessage.setMessage(specialMessage);
        responseMessage.setDevMsg(specialDevMsg);

        assertEquals(specialMessage, responseMessage.getMessage());
        assertEquals(specialDevMsg, responseMessage.getDevMsg());
    }

    @Test
    public void testAuthenticationResponses() {
        // Unauthorized
        responseMessage.setStatus(401);
        responseMessage.setMessage("Unauthorized");
        responseMessage.setDevMsg("Invalid JWT token");

        assertEquals(401, responseMessage.getStatus());
        assertEquals("Unauthorized", responseMessage.getMessage());
        assertEquals("Invalid JWT token", responseMessage.getDevMsg());

        // Forbidden
        responseMessage.setStatus(403);
        responseMessage.setMessage("Forbidden");
        responseMessage.setDevMsg("User does not have permission to access this resource");

        assertEquals(403, responseMessage.getStatus());
        assertEquals("Forbidden", responseMessage.getMessage());
        assertEquals("User does not have permission to access this resource", responseMessage.getDevMsg());
    }

    @Test
    public void testValidationErrorResponse() {
        responseMessage.setStatus(422);
        responseMessage.setMessage("Validation Failed");
        responseMessage.setDevMsg("Field 'email' must be a valid email address");

        assertEquals(422, responseMessage.getStatus());
        assertEquals("Validation Failed", responseMessage.getMessage());
        assertEquals("Field 'email' must be a valid email address", responseMessage.getDevMsg());
    }

    @Test
    public void testCreatedResponse() {
        responseMessage.setStatus(201);
        responseMessage.setMessage("Resource Created");
        responseMessage.setDevMsg("New company created with ID: COMP123");

        assertEquals(201, responseMessage.getStatus());
        assertEquals("Resource Created", responseMessage.getMessage());
        assertEquals("New company created with ID: COMP123", responseMessage.getDevMsg());
    }

    @Test
    public void testNoContentResponse() {
        responseMessage.setStatus(204);
        responseMessage.setMessage("No Content");
        responseMessage.setDevMsg("Resource deleted successfully");

        assertEquals(204, responseMessage.getStatus());
        assertEquals("No Content", responseMessage.getMessage());
        assertEquals("Resource deleted successfully", responseMessage.getDevMsg());
    }

    @Test
    public void testConflictResponse() {
        responseMessage.setStatus(409);
        responseMessage.setMessage("Conflict");
        responseMessage.setDevMsg("Username 'johndoe' already exists");

        assertEquals(409, responseMessage.getStatus());
        assertEquals("Conflict", responseMessage.getMessage());
        assertEquals("Username 'johndoe' already exists", responseMessage.getDevMsg());
    }

    @Test
    public void testMinimalResponse() {
        responseMessage.setStatus(200);
        responseMessage.setMessage("OK");
        responseMessage.setDevMsg("Success");

        assertEquals(200, responseMessage.getStatus());
        assertEquals("OK", responseMessage.getMessage());
        assertEquals("Success", responseMessage.getDevMsg());
    }

    @Test
    public void testDetailedErrorResponse() {
        responseMessage.setStatus(500);
        responseMessage.setMessage("Internal Server Error");
        responseMessage.setDevMsg("SQLException: Connection to database 'boycottprodb' failed. " +
                                  "Error code: 08001. SQLState: 08001. " +
                                  "Message: The connection attempt failed due to timeout.");

        assertEquals(500, responseMessage.getStatus());
        assertEquals("Internal Server Error", responseMessage.getMessage());
        assertTrue(responseMessage.getDevMsg().contains("SQLException"));
        assertTrue(responseMessage.getDevMsg().contains("boycottprodb"));
        assertTrue(responseMessage.getDevMsg().contains("timeout"));
    }
}