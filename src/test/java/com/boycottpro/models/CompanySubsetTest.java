package com.boycottpro.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive test suite for CompanySubset model class
 */
public class CompanySubsetTest {

    private CompanySubset companySubset;

    @BeforeEach
    public void setUp() {
        companySubset = new CompanySubset();
    }

    @Test
    public void testDefaultConstructor() {
        assertEquals(0, companySubset.getRank());
        assertNull(companySubset.getCompany_id());
        assertNull(companySubset.getCompany_name());
        assertEquals(0, companySubset.getBoycott_count());
    }

    @Test
    public void testParameterizedConstructor() {
        CompanySubset testSubset = new CompanySubset(
            1,
            "COMP123",
            "Apple Inc.",
            15000
        );

        assertEquals(1, testSubset.getRank());
        assertEquals("COMP123", testSubset.getCompany_id());
        assertEquals("Apple Inc.", testSubset.getCompany_name());
        assertEquals(15000, testSubset.getBoycott_count());
    }

    @Test
    public void testRankGetterSetter() {
        int rank = 5;
        companySubset.setRank(rank);
        assertEquals(rank, companySubset.getRank());
    }

    @Test
    public void testCompanyIdGetterSetter() {
        String companyId = "COMP456";
        companySubset.setCompany_id(companyId);
        assertEquals(companyId, companySubset.getCompany_id());
    }

    @Test
    public void testCompanyNameGetterSetter() {
        String companyName = "Microsoft Corporation";
        companySubset.setCompany_name(companyName);
        assertEquals(companyName, companySubset.getCompany_name());
    }

    @Test
    public void testBoycottCountGetterSetter() {
        int boycottCount = 8500;
        companySubset.setBoycott_count(boycottCount);
        assertEquals(boycottCount, companySubset.getBoycott_count());
    }

    @Test
    public void testCompleteSubsetScenario() {
        companySubset.setRank(3);
        companySubset.setCompany_id("TECH789");
        companySubset.setCompany_name("Amazon");
        companySubset.setBoycott_count(12000);

        assertEquals(3, companySubset.getRank());
        assertEquals("TECH789", companySubset.getCompany_id());
        assertEquals("Amazon", companySubset.getCompany_name());
        assertEquals(12000, companySubset.getBoycott_count());
    }

    @Test
    public void testTopRankedCompany() {
        companySubset.setRank(1);
        companySubset.setCompany_id("CONTROVERSIAL001");
        companySubset.setCompany_name("Most Boycotted Company");
        companySubset.setBoycott_count(50000);

        assertEquals(1, companySubset.getRank());
        assertEquals("CONTROVERSIAL001", companySubset.getCompany_id());
        assertEquals("Most Boycotted Company", companySubset.getCompany_name());
        assertEquals(50000, companySubset.getBoycott_count());
    }

    @Test
    public void testLowRankedCompany() {
        companySubset.setRank(100);
        companySubset.setCompany_id("MINOR001");
        companySubset.setCompany_name("Small Company");
        companySubset.setBoycott_count(50);

        assertEquals(100, companySubset.getRank());
        assertEquals("MINOR001", companySubset.getCompany_id());
        assertEquals("Small Company", companySubset.getCompany_name());
        assertEquals(50, companySubset.getBoycott_count());
    }

    @Test
    public void testNullStringFields() {
        companySubset.setCompany_id(null);
        companySubset.setCompany_name(null);

        assertNull(companySubset.getCompany_id());
        assertNull(companySubset.getCompany_name());
    }

    @Test
    public void testEmptyStringFields() {
        companySubset.setCompany_id("");
        companySubset.setCompany_name("");

        assertEquals("", companySubset.getCompany_id());
        assertEquals("", companySubset.getCompany_name());
    }

    @Test
    public void testZeroValues() {
        companySubset.setRank(0);
        companySubset.setBoycott_count(0);

        assertEquals(0, companySubset.getRank());
        assertEquals(0, companySubset.getBoycott_count());
    }

    @Test
    public void testNegativeRank() {
        companySubset.setRank(-1);
        assertEquals(-1, companySubset.getRank());
    }

    @Test
    public void testNegativeBoycottCount() {
        companySubset.setBoycott_count(-1);
        assertEquals(-1, companySubset.getBoycott_count());
    }

    @Test
    public void testMaximumValues() {
        companySubset.setRank(Integer.MAX_VALUE);
        companySubset.setBoycott_count(Integer.MAX_VALUE);

        assertEquals(Integer.MAX_VALUE, companySubset.getRank());
        assertEquals(Integer.MAX_VALUE, companySubset.getBoycott_count());
    }

    @Test
    public void testRankingScenarios() {
        // Test various ranking scenarios
        int[] ranks = {1, 5, 10, 25, 50, 100, 500, 1000};
        String[] companyNames = {
            "Top Boycotted Company",
            "Fifth Place Company",
            "Tenth Place Company",
            "Twenty-Fifth Company",
            "Fiftieth Company",
            "Hundredth Company",
            "Five Hundredth Company",
            "Thousandth Company"
        };

        for (int i = 0; i < ranks.length; i++) {
            companySubset.setRank(ranks[i]);
            companySubset.setCompany_name(companyNames[i]);

            assertEquals(ranks[i], companySubset.getRank());
            assertEquals(companyNames[i], companySubset.getCompany_name());
        }
    }

    @Test
    public void testBoycottCountRanges() {
        // Test different boycott count ranges
        Object[][] testData = {
            {1, "LOW001", "Low Boycott Company", 100},
            {2, "MED001", "Medium Boycott Company", 5000},
            {3, "HIGH001", "High Boycott Company", 25000},
            {4, "VIRAL001", "Viral Boycott Company", 100000},
            {5, "MASSIVE001", "Massive Boycott Company", 500000}
        };

        for (Object[] data : testData) {
            companySubset.setRank((Integer) data[0]);
            companySubset.setCompany_id((String) data[1]);
            companySubset.setCompany_name((String) data[2]);
            companySubset.setBoycott_count((Integer) data[3]);

            assertEquals(data[0], companySubset.getRank());
            assertEquals(data[1], companySubset.getCompany_id());
            assertEquals(data[2], companySubset.getCompany_name());
            assertEquals(data[3], companySubset.getBoycott_count());
        }
    }

    @Test
    public void testRealWorldCompanySubsets() {
        // Test realistic company subset scenarios
        Object[][] companies = {
            {1, "META001", "Meta Platforms", 45000},
            {2, "AMAZON001", "Amazon", 38000},
            {3, "APPLE001", "Apple Inc.", 32000},
            {4, "GOOGLE001", "Alphabet Inc.", 28000},
            {5, "TESLA001", "Tesla Inc.", 25000},
            {6, "WALMART001", "Walmart", 22000},
            {7, "EXXON001", "ExxonMobil", 20000},
            {8, "NESTLE001", "Nestlé", 18000},
            {9, "COCA001", "Coca-Cola", 15000},
            {10, "MCDONALDS001", "McDonald's", 12000}
        };

        for (Object[] company : companies) {
            companySubset.setRank((Integer) company[0]);
            companySubset.setCompany_id((String) company[1]);
            companySubset.setCompany_name((String) company[2]);
            companySubset.setBoycott_count((Integer) company[3]);

            assertEquals(company[0], companySubset.getRank());
            assertEquals(company[1], companySubset.getCompany_id());
            assertEquals(company[2], companySubset.getCompany_name());
            assertEquals(company[3], companySubset.getBoycott_count());
        }
    }

    @Test
    public void testSpecialCharactersInCompanyName() {
        companySubset.setCompany_id("SPECIAL-123_$%");
        companySubset.setCompany_name("AT&T Inc. (formerly AT&T)");

        assertEquals("SPECIAL-123_$%", companySubset.getCompany_id());
        assertEquals("AT&T Inc. (formerly AT&T)", companySubset.getCompany_name());
    }

    @Test
    public void testLongCompanyName() {
        String longName = "Very Long Corporation Name That Includes Multiple Business Units, Subsidiaries, and International Operations Across Various Industries";
        companySubset.setCompany_name(longName);
        assertEquals(longName, companySubset.getCompany_name());
    }

    @Test
    public void testMinimalValidSubset() {
        companySubset.setRank(1);
        companySubset.setCompany_id("A");
        companySubset.setCompany_name("B");
        companySubset.setBoycott_count(1);

        assertEquals(1, companySubset.getRank());
        assertEquals("A", companySubset.getCompany_id());
        assertEquals("B", companySubset.getCompany_name());
        assertEquals(1, companySubset.getBoycott_count());
    }

    @Test
    public void testMultipleUpdates() {
        // Test updating the same subset object multiple times
        companySubset.setRank(10);
        companySubset.setCompany_id("INITIAL");
        companySubset.setCompany_name("Initial Company");
        companySubset.setBoycott_count(1000);

        assertEquals(10, companySubset.getRank());
        assertEquals("INITIAL", companySubset.getCompany_id());
        assertEquals("Initial Company", companySubset.getCompany_name());
        assertEquals(1000, companySubset.getBoycott_count());

        // Update values
        companySubset.setRank(5);
        companySubset.setCompany_id("UPDATED");
        companySubset.setCompany_name("Updated Company");
        companySubset.setBoycott_count(2000);

        assertEquals(5, companySubset.getRank());
        assertEquals("UPDATED", companySubset.getCompany_id());
        assertEquals("Updated Company", companySubset.getCompany_name());
        assertEquals(2000, companySubset.getBoycott_count());
    }

    @Test
    public void testRankConsistencyWithBoycottCount() {
        // Test that rank generally corresponds to boycott count (higher boycott = lower rank number)
        Object[][] data = {
            {1, 50000}, // Rank 1 (highest boycotts)
            {2, 45000}, // Rank 2
            {3, 40000}, // Rank 3
            {4, 35000}, // Rank 4
            {5, 30000}  // Rank 5
        };

        for (Object[] entry : data) {
            companySubset.setRank((Integer) entry[0]);
            companySubset.setBoycott_count((Integer) entry[1]);

            assertEquals(entry[0], companySubset.getRank());
            assertEquals(entry[1], companySubset.getBoycott_count());
        }
    }

    @Test
    public void testInternationalCompanyNames() {
        String[] internationalNames = {
            "Nestlé S.A.",
            "ASML Holding N.V.",
            "SAP SE",
            "Volkswagen AG",
            "Unilever PLC",
            "TSMC (Taiwan Semiconductor)",
            "Tencent Holdings Ltd.",
            "Alibaba Group"
        };

        for (String name : internationalNames) {
            companySubset.setCompany_name(name);
            assertEquals(name, companySubset.getCompany_name());
        }
    }

    @Test
    public void testEdgeCaseRanks() {
        // Test edge case ranking scenarios
        int[] edgeRanks = {-100, -1, 0, 1, 999999, Integer.MAX_VALUE, Integer.MIN_VALUE};

        for (int rank : edgeRanks) {
            companySubset.setRank(rank);
            assertEquals(rank, companySubset.getRank());
        }
    }
}