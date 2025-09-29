package com.boycottpro.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive test suite for UserCauses model class
 */
public class UserCausesTest {

    private UserCauses userCause;

    @BeforeEach
    public void setUp() {
        userCause = new UserCauses();
    }

    @Test
    public void testDefaultConstructor() {
        assertNull(userCause.getUser_id());
        assertNull(userCause.getCause_id());
        assertNull(userCause.getCause_desc());
        assertNull(userCause.getTimestamp());
    }

    @Test
    public void testParameterizedConstructor() {
        UserCauses testUserCause = new UserCauses(
            "USER123",
            "CAUSE456",
            "Environmental Protection",
            "2023-12-01T10:30:00Z"
        );

        assertEquals("USER123", testUserCause.getUser_id());
        assertEquals("CAUSE456", testUserCause.getCause_id());
        assertEquals("Environmental Protection", testUserCause.getCause_desc());
        assertEquals("2023-12-01T10:30:00Z", testUserCause.getTimestamp());
    }

    @Test
    public void testUserIdGetterSetter() {
        String userId = "USER789";
        userCause.setUser_id(userId);
        assertEquals(userId, userCause.getUser_id());
    }

    @Test
    public void testCauseIdGetterSetter() {
        String causeId = "CAUSE321";
        userCause.setCause_id(causeId);
        assertEquals(causeId, userCause.getCause_id());
    }

    @Test
    public void testCauseDescGetterSetter() {
        String causeDesc = "Climate Change Action";
        userCause.setCause_desc(causeDesc);
        assertEquals(causeDesc, userCause.getCause_desc());
    }

    @Test
    public void testTimestampGetterSetter() {
        String timestamp = "2023-12-15T14:30:45Z";
        userCause.setTimestamp(timestamp);
        assertEquals(timestamp, userCause.getTimestamp());
    }

    @Test
    public void testCompleteUserCauseScenario() {
        userCause.setUser_id("USER456");
        userCause.setCause_id("CAUSE789");
        userCause.setCause_desc("Animal Rights Protection");
        userCause.setTimestamp("2023-11-20T09:15:30Z");

        assertEquals("USER456", userCause.getUser_id());
        assertEquals("CAUSE789", userCause.getCause_id());
        assertEquals("Animal Rights Protection", userCause.getCause_desc());
        assertEquals("2023-11-20T09:15:30Z", userCause.getTimestamp());
    }

    @Test
    public void testNullStringFields() {
        userCause.setUser_id(null);
        userCause.setCause_id(null);
        userCause.setCause_desc(null);
        userCause.setTimestamp(null);

        assertNull(userCause.getUser_id());
        assertNull(userCause.getCause_id());
        assertNull(userCause.getCause_desc());
        assertNull(userCause.getTimestamp());
    }

    @Test
    public void testEmptyStringFields() {
        userCause.setUser_id("");
        userCause.setCause_id("");
        userCause.setCause_desc("");
        userCause.setTimestamp("");

        assertEquals("", userCause.getUser_id());
        assertEquals("", userCause.getCause_id());
        assertEquals("", userCause.getCause_desc());
        assertEquals("", userCause.getTimestamp());
    }

    @Test
    public void testCauseCategories() {
        String[] causeDescriptions = {
            "Environmental Protection",
            "Human Rights",
            "Animal Welfare",
            "Labor Rights",
            "Social Justice",
            "Healthcare Access",
            "Education Equality",
            "Economic Justice",
            "Political Reform",
            "Anti-Corruption",
            "Peace and Non-Violence",
            "LGBTQ+ Rights",
            "Racial Equality",
            "Gender Equality"
        };

        for (String causeDesc : causeDescriptions) {
            userCause.setCause_desc(causeDesc);
            assertEquals(causeDesc, userCause.getCause_desc());
        }
    }

    @Test
    public void testSpecialCharactersInFields() {
        String specialUserId = "USER-123_$%";
        String specialCauseId = "CAUSE@456!&";
        String specialCauseDesc = "Environment & Climate Action (Global Initiative)";

        userCause.setUser_id(specialUserId);
        userCause.setCause_id(specialCauseId);
        userCause.setCause_desc(specialCauseDesc);

        assertEquals(specialUserId, userCause.getUser_id());
        assertEquals(specialCauseId, userCause.getCause_id());
        assertEquals(specialCauseDesc, userCause.getCause_desc());
    }

    @Test
    public void testLongCauseDescription() {
        String longDescription = "This is a very detailed cause description that explains the mission, goals, objectives, and impact of this particular cause. ".repeat(20);
        userCause.setCause_desc(longDescription);
        assertEquals(longDescription, userCause.getCause_desc());
    }

    @Test
    public void testTimestampFormats() {
        String[] timestamps = {
            "2023-12-01T10:30:00Z",        // ISO 8601 with Z
            "2023-12-01T10:30:00-05:00",   // ISO 8601 with timezone
            "2023-12-01 10:30:00",         // SQL format
            "1672531200000",               // Unix timestamp (milliseconds)
            "1672531200",                  // Unix timestamp (seconds)
            "Dec 1, 2023 10:30 AM",       // Human readable
            "2023/12/01 10:30:00",        // Alternative format
            "01-Dec-2023 10:30:00"        // Another format
        };

        for (String timestamp : timestamps) {
            userCause.setTimestamp(timestamp);
            assertEquals(timestamp, userCause.getTimestamp());
        }
    }

    @Test
    public void testMinimalUserCause() {
        userCause.setUser_id("U1");
        userCause.setCause_id("C1");
        userCause.setCause_desc("Test");
        userCause.setTimestamp("1");

        assertEquals("U1", userCause.getUser_id());
        assertEquals("C1", userCause.getCause_id());
        assertEquals("Test", userCause.getCause_desc());
        assertEquals("1", userCause.getTimestamp());
    }

    @Test
    public void testMaximalUserCause() {
        String longUserId = "USER_" + "X".repeat(100);
        String longCauseId = "CAUSE_" + "Y".repeat(100);
        String longCauseDesc = "Very detailed cause description that includes comprehensive information about the cause's mission, vision, goals, strategies, impact, history, and future plans. ".repeat(10);
        String detailedTimestamp = "2023-12-01T10:30:45.123456789Z";

        userCause.setUser_id(longUserId);
        userCause.setCause_id(longCauseId);
        userCause.setCause_desc(longCauseDesc);
        userCause.setTimestamp(detailedTimestamp);

        assertEquals(longUserId, userCause.getUser_id());
        assertEquals(longCauseId, userCause.getCause_id());
        assertEquals(longCauseDesc, userCause.getCause_desc());
        assertEquals(detailedTimestamp, userCause.getTimestamp());
    }

    @Test
    public void testMultipleUserCauseUpdates() {
        // Test updating fields multiple times
        userCause.setUser_id("USER1");
        userCause.setCause_id("CAUSE1");
        userCause.setCause_desc("First cause");
        userCause.setTimestamp("2023-01-01T00:00:00Z");

        assertEquals("USER1", userCause.getUser_id());
        assertEquals("CAUSE1", userCause.getCause_id());
        assertEquals("First cause", userCause.getCause_desc());
        assertEquals("2023-01-01T00:00:00Z", userCause.getTimestamp());

        // Update all fields
        userCause.setUser_id("USER2");
        userCause.setCause_id("CAUSE2");
        userCause.setCause_desc("Second cause");
        userCause.setTimestamp("2023-06-15T12:30:00Z");

        assertEquals("USER2", userCause.getUser_id());
        assertEquals("CAUSE2", userCause.getCause_id());
        assertEquals("Second cause", userCause.getCause_desc());
        assertEquals("2023-06-15T12:30:00Z", userCause.getTimestamp());
    }

    @Test
    public void testUserFollowingMultipleCauses() {
        // Simulate the same user following different causes
        String userId = "USER123";

        String[] causes = {
            "Environmental Protection",
            "Human Rights",
            "Animal Welfare",
            "Labor Rights"
        };

        String[] causeIds = {
            "ENV001",
            "HUM002",
            "ANI003",
            "LAB004"
        };

        for (int i = 0; i < causes.length; i++) {
            userCause.setUser_id(userId);
            userCause.setCause_id(causeIds[i]);
            userCause.setCause_desc(causes[i]);
            userCause.setTimestamp("2023-12-" + String.format("%02d", i + 1) + "T10:00:00Z");

            assertEquals(userId, userCause.getUser_id());
            assertEquals(causeIds[i], userCause.getCause_id());
            assertEquals(causes[i], userCause.getCause_desc());
        }
    }

    @Test
    public void testCauseWithMultipleFollowers() {
        // Simulate the same cause being followed by different users
        String causeId = "CLIMATE001";
        String causeDesc = "Climate Change Action";

        String[] userIds = {
            "USER001",
            "USER002",
            "USER003",
            "USER004"
        };

        for (String userId : userIds) {
            userCause.setUser_id(userId);
            userCause.setCause_id(causeId);
            userCause.setCause_desc(causeDesc);
            userCause.setTimestamp("2023-12-01T10:00:00Z");

            assertEquals(userId, userCause.getUser_id());
            assertEquals(causeId, userCause.getCause_id());
            assertEquals(causeDesc, userCause.getCause_desc());
        }
    }

    @Test
    public void testWhitespaceHandling() {
        userCause.setUser_id("  USER123  ");
        userCause.setCause_id("  CAUSE456  ");
        userCause.setCause_desc("  Environmental Protection  ");
        userCause.setTimestamp("  2023-12-01T10:00:00Z  ");

        assertEquals("  USER123  ", userCause.getUser_id());
        assertEquals("  CAUSE456  ", userCause.getCause_id());
        assertEquals("  Environmental Protection  ", userCause.getCause_desc());
        assertEquals("  2023-12-01T10:00:00Z  ", userCause.getTimestamp());
    }

    @Test
    public void testUnicodeCharacters() {
        userCause.setUser_id("用户123");
        userCause.setCause_id("事业456");
        userCause.setCause_desc("环境保护与可持续发展");
        userCause.setTimestamp("2023-12-01T10:00:00Z");

        assertEquals("用户123", userCause.getUser_id());
        assertEquals("事业456", userCause.getCause_id());
        assertEquals("环境保护与可持续发展", userCause.getCause_desc());
    }
}