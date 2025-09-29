package com.boycottpro.utilities;

/**
 * Static Logger utility class for BoycottPro Lambda functions
 *
 * Provides simple, standardized logging for all Lambda functions with:
 * - Error logging with line number, user ID, and error message
 * - Info logging with line number and user ID for execution tracking
 *
 * Usage Examples:
 * Logger.error(42, "user123", "Database connection failed");
 * Logger.info(25, "user456");
 */
public class Logger {

    /**
     * Private constructor to prevent instantiation of utility class
     */
    private Logger() {
        throw new IllegalStateException("Utility class - do not instantiate");
    }

    /**
     * Logs error information with line number, user ID, and error message
     *
     * @param lineNumber The line number where the error occurred
     * @param userId     The user ID associated with the request (can be null)
     * @param message    The error message to log
     */
    public static void error(int lineNumber, String userId, String message) {
        String userIdStr = (userId != null) ? userId : "unknown";
        System.out.println("ERROR: at line: " + lineNumber + ", user_id: " + userIdStr + ", err= " + message);
    }

    /**
     * Logs info checkpoint with line number and user ID
     * Used sparingly to mark execution progress through Lambda function
     *
     * @param lineNumber The line number reached in execution
     * @param userId     The user ID associated with the request (can be null)
     */
    public static void info(int lineNumber, String userId) {
        String userIdStr = (userId != null) ? userId : "unknown";
        System.out.println("INFO: at line: " + lineNumber + ", user_id: " + userIdStr);
    }
}