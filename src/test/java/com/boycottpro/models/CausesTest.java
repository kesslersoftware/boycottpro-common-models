package com.boycottpro.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive test suite for Causes model class
 */
public class CausesTest {

    private Causes cause;

    @BeforeEach
    public void setUp() {
        cause = new Causes();
    }

    @Test
    public void testDefaultConstructor() {
        assertNull(cause.getCause_id());
        assertNull(cause.getCategory());
        assertNull(cause.getCause_desc());
        assertEquals(0, cause.getFollower_count());
    }

    @Test
    public void testParameterizedConstructor() {
        Causes testCause = new Causes(
            "CAUSE123",
            "Environmental",
            "Climate change awareness and action",
            5000
        );

        assertEquals("CAUSE123", testCause.getCause_id());
        assertEquals("Environmental", testCause.getCategory());
        assertEquals("Climate change awareness and action", testCause.getCause_desc());
        assertEquals(5000, testCause.getFollower_count());
    }

    @Test
    public void testCauseIdGetterSetter() {
        String causeId = "CAUSE456";
        cause.setCause_id(causeId);
        assertEquals(causeId, cause.getCause_id());
    }

    @Test
    public void testCategoryGetterSetter() {
        String category = "Social Justice";
        cause.setCategory(category);
        assertEquals(category, cause.getCategory());
    }

    @Test
    public void testCauseDescGetterSetter() {
        String description = "Fighting for equal rights and justice for all people";
        cause.setCause_desc(description);
        assertEquals(description, cause.getCause_desc());
    }

    @Test
    public void testFollowerCountGetterSetter() {
        int followerCount = 10000;
        cause.setFollower_count(followerCount);
        assertEquals(followerCount, cause.getFollower_count());
    }

    @Test
    public void testNullStringFields() {
        cause.setCause_id(null);
        cause.setCategory(null);
        cause.setCause_desc(null);

        assertNull(cause.getCause_id());
        assertNull(cause.getCategory());
        assertNull(cause.getCause_desc());
    }

    @Test
    public void testEmptyStringFields() {
        cause.setCause_id("");
        cause.setCategory("");
        cause.setCause_desc("");

        assertEquals("", cause.getCause_id());
        assertEquals("", cause.getCategory());
        assertEquals("", cause.getCause_desc());
    }

    @Test
    public void testZeroFollowerCount() {
        cause.setFollower_count(0);
        assertEquals(0, cause.getFollower_count());
    }

    @Test
    public void testNegativeFollowerCount() {
        cause.setFollower_count(-1);
        assertEquals(-1, cause.getFollower_count());
    }

    @Test
    public void testMaximumFollowerCount() {
        cause.setFollower_count(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, cause.getFollower_count());
    }

    @Test
    public void testLargeCauseDescription() {
        String largeDescription = "A".repeat(1000);
        cause.setCause_desc(largeDescription);
        assertEquals(largeDescription, cause.getCause_desc());
        assertEquals(1000, cause.getCause_desc().length());
    }

    @Test
    public void testSpecialCharactersInFields() {
        String specialId = "CAUSE-123_$%";
        String specialCategory = "Human Rights & Freedom";
        String specialDesc = "Fighting for justice, equality & peace!";

        cause.setCause_id(specialId);
        cause.setCategory(specialCategory);
        cause.setCause_desc(specialDesc);

        assertEquals(specialId, cause.getCause_id());
        assertEquals(specialCategory, cause.getCategory());
        assertEquals(specialDesc, cause.getCause_desc());
    }

    @Test
    public void testCauseCategories() {
        String[] categories = {
            "Environmental",
            "Social Justice",
            "Animal Rights",
            "Labor Rights",
            "Human Rights",
            "Healthcare",
            "Education",
            "Political",
            "Economic Justice"
        };

        for (String category : categories) {
            cause.setCategory(category);
            assertEquals(category, cause.getCategory());
        }
    }

    @Test
    public void testCauseWithMinimalData() {
        cause.setCause_id("MIN");
        cause.setCategory("Test");
        cause.setCause_desc("Minimal test cause");
        cause.setFollower_count(1);

        assertEquals("MIN", cause.getCause_id());
        assertEquals("Test", cause.getCategory());
        assertEquals("Minimal test cause", cause.getCause_desc());
        assertEquals(1, cause.getFollower_count());
    }

    @Test
    public void testCauseWithMaximalData() {
        String longId = "CAUSE_" + "X".repeat(100);
        String longCategory = "Very Long Category Name That Might Be Used";
        String longDescription = "This is a very detailed description of a cause that goes into extensive detail about the mission, goals, and objectives of the organization. ".repeat(10);
        int maxFollowers = 1000000;

        cause.setCause_id(longId);
        cause.setCategory(longCategory);
        cause.setCause_desc(longDescription);
        cause.setFollower_count(maxFollowers);

        assertEquals(longId, cause.getCause_id());
        assertEquals(longCategory, cause.getCategory());
        assertEquals(longDescription, cause.getCause_desc());
        assertEquals(maxFollowers, cause.getFollower_count());
    }
}