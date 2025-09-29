package com.boycottpro.utilities;

import com.boycottpro.models.Causes;
import com.boycottpro.models.CausesSubset;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Comprehensive test suite for CausesUtility class
 */
public class CausesUtilityTest {

    private Map<String, AttributeValue> fullCauseItem;
    private Map<String, AttributeValue> minimalCauseItem;
    private Map<String, AttributeValue> subsetCauseItem;

    @BeforeEach
    public void setUp() {
        // Full cause item with all fields
        fullCauseItem = new HashMap<>();
        fullCauseItem.put("cause_id", AttributeValue.fromS("CAUSE123"));
        fullCauseItem.put("category", AttributeValue.fromS("Environmental"));
        fullCauseItem.put("cause_desc", AttributeValue.fromS("Climate change awareness and action"));
        fullCauseItem.put("follower_count", AttributeValue.fromN("15000"));

        // Minimal cause item with only required fields
        minimalCauseItem = new HashMap<>();
        minimalCauseItem.put("cause_id", AttributeValue.fromS("CAUSE456"));
        minimalCauseItem.put("category", AttributeValue.fromS("Social Justice"));
        minimalCauseItem.put("cause_desc", AttributeValue.fromS("Fighting for equality"));

        // Subset cause item
        subsetCauseItem = new HashMap<>();
        subsetCauseItem.put("cause_id", AttributeValue.fromS("CAUSE789"));
        subsetCauseItem.put("cause_desc", AttributeValue.fromS("Animal rights protection"));
        subsetCauseItem.put("follower_count", AttributeValue.fromN("8500"));
    }

    @Test
    public void testMapToCausesWithFullData() {
        Causes cause = CausesUtility.mapToCauses(fullCauseItem);

        assertEquals("CAUSE123", cause.getCause_id());
        assertEquals("Environmental", cause.getCategory());
        assertEquals("Climate change awareness and action", cause.getCause_desc());
        assertEquals(15000, cause.getFollower_count());
    }

    @Test
    public void testMapToCausesWithMinimalData() {
        Causes cause = CausesUtility.mapToCauses(minimalCauseItem);

        assertEquals("CAUSE456", cause.getCause_id());
        assertEquals("Social Justice", cause.getCategory());
        assertEquals("Fighting for equality", cause.getCause_desc());
        assertEquals(0, cause.getFollower_count()); // Default value
    }

    @Test
    public void testMapToCausesWithZeroFollowerCount() {
        Map<String, AttributeValue> zeroItem = new HashMap<>();
        zeroItem.put("cause_id", AttributeValue.fromS("ZERO123"));
        zeroItem.put("category", AttributeValue.fromS("New Cause"));
        zeroItem.put("cause_desc", AttributeValue.fromS("Brand new cause"));
        zeroItem.put("follower_count", AttributeValue.fromN("0"));

        Causes cause = CausesUtility.mapToCauses(zeroItem);

        assertEquals("ZERO123", cause.getCause_id());
        assertEquals("New Cause", cause.getCategory());
        assertEquals("Brand new cause", cause.getCause_desc());
        assertEquals(0, cause.getFollower_count());
    }

    @Test
    public void testMapToCausesWithHighFollowerCount() {
        Map<String, AttributeValue> highItem = new HashMap<>();
        highItem.put("cause_id", AttributeValue.fromS("VIRAL123"));
        highItem.put("category", AttributeValue.fromS("Global Movement"));
        highItem.put("cause_desc", AttributeValue.fromS("Viral global cause"));
        highItem.put("follower_count", AttributeValue.fromN("1000000"));

        Causes cause = CausesUtility.mapToCauses(highItem);

        assertEquals("VIRAL123", cause.getCause_id());
        assertEquals("Global Movement", cause.getCategory());
        assertEquals("Viral global cause", cause.getCause_desc());
        assertEquals(1000000, cause.getFollower_count());
    }

    @Test
    public void testMapToSubsetWithFullData() {
        CausesSubset subset = CausesUtility.mapToSubset(subsetCauseItem);

        assertEquals("CAUSE789", subset.getCause_id());
        assertEquals("Animal rights protection", subset.getCause_desc());
        assertEquals(8500, subset.getFollower_count());
        assertEquals(0, subset.getRank()); // Rank is not set in the mapping
    }

    @Test
    public void testMapToSubsetWithMinimalData() {
        Map<String, AttributeValue> minimalSubsetItem = new HashMap<>();
        minimalSubsetItem.put("cause_id", AttributeValue.fromS("MIN123"));
        minimalSubsetItem.put("cause_desc", AttributeValue.fromS("Minimal cause"));

        CausesSubset subset = CausesUtility.mapToSubset(minimalSubsetItem);

        assertEquals("MIN123", subset.getCause_id());
        assertEquals("Minimal cause", subset.getCause_desc());
        assertEquals(0, subset.getFollower_count()); // Default value
        assertEquals(0, subset.getRank()); // Default value
    }

    @Test
    public void testMapToSubsetWithZeroFollowerCount() {
        Map<String, AttributeValue> zeroSubsetItem = new HashMap<>();
        zeroSubsetItem.put("cause_id", AttributeValue.fromS("ZERO456"));
        zeroSubsetItem.put("cause_desc", AttributeValue.fromS("Zero follower cause"));
        zeroSubsetItem.put("follower_count", AttributeValue.fromN("0"));

        CausesSubset subset = CausesUtility.mapToSubset(zeroSubsetItem);

        assertEquals("ZERO456", subset.getCause_id());
        assertEquals("Zero follower cause", subset.getCause_desc());
        assertEquals(0, subset.getFollower_count());
    }

    @Test
    public void testMapToCausesWithSpecialCharacters() {
        Map<String, AttributeValue> specialItem = new HashMap<>();
        specialItem.put("cause_id", AttributeValue.fromS("CAUSE-123_$%"));
        specialItem.put("category", AttributeValue.fromS("Human Rights & Freedom"));
        specialItem.put("cause_desc", AttributeValue.fromS("Fighting for justice, equality & peace!"));
        specialItem.put("follower_count", AttributeValue.fromN("25000"));

        Causes cause = CausesUtility.mapToCauses(specialItem);

        assertEquals("CAUSE-123_$%", cause.getCause_id());
        assertEquals("Human Rights & Freedom", cause.getCategory());
        assertEquals("Fighting for justice, equality & peace!", cause.getCause_desc());
        assertEquals(25000, cause.getFollower_count());
    }

    @Test
    public void testMapToCausesWithEmptyStrings() {
        Map<String, AttributeValue> emptyItem = new HashMap<>();
        emptyItem.put("cause_id", AttributeValue.fromS(""));
        emptyItem.put("category", AttributeValue.fromS(""));
        emptyItem.put("cause_desc", AttributeValue.fromS(""));
        emptyItem.put("follower_count", AttributeValue.fromN("100"));

        Causes cause = CausesUtility.mapToCauses(emptyItem);

        assertEquals("", cause.getCause_id());
        assertEquals("", cause.getCategory());
        assertEquals("", cause.getCause_desc());
        assertEquals(100, cause.getFollower_count());
    }

    @Test
    public void testMapToCausesWithLongDescription() {
        String longDescription = "This is a very detailed cause description that explains the comprehensive mission, vision, goals, strategies, implementation plans, expected outcomes, and long-term impact of this particular social justice initiative. ".repeat(10);

        Map<String, AttributeValue> longItem = new HashMap<>();
        longItem.put("cause_id", AttributeValue.fromS("LONG123"));
        longItem.put("category", AttributeValue.fromS("Detailed Cause"));
        longItem.put("cause_desc", AttributeValue.fromS(longDescription));
        longItem.put("follower_count", AttributeValue.fromN("5000"));

        Causes cause = CausesUtility.mapToCauses(longItem);

        assertEquals("LONG123", cause.getCause_id());
        assertEquals("Detailed Cause", cause.getCategory());
        assertEquals(longDescription, cause.getCause_desc());
        assertEquals(5000, cause.getFollower_count());
    }

    @Test
    public void testBothMappingMethods() {
        // Test that both mapping methods work correctly with the same data
        Map<String, AttributeValue> testItem = new HashMap<>();
        testItem.put("cause_id", AttributeValue.fromS("TEST123"));
        testItem.put("category", AttributeValue.fromS("Test Category"));
        testItem.put("cause_desc", AttributeValue.fromS("Test cause description"));
        testItem.put("follower_count", AttributeValue.fromN("12000"));

        Causes fullCause = CausesUtility.mapToCauses(testItem);
        CausesSubset subset = CausesUtility.mapToSubset(testItem);

        // Both should have the same core fields
        assertEquals(fullCause.getCause_id(), subset.getCause_id());
        assertEquals(fullCause.getCause_desc(), subset.getCause_desc());
        assertEquals(fullCause.getFollower_count(), subset.getFollower_count());

        // Full cause should have additional fields
        assertEquals("Test Category", fullCause.getCategory());

        // Subset should have default rank
        assertEquals(0, subset.getRank());
    }

    @Test
    public void testMapToCausesWithDifferentCategories() {
        String[] categories = {
            "Environmental",
            "Social Justice",
            "Animal Rights",
            "Labor Rights",
            "Human Rights",
            "Healthcare",
            "Education",
            "Political",
            "Economic Justice",
            "LGBTQ+ Rights",
            "Racial Equality",
            "Gender Equality"
        };

        for (int i = 0; i < categories.length; i++) {
            Map<String, AttributeValue> item = new HashMap<>();
            item.put("cause_id", AttributeValue.fromS("CAT" + i));
            item.put("category", AttributeValue.fromS(categories[i]));
            item.put("cause_desc", AttributeValue.fromS("Description for " + categories[i]));
            item.put("follower_count", AttributeValue.fromN(String.valueOf((i + 1) * 1000)));

            Causes cause = CausesUtility.mapToCauses(item);

            assertEquals("CAT" + i, cause.getCause_id());
            assertEquals(categories[i], cause.getCategory());
            assertEquals("Description for " + categories[i], cause.getCause_desc());
            assertEquals((i + 1) * 1000, cause.getFollower_count());
        }
    }

    @Test
    public void testMapToSubsetWithDifferentFollowerCounts() {
        int[] followerCounts = {1, 10, 100, 1000, 10000, 100000, 500000, 1000000};

        for (int i = 0; i < followerCounts.length; i++) {
            Map<String, AttributeValue> item = new HashMap<>();
            item.put("cause_id", AttributeValue.fromS("FOLLOW" + i));
            item.put("cause_desc", AttributeValue.fromS("Cause with " + followerCounts[i] + " followers"));
            item.put("follower_count", AttributeValue.fromN(String.valueOf(followerCounts[i])));

            CausesSubset subset = CausesUtility.mapToSubset(item);

            assertEquals("FOLLOW" + i, subset.getCause_id());
            assertEquals("Cause with " + followerCounts[i] + " followers", subset.getCause_desc());
            assertEquals(followerCounts[i], subset.getFollower_count());
        }
    }

    @Test
    public void testMapToCausesWithNegativeFollowerCount() {
        Map<String, AttributeValue> negativeItem = new HashMap<>();
        negativeItem.put("cause_id", AttributeValue.fromS("NEG001"));
        negativeItem.put("category", AttributeValue.fromS("Negative Test"));
        negativeItem.put("cause_desc", AttributeValue.fromS("Negative follower count test"));
        negativeItem.put("follower_count", AttributeValue.fromN("-1"));

        Causes cause = CausesUtility.mapToCauses(negativeItem);

        assertEquals("NEG001", cause.getCause_id());
        assertEquals("Negative Test", cause.getCategory());
        assertEquals("Negative follower count test", cause.getCause_desc());
        assertEquals(-1, cause.getFollower_count());
    }

    @Test
    public void testMapToCausesWithMaximumFollowerCount() {
        Map<String, AttributeValue> maxItem = new HashMap<>();
        maxItem.put("cause_id", AttributeValue.fromS("MAX001"));
        maxItem.put("category", AttributeValue.fromS("Maximum Test"));
        maxItem.put("cause_desc", AttributeValue.fromS("Maximum follower count test"));
        maxItem.put("follower_count", AttributeValue.fromN(String.valueOf(Integer.MAX_VALUE)));

        Causes cause = CausesUtility.mapToCauses(maxItem);

        assertEquals("MAX001", cause.getCause_id());
        assertEquals("Maximum Test", cause.getCategory());
        assertEquals("Maximum follower count test", cause.getCause_desc());
        assertEquals(Integer.MAX_VALUE, cause.getFollower_count());
    }

    @Test
    public void testRealWorldCauseScenarios() {
        // Test realistic cause mapping scenarios
        Object[][] causeData = {
            {"ENV001", "Environmental", "Climate Change Action", "250000"},
            {"SOC001", "Social Justice", "Black Lives Matter", "500000"},
            {"ANI001", "Animal Rights", "End Factory Farming", "150000"},
            {"LAB001", "Labor Rights", "Fair Wages Movement", "200000"},
            {"HUM001", "Human Rights", "Amnesty International", "1000000"},
            {"HEALTH001", "Healthcare", "Universal Healthcare", "300000"},
            {"EDU001", "Education", "Education for All", "180000"},
            {"LGBTQ001", "LGBTQ+ Rights", "Marriage Equality", "400000"},
            {"RACIAL001", "Racial Equality", "Stop Asian Hate", "350000"},
            {"GENDER001", "Gender Equality", "Me Too Movement", "600000"}
        };

        for (Object[] data : causeData) {
            Map<String, AttributeValue> item = new HashMap<>();
            item.put("cause_id", AttributeValue.fromS((String) data[0]));
            item.put("category", AttributeValue.fromS((String) data[1]));
            item.put("cause_desc", AttributeValue.fromS((String) data[2]));
            item.put("follower_count", AttributeValue.fromN((String) data[3]));

            Causes cause = CausesUtility.mapToCauses(item);
            CausesSubset subset = CausesUtility.mapToSubset(item);

            assertEquals(data[0], cause.getCause_id());
            assertEquals(data[1], cause.getCategory());
            assertEquals(data[2], cause.getCause_desc());
            assertEquals(Integer.parseInt((String) data[3]), cause.getFollower_count());

            // Test subset mapping as well
            assertEquals(data[0], subset.getCause_id());
            assertEquals(data[2], subset.getCause_desc());
            assertEquals(Integer.parseInt((String) data[3]), subset.getFollower_count());
        }
    }

    @Test
    public void testMapToCausesWithInternationalCauses() {
        Map<String, AttributeValue> intlItem = new HashMap<>();
        intlItem.put("cause_id", AttributeValue.fromS("INTL001"));
        intlItem.put("category", AttributeValue.fromS("Global Human Rights"));
        intlItem.put("cause_desc", AttributeValue.fromS("国际人权保护 - International Human Rights Protection"));
        intlItem.put("follower_count", AttributeValue.fromN("75000"));

        Causes cause = CausesUtility.mapToCauses(intlItem);

        assertEquals("INTL001", cause.getCause_id());
        assertEquals("Global Human Rights", cause.getCategory());
        assertEquals("国际人权保护 - International Human Rights Protection", cause.getCause_desc());
        assertEquals(75000, cause.getFollower_count());
    }

    @Test
    public void testMapToSubsetWithInternationalCauses() {
        Map<String, AttributeValue> intlSubsetItem = new HashMap<>();
        intlSubsetItem.put("cause_id", AttributeValue.fromS("INTL002"));
        intlSubsetItem.put("cause_desc", AttributeValue.fromS("Protección del Medio Ambiente - Environmental Protection"));
        intlSubsetItem.put("follower_count", AttributeValue.fromN("45000"));

        CausesSubset subset = CausesUtility.mapToSubset(intlSubsetItem);

        assertEquals("INTL002", subset.getCause_id());
        assertEquals("Protección del Medio Ambiente - Environmental Protection", subset.getCause_desc());
        assertEquals(45000, subset.getFollower_count());
    }

    @Test
    public void testEmergingCauseScenarios() {
        // Test scenarios for new/emerging causes
        Object[][] emergingCauses = {
            {"CRYPTO001", "Technology Ethics", "Cryptocurrency Environmental Impact", "5000"},
            {"AI001", "Technology Ethics", "AI Ethics and Safety", "25000"},
            {"SPACE001", "Environmental", "Space Debris Cleanup", "1500"},
            {"MENTAL001", "Healthcare", "Mental Health Awareness", "80000"},
            {"PRIVACY001", "Digital Rights", "Data Privacy Protection", "120000"}
        };

        for (Object[] data : emergingCauses) {
            Map<String, AttributeValue> item = new HashMap<>();
            item.put("cause_id", AttributeValue.fromS((String) data[0]));
            item.put("category", AttributeValue.fromS((String) data[1]));
            item.put("cause_desc", AttributeValue.fromS((String) data[2]));
            item.put("follower_count", AttributeValue.fromN((String) data[3]));

            Causes cause = CausesUtility.mapToCauses(item);

            assertEquals(data[0], cause.getCause_id());
            assertEquals(data[1], cause.getCategory());
            assertEquals(data[2], cause.getCause_desc());
            assertEquals(Integer.parseInt((String) data[3]), cause.getFollower_count());
        }
    }
}