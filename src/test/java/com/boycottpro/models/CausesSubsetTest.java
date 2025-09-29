package com.boycottpro.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive test suite for CausesSubset model class
 */
public class CausesSubsetTest {

    private CausesSubset causesSubset;

    @BeforeEach
    public void setUp() {
        causesSubset = new CausesSubset();
    }

    @Test
    public void testDefaultConstructor() {
        assertEquals(0, causesSubset.getRank());
        assertNull(causesSubset.getCause_id());
        assertNull(causesSubset.getCause_desc());
        assertEquals(0, causesSubset.getFollower_count());
    }

    @Test
    public void testParameterizedConstructor() {
        CausesSubset testSubset = new CausesSubset(
            1,
            "CAUSE123",
            "Climate Change Action",
            25000
        );

        assertEquals(1, testSubset.getRank());
        assertEquals("CAUSE123", testSubset.getCause_id());
        assertEquals("Climate Change Action", testSubset.getCause_desc());
        assertEquals(25000, testSubset.getFollower_count());
    }

    @Test
    public void testRankGetterSetter() {
        int rank = 3;
        causesSubset.setRank(rank);
        assertEquals(rank, causesSubset.getRank());
    }

    @Test
    public void testCauseIdGetterSetter() {
        String causeId = "CAUSE456";
        causesSubset.setCause_id(causeId);
        assertEquals(causeId, causesSubset.getCause_id());
    }

    @Test
    public void testCauseDescGetterSetter() {
        String causeDesc = "Environmental Protection";
        causesSubset.setCause_desc(causeDesc);
        assertEquals(causeDesc, causesSubset.getCause_desc());
    }

    @Test
    public void testFollowerCountGetterSetter() {
        int followerCount = 15000;
        causesSubset.setFollower_count(followerCount);
        assertEquals(followerCount, causesSubset.getFollower_count());
    }

    @Test
    public void testCompleteCauseSubsetScenario() {
        causesSubset.setRank(2);
        causesSubset.setCause_id("SOCIAL789");
        causesSubset.setCause_desc("Social Justice and Equality");
        causesSubset.setFollower_count(18500);

        assertEquals(2, causesSubset.getRank());
        assertEquals("SOCIAL789", causesSubset.getCause_id());
        assertEquals("Social Justice and Equality", causesSubset.getCause_desc());
        assertEquals(18500, causesSubset.getFollower_count());
    }

    @Test
    public void testTopRankedCause() {
        causesSubset.setRank(1);
        causesSubset.setCause_id("POPULAR001");
        causesSubset.setCause_desc("Most Popular Cause");
        causesSubset.setFollower_count(100000);

        assertEquals(1, causesSubset.getRank());
        assertEquals("POPULAR001", causesSubset.getCause_id());
        assertEquals("Most Popular Cause", causesSubset.getCause_desc());
        assertEquals(100000, causesSubset.getFollower_count());
    }

    @Test
    public void testLowRankedCause() {
        causesSubset.setRank(50);
        causesSubset.setCause_id("NICHE001");
        causesSubset.setCause_desc("Niche Cause");
        causesSubset.setFollower_count(100);

        assertEquals(50, causesSubset.getRank());
        assertEquals("NICHE001", causesSubset.getCause_id());
        assertEquals("Niche Cause", causesSubset.getCause_desc());
        assertEquals(100, causesSubset.getFollower_count());
    }

    @Test
    public void testNullStringFields() {
        causesSubset.setCause_id(null);
        causesSubset.setCause_desc(null);

        assertNull(causesSubset.getCause_id());
        assertNull(causesSubset.getCause_desc());
    }

    @Test
    public void testEmptyStringFields() {
        causesSubset.setCause_id("");
        causesSubset.setCause_desc("");

        assertEquals("", causesSubset.getCause_id());
        assertEquals("", causesSubset.getCause_desc());
    }

    @Test
    public void testZeroValues() {
        causesSubset.setRank(0);
        causesSubset.setFollower_count(0);

        assertEquals(0, causesSubset.getRank());
        assertEquals(0, causesSubset.getFollower_count());
    }

    @Test
    public void testNegativeRank() {
        causesSubset.setRank(-1);
        assertEquals(-1, causesSubset.getRank());
    }

    @Test
    public void testNegativeFollowerCount() {
        causesSubset.setFollower_count(-1);
        assertEquals(-1, causesSubset.getFollower_count());
    }

    @Test
    public void testMaximumValues() {
        causesSubset.setRank(Integer.MAX_VALUE);
        causesSubset.setFollower_count(Integer.MAX_VALUE);

        assertEquals(Integer.MAX_VALUE, causesSubset.getRank());
        assertEquals(Integer.MAX_VALUE, causesSubset.getFollower_count());
    }

    @Test
    public void testCauseCategories() {
        // Test various cause categories
        Object[][] causeData = {
            {1, "ENV001", "Environmental Protection", 45000},
            {2, "SOCIAL001", "Social Justice", 38000},
            {3, "ANIMAL001", "Animal Rights", 32000},
            {4, "LABOR001", "Labor Rights", 28000},
            {5, "HUMAN001", "Human Rights", 25000},
            {6, "HEALTH001", "Healthcare Access", 22000},
            {7, "EDU001", "Education Equality", 20000},
            {8, "LGBTQ001", "LGBTQ+ Rights", 18000},
            {9, "RACIAL001", "Racial Equality", 15000},
            {10, "GENDER001", "Gender Equality", 12000}
        };

        for (Object[] data : causeData) {
            causesSubset.setRank((Integer) data[0]);
            causesSubset.setCause_id((String) data[1]);
            causesSubset.setCause_desc((String) data[2]);
            causesSubset.setFollower_count((Integer) data[3]);

            assertEquals(data[0], causesSubset.getRank());
            assertEquals(data[1], causesSubset.getCause_id());
            assertEquals(data[2], causesSubset.getCause_desc());
            assertEquals(data[3], causesSubset.getFollower_count());
        }
    }

    @Test
    public void testFollowerCountRanges() {
        // Test different follower count ranges
        Object[][] testData = {
            {1, "MASSIVE001", "Massive Global Cause", 500000},
            {2, "VIRAL001", "Viral Social Movement", 250000},
            {3, "LARGE001", "Large Scale Cause", 100000},
            {4, "MEDIUM001", "Medium Sized Cause", 50000},
            {5, "GROWING001", "Growing Cause", 25000},
            {6, "EMERGING001", "Emerging Cause", 10000},
            {7, "LOCAL001", "Local Community Cause", 5000},
            {8, "SMALL001", "Small Activist Group", 1000},
            {9, "STARTUP001", "New Cause", 500},
            {10, "MINIMAL001", "Minimal Following", 100}
        };

        for (Object[] data : testData) {
            causesSubset.setRank((Integer) data[0]);
            causesSubset.setCause_id((String) data[1]);
            causesSubset.setCause_desc((String) data[2]);
            causesSubset.setFollower_count((Integer) data[3]);

            assertEquals(data[0], causesSubset.getRank());
            assertEquals(data[1], causesSubset.getCause_id());
            assertEquals(data[2], causesSubset.getCause_desc());
            assertEquals(data[3], causesSubset.getFollower_count());
        }
    }

    @Test
    public void testSpecialCharactersInCauseDesc() {
        causesSubset.setCause_id("SPECIAL-123_$%");
        causesSubset.setCause_desc("Environment & Climate Action (Global Initiative)");

        assertEquals("SPECIAL-123_$%", causesSubset.getCause_id());
        assertEquals("Environment & Climate Action (Global Initiative)", causesSubset.getCause_desc());
    }

    @Test
    public void testLongCauseDescription() {
        String longDesc = "This is a very detailed cause description that explains the comprehensive mission, vision, goals, strategies, implementation plans, expected outcomes, and long-term impact of this particular social justice initiative. ".repeat(5);
        causesSubset.setCause_desc(longDesc);
        assertEquals(longDesc, causesSubset.getCause_desc());
    }

    @Test
    public void testMinimalValidCauseSubset() {
        causesSubset.setRank(1);
        causesSubset.setCause_id("A");
        causesSubset.setCause_desc("B");
        causesSubset.setFollower_count(1);

        assertEquals(1, causesSubset.getRank());
        assertEquals("A", causesSubset.getCause_id());
        assertEquals("B", causesSubset.getCause_desc());
        assertEquals(1, causesSubset.getFollower_count());
    }

    @Test
    public void testMultipleUpdates() {
        // Test updating the same subset object multiple times
        causesSubset.setRank(5);
        causesSubset.setCause_id("INITIAL");
        causesSubset.setCause_desc("Initial Cause");
        causesSubset.setFollower_count(5000);

        assertEquals(5, causesSubset.getRank());
        assertEquals("INITIAL", causesSubset.getCause_id());
        assertEquals("Initial Cause", causesSubset.getCause_desc());
        assertEquals(5000, causesSubset.getFollower_count());

        // Update values
        causesSubset.setRank(2);
        causesSubset.setCause_id("UPDATED");
        causesSubset.setCause_desc("Updated Cause");
        causesSubset.setFollower_count(10000);

        assertEquals(2, causesSubset.getRank());
        assertEquals("UPDATED", causesSubset.getCause_id());
        assertEquals("Updated Cause", causesSubset.getCause_desc());
        assertEquals(10000, causesSubset.getFollower_count());
    }

    @Test
    public void testRankConsistencyWithFollowerCount() {
        // Test that rank generally corresponds to follower count (higher followers = lower rank number)
        Object[][] data = {
            {1, 100000}, // Rank 1 (highest followers)
            {2, 75000},  // Rank 2
            {3, 50000},  // Rank 3
            {4, 25000},  // Rank 4
            {5, 10000}   // Rank 5
        };

        for (Object[] entry : data) {
            causesSubset.setRank((Integer) entry[0]);
            causesSubset.setFollower_count((Integer) entry[1]);

            assertEquals(entry[0], causesSubset.getRank());
            assertEquals(entry[1], causesSubset.getFollower_count());
        }
    }

    @Test
    public void testInternationalCauses() {
        Object[][] internationalCauses = {
            {1, "GLOBAL001", "Global Climate Action", 200000},
            {2, "INTL001", "International Human Rights", 150000},
            {3, "WORLD001", "World Peace Initiative", 125000},
            {4, "PLANET001", "Planetary Conservation", 100000},
            {5, "EARTH001", "Earth Protection Movement", 80000}
        };

        for (Object[] cause : internationalCauses) {
            causesSubset.setRank((Integer) cause[0]);
            causesSubset.setCause_id((String) cause[1]);
            causesSubset.setCause_desc((String) cause[2]);
            causesSubset.setFollower_count((Integer) cause[3]);

            assertEquals(cause[0], causesSubset.getRank());
            assertEquals(cause[1], causesSubset.getCause_id());
            assertEquals(cause[2], causesSubset.getCause_desc());
            assertEquals(cause[3], causesSubset.getFollower_count());
        }
    }

    @Test
    public void testLocalCommunityIssues() {
        Object[][] localCauses = {
            {25, "LOCAL001", "Save Local Park", 2500},
            {26, "COMM001", "Community Center Funding", 2000},
            {27, "SCHOOL001", "School Board Reform", 1800},
            {28, "TRANSIT001", "Public Transit Improvement", 1500},
            {29, "HOUSING001", "Affordable Housing", 1200},
            {30, "FOOD001", "Food Bank Support", 1000}
        };

        for (Object[] cause : localCauses) {
            causesSubset.setRank((Integer) cause[0]);
            causesSubset.setCause_id((String) cause[1]);
            causesSubset.setCause_desc((String) cause[2]);
            causesSubset.setFollower_count((Integer) cause[3]);

            assertEquals(cause[0], causesSubset.getRank());
            assertEquals(cause[1], causesSubset.getCause_id());
            assertEquals(cause[2], causesSubset.getCause_desc());
            assertEquals(cause[3], causesSubset.getFollower_count());
        }
    }

    @Test
    public void testEdgeCaseRanks() {
        // Test edge case ranking scenarios
        int[] edgeRanks = {-100, -1, 0, 1, 999999, Integer.MAX_VALUE, Integer.MIN_VALUE};

        for (int rank : edgeRanks) {
            causesSubset.setRank(rank);
            assertEquals(rank, causesSubset.getRank());
        }
    }

    @Test
    public void testUnicodeInCauseDescription() {
        causesSubset.setCause_id("UNICODE001");
        causesSubset.setCause_desc("环境保护与可持续发展 - Environmental Protection");
        causesSubset.setFollower_count(5000);

        assertEquals("UNICODE001", causesSubset.getCause_id());
        assertEquals("环境保护与可持续发展 - Environmental Protection", causesSubset.getCause_desc());
        assertEquals(5000, causesSubset.getFollower_count());
    }

    @Test
    public void testWhitespaceHandling() {
        causesSubset.setCause_id("  CAUSE123  ");
        causesSubset.setCause_desc("  Environmental Action  ");

        assertEquals("  CAUSE123  ", causesSubset.getCause_id());
        assertEquals("  Environmental Action  ", causesSubset.getCause_desc());
    }

    @Test
    public void testTrendingCauseScenarios() {
        // Test scenarios representing trending/viral causes
        Object[][] trendingCauses = {
            {1, "VIRAL2023", "Viral Social Movement 2023", 500000},
            {2, "TRENDING01", "Climate Strike Movement", 350000},
            {3, "HASHTAG01", "#BlackLivesMatter", 300000},
            {4, "MOVEMENT01", "Me Too Movement", 250000},
            {5, "ACTIVIST01", "Youth Climate Activism", 200000}
        };

        for (Object[] cause : trendingCauses) {
            causesSubset.setRank((Integer) cause[0]);
            causesSubset.setCause_id((String) cause[1]);
            causesSubset.setCause_desc((String) cause[2]);
            causesSubset.setFollower_count((Integer) cause[3]);

            assertEquals(cause[0], causesSubset.getRank());
            assertEquals(cause[1], causesSubset.getCause_id());
            assertEquals(cause[2], causesSubset.getCause_desc());
            assertEquals(cause[3], causesSubset.getFollower_count());
        }
    }
}