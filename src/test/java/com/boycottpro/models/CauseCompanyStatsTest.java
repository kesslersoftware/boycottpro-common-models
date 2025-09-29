package com.boycottpro.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive test suite for CauseCompanyStats model class
 */
public class CauseCompanyStatsTest {

    private CauseCompanyStats causeCompanyStats;

    @BeforeEach
    public void setUp() {
        causeCompanyStats = new CauseCompanyStats();
    }

    @Test
    public void testDefaultConstructor() {
        assertNull(causeCompanyStats.getCause_id());
        assertNull(causeCompanyStats.getCause_desc());
        assertNull(causeCompanyStats.getCompany_id());
        assertNull(causeCompanyStats.getCompany_name());
        assertEquals(0, causeCompanyStats.getBoycott_count());
    }

    @Test
    public void testParameterizedConstructor() {
        CauseCompanyStats testStats = new CauseCompanyStats(
            "CAUSE123",
            "Environmental Protection",
            "COMP456",
            "ExxonMobil",
            2500
        );

        assertEquals("CAUSE123", testStats.getCause_id());
        assertEquals("Environmental Protection", testStats.getCause_desc());
        assertEquals("COMP456", testStats.getCompany_id());
        assertEquals("ExxonMobil", testStats.getCompany_name());
        assertEquals(2500, testStats.getBoycott_count());
    }

    @Test
    public void testCauseIdGetterSetter() {
        String causeId = "CAUSE789";
        causeCompanyStats.setCause_id(causeId);
        assertEquals(causeId, causeCompanyStats.getCause_id());
    }

    @Test
    public void testCauseDescGetterSetter() {
        String causeDesc = "Climate Change Action";
        causeCompanyStats.setCause_desc(causeDesc);
        assertEquals(causeDesc, causeCompanyStats.getCause_desc());
    }

    @Test
    public void testCompanyIdGetterSetter() {
        String companyId = "COMP321";
        causeCompanyStats.setCompany_id(companyId);
        assertEquals(companyId, causeCompanyStats.getCompany_id());
    }

    @Test
    public void testCompanyNameGetterSetter() {
        String companyName = "Amazon Inc.";
        causeCompanyStats.setCompany_name(companyName);
        assertEquals(companyName, causeCompanyStats.getCompany_name());
    }

    @Test
    public void testBoycottCountGetterSetter() {
        int boycottCount = 1500;
        causeCompanyStats.setBoycott_count(boycottCount);
        assertEquals(boycottCount, causeCompanyStats.getBoycott_count());
    }

    @Test
    public void testCompleteStatsScenario() {
        causeCompanyStats.setCause_id("LABOR001");
        causeCompanyStats.setCause_desc("Labor Rights Violations");
        causeCompanyStats.setCompany_id("RETAIL456");
        causeCompanyStats.setCompany_name("Walmart");
        causeCompanyStats.setBoycott_count(3200);

        assertEquals("LABOR001", causeCompanyStats.getCause_id());
        assertEquals("Labor Rights Violations", causeCompanyStats.getCause_desc());
        assertEquals("RETAIL456", causeCompanyStats.getCompany_id());
        assertEquals("Walmart", causeCompanyStats.getCompany_name());
        assertEquals(3200, causeCompanyStats.getBoycott_count());
    }

    @Test
    public void testNullStringFields() {
        causeCompanyStats.setCause_id(null);
        causeCompanyStats.setCause_desc(null);
        causeCompanyStats.setCompany_id(null);
        causeCompanyStats.setCompany_name(null);

        assertNull(causeCompanyStats.getCause_id());
        assertNull(causeCompanyStats.getCause_desc());
        assertNull(causeCompanyStats.getCompany_id());
        assertNull(causeCompanyStats.getCompany_name());
    }

    @Test
    public void testEmptyStringFields() {
        causeCompanyStats.setCause_id("");
        causeCompanyStats.setCause_desc("");
        causeCompanyStats.setCompany_id("");
        causeCompanyStats.setCompany_name("");

        assertEquals("", causeCompanyStats.getCause_id());
        assertEquals("", causeCompanyStats.getCause_desc());
        assertEquals("", causeCompanyStats.getCompany_id());
        assertEquals("", causeCompanyStats.getCompany_name());
    }

    @Test
    public void testZeroBoycottCount() {
        causeCompanyStats.setBoycott_count(0);
        assertEquals(0, causeCompanyStats.getBoycott_count());
    }

    @Test
    public void testNegativeBoycottCount() {
        causeCompanyStats.setBoycott_count(-1);
        assertEquals(-1, causeCompanyStats.getBoycott_count());
    }

    @Test
    public void testMaximumBoycottCount() {
        causeCompanyStats.setBoycott_count(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, causeCompanyStats.getBoycott_count());
    }

    @Test
    public void testHighBoycottCountScenarios() {
        // Test various high boycott count scenarios
        int[] highCounts = {10000, 50000, 100000, 500000, 1000000};

        for (int count : highCounts) {
            causeCompanyStats.setBoycott_count(count);
            assertEquals(count, causeCompanyStats.getBoycott_count());
        }
    }

    @Test
    public void testEnvironmentalCauseStats() {
        causeCompanyStats.setCause_id("ENV001");
        causeCompanyStats.setCause_desc("Environmental Damage");
        causeCompanyStats.setCompany_id("OIL123");
        causeCompanyStats.setCompany_name("Shell");
        causeCompanyStats.setBoycott_count(4500);

        assertEquals("ENV001", causeCompanyStats.getCause_id());
        assertEquals("Environmental Damage", causeCompanyStats.getCause_desc());
        assertEquals("OIL123", causeCompanyStats.getCompany_id());
        assertEquals("Shell", causeCompanyStats.getCompany_name());
        assertEquals(4500, causeCompanyStats.getBoycott_count());
    }

    @Test
    public void testLaborRightsCauseStats() {
        causeCompanyStats.setCause_id("LABOR002");
        causeCompanyStats.setCause_desc("Worker Exploitation");
        causeCompanyStats.setCompany_id("TECH789");
        causeCompanyStats.setCompany_name("Apple");
        causeCompanyStats.setBoycott_count(8200);

        assertEquals("LABOR002", causeCompanyStats.getCause_id());
        assertEquals("Worker Exploitation", causeCompanyStats.getCause_desc());
        assertEquals("TECH789", causeCompanyStats.getCompany_id());
        assertEquals("Apple", causeCompanyStats.getCompany_name());
        assertEquals(8200, causeCompanyStats.getBoycott_count());
    }

    @Test
    public void testHumanRightsCauseStats() {
        causeCompanyStats.setCause_id("HUMAN003");
        causeCompanyStats.setCause_desc("Human Rights Violations");
        causeCompanyStats.setCompany_id("SOCIAL456");
        causeCompanyStats.setCompany_name("Meta");
        causeCompanyStats.setBoycott_count(6700);

        assertEquals("HUMAN003", causeCompanyStats.getCause_id());
        assertEquals("Human Rights Violations", causeCompanyStats.getCause_desc());
        assertEquals("SOCIAL456", causeCompanyStats.getCompany_id());
        assertEquals("Meta", causeCompanyStats.getCompany_name());
        assertEquals(6700, causeCompanyStats.getBoycott_count());
    }

    @Test
    public void testSpecialCharactersInFields() {
        causeCompanyStats.setCause_id("CAUSE-123_$%");
        causeCompanyStats.setCause_desc("Environmental & Climate Action");
        causeCompanyStats.setCompany_id("COMP@456!&");
        causeCompanyStats.setCompany_name("Company & Co. Ltd.");

        assertEquals("CAUSE-123_$%", causeCompanyStats.getCause_id());
        assertEquals("Environmental & Climate Action", causeCompanyStats.getCause_desc());
        assertEquals("COMP@456!&", causeCompanyStats.getCompany_id());
        assertEquals("Company & Co. Ltd.", causeCompanyStats.getCompany_name());
    }

    @Test
    public void testLongFieldValues() {
        String longCauseId = "CAUSE_" + "X".repeat(100);
        String longCauseDesc = "Very detailed cause description explaining the environmental impact and social consequences of corporate actions. ".repeat(10);
        String longCompanyId = "COMPANY_" + "Y".repeat(100);
        String longCompanyName = "Very Long Corporation Name That Includes Multiple Business Units and Subsidiaries Worldwide";

        causeCompanyStats.setCause_id(longCauseId);
        causeCompanyStats.setCause_desc(longCauseDesc);
        causeCompanyStats.setCompany_id(longCompanyId);
        causeCompanyStats.setCompany_name(longCompanyName);

        assertEquals(longCauseId, causeCompanyStats.getCause_id());
        assertEquals(longCauseDesc, causeCompanyStats.getCause_desc());
        assertEquals(longCompanyId, causeCompanyStats.getCompany_id());
        assertEquals(longCompanyName, causeCompanyStats.getCompany_name());
    }

    @Test
    public void testMinimalValidStats() {
        causeCompanyStats.setCause_id("C");
        causeCompanyStats.setCause_desc("T");
        causeCompanyStats.setCompany_id("X");
        causeCompanyStats.setCompany_name("Y");
        causeCompanyStats.setBoycott_count(1);

        assertEquals("C", causeCompanyStats.getCause_id());
        assertEquals("T", causeCompanyStats.getCause_desc());
        assertEquals("X", causeCompanyStats.getCompany_id());
        assertEquals("Y", causeCompanyStats.getCompany_name());
        assertEquals(1, causeCompanyStats.getBoycott_count());
    }

    @Test
    public void testMultipleStatsUpdates() {
        // Test updating the same stats object multiple times
        causeCompanyStats.setCause_id("INITIAL_CAUSE");
        causeCompanyStats.setCompany_id("INITIAL_COMPANY");
        causeCompanyStats.setBoycott_count(100);

        assertEquals("INITIAL_CAUSE", causeCompanyStats.getCause_id());
        assertEquals("INITIAL_COMPANY", causeCompanyStats.getCompany_id());
        assertEquals(100, causeCompanyStats.getBoycott_count());

        // Update values
        causeCompanyStats.setCause_id("UPDATED_CAUSE");
        causeCompanyStats.setCompany_id("UPDATED_COMPANY");
        causeCompanyStats.setBoycott_count(200);

        assertEquals("UPDATED_CAUSE", causeCompanyStats.getCause_id());
        assertEquals("UPDATED_COMPANY", causeCompanyStats.getCompany_id());
        assertEquals(200, causeCompanyStats.getBoycott_count());
    }

    @Test
    public void testRealWorldCauseCompanyStats() {
        // Test realistic cause-company boycott scenarios
        Object[][] testData = {
            {"ENV001", "Climate Change", "OIL001", "ExxonMobil", 15000},
            {"LABOR001", "Worker Rights", "RETAIL001", "Amazon", 25000},
            {"PRIVACY001", "Data Privacy", "TECH001", "Facebook", 12000},
            {"ANIMAL001", "Animal Welfare", "FOOD001", "McDonald's", 8000},
            {"HEALTH001", "Public Health", "TOBACCO001", "Philip Morris", 30000}
        };

        for (Object[] data : testData) {
            causeCompanyStats.setCause_id((String) data[0]);
            causeCompanyStats.setCause_desc((String) data[1]);
            causeCompanyStats.setCompany_id((String) data[2]);
            causeCompanyStats.setCompany_name((String) data[3]);
            causeCompanyStats.setBoycott_count((Integer) data[4]);

            assertEquals(data[0], causeCompanyStats.getCause_id());
            assertEquals(data[1], causeCompanyStats.getCause_desc());
            assertEquals(data[2], causeCompanyStats.getCompany_id());
            assertEquals(data[3], causeCompanyStats.getCompany_name());
            assertEquals(data[4], causeCompanyStats.getBoycott_count());
        }
    }
}