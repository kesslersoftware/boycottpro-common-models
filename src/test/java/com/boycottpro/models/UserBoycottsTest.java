package com.boycottpro.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive test suite for UserBoycotts model class
 */
public class UserBoycottsTest {

    private UserBoycotts userBoycott;

    @BeforeEach
    public void setUp() {
        userBoycott = new UserBoycotts();
    }

    @Test
    public void testDefaultConstructor() {
        assertNull(userBoycott.getUser_id());
        assertNull(userBoycott.getCompany_id());
        assertNull(userBoycott.getCompany_name());
        assertNull(userBoycott.getCause_id());
        assertNull(userBoycott.getCause_desc());
        assertNull(userBoycott.getCompany_cause_id());
        assertNull(userBoycott.getPersonal_reason());
        assertNull(userBoycott.getTimestamp());
    }

    @Test
    public void testParameterizedConstructor() {
        UserBoycotts testBoycott = new UserBoycotts(
            "USER123",
            "COMP456",
            "Apple Inc.",
            "CAUSE789",
            "Labor Rights",
            "COMP456#CAUSE789",
            "Poor working conditions in factories",
            "2023-12-01T10:30:00Z"
        );

        assertEquals("USER123", testBoycott.getUser_id());
        assertEquals("COMP456", testBoycott.getCompany_id());
        assertEquals("Apple Inc.", testBoycott.getCompany_name());
        assertEquals("CAUSE789", testBoycott.getCause_id());
        assertEquals("Labor Rights", testBoycott.getCause_desc());
        assertEquals("COMP456#CAUSE789", testBoycott.getCompany_cause_id());
        assertEquals("Poor working conditions in factories", testBoycott.getPersonal_reason());
        assertEquals("2023-12-01T10:30:00Z", testBoycott.getTimestamp());
    }

    @Test
    public void testUserIdGetterSetter() {
        String userId = "USER456";
        userBoycott.setUser_id(userId);
        assertEquals(userId, userBoycott.getUser_id());
    }

    @Test
    public void testCompanyNameGetterSetter() {
        String companyName = "Microsoft Corporation";
        userBoycott.setCompany_name(companyName);
        assertEquals(companyName, userBoycott.getCompany_name());
    }

    @Test
    public void testCauseDescGetterSetter() {
        String causeDesc = "Environmental Protection";
        userBoycott.setCause_desc(causeDesc);
        assertEquals(causeDesc, userBoycott.getCause_desc());
    }

    @Test
    public void testPersonalReasonGetterSetter() {
        String personalReason = "Company supports deforestation projects";
        userBoycott.setPersonal_reason(personalReason);
        assertEquals(personalReason, userBoycott.getPersonal_reason());
    }

    @Test
    public void testTimestampGetterSetter() {
        String timestamp = "2023-12-01T15:30:45Z";
        userBoycott.setTimestamp(timestamp);
        assertEquals(timestamp, userBoycott.getTimestamp());
    }

    @Test
    public void testCompanyCauseIdAutoGeneration() {
        // Test that setting company_id and cause_id automatically generates company_cause_id
        userBoycott.setCompany_id("COMP123");
        userBoycott.setCause_id("CAUSE456");

        assertEquals("COMP123#CAUSE456", userBoycott.getCompany_cause_id());
    }

    @Test
    public void testCompanyCauseIdGenerationOrderCompanyFirst() {
        // Set company_id first, then cause_id
        userBoycott.setCompany_id("COMP789");
        assertNull(userBoycott.getCompany_cause_id()); // Should be null until cause_id is set

        userBoycott.setCause_id("CAUSE123");
        assertEquals("COMP789#CAUSE123", userBoycott.getCompany_cause_id());
    }

    @Test
    public void testCompanyCauseIdGenerationOrderCauseFirst() {
        // Set cause_id first, then company_id
        userBoycott.setCause_id("CAUSE456");
        assertNull(userBoycott.getCompany_cause_id()); // Should be null until company_id is set

        userBoycott.setCompany_id("COMP321");
        assertEquals("COMP321#CAUSE456", userBoycott.getCompany_cause_id());
    }

    @Test
    public void testCompanyCauseIdUpdateWhenCompanyChanges() {
        // Initial setup
        userBoycott.setCompany_id("COMP111");
        userBoycott.setCause_id("CAUSE222");
        assertEquals("COMP111#CAUSE222", userBoycott.getCompany_cause_id());

        // Change company_id
        userBoycott.setCompany_id("COMP333");
        assertEquals("COMP333#CAUSE222", userBoycott.getCompany_cause_id());
    }

    @Test
    public void testCompanyCauseIdUpdateWhenCauseChanges() {
        // Initial setup
        userBoycott.setCompany_id("COMP444");
        userBoycott.setCause_id("CAUSE555");
        assertEquals("COMP444#CAUSE555", userBoycott.getCompany_cause_id());

        // Change cause_id
        userBoycott.setCause_id("CAUSE666");
        assertEquals("COMP444#CAUSE666", userBoycott.getCompany_cause_id());
    }

    @Test
    public void testCompanyCauseIdWithNullCompany() {
        userBoycott.setCompany_id(null);
        userBoycott.setCause_id("CAUSE123");
        assertNull(userBoycott.getCompany_cause_id());
    }

    @Test
    public void testCompanyCauseIdWithNullCause() {
        userBoycott.setCompany_id("COMP123");
        userBoycott.setCause_id(null);
        assertNull(userBoycott.getCompany_cause_id());
    }

    @Test
    public void testCompanyCauseIdWithBothNull() {
        userBoycott.setCompany_id(null);
        userBoycott.setCause_id(null);
        assertNull(userBoycott.getCompany_cause_id());
    }

    @Test
    public void testCompanyCauseIdDirectSetter() {
        String companyCauseId = "MANUAL#COMPANY_CAUSE_ID";
        userBoycott.setCompany_cause_id(companyCauseId);
        assertEquals(companyCauseId, userBoycott.getCompany_cause_id());
    }

    @Test
    public void testCompleteBoycottScenario() {
        userBoycott.setUser_id("USER789");
        userBoycott.setCompany_id("COMP123");
        userBoycott.setCompany_name("Amazon");
        userBoycott.setCause_id("CAUSE456");
        userBoycott.setCause_desc("Worker Rights");
        userBoycott.setPersonal_reason("Poor treatment of warehouse workers");
        userBoycott.setTimestamp("2023-12-15T09:15:30Z");

        assertEquals("USER789", userBoycott.getUser_id());
        assertEquals("COMP123", userBoycott.getCompany_id());
        assertEquals("Amazon", userBoycott.getCompany_name());
        assertEquals("CAUSE456", userBoycott.getCause_id());
        assertEquals("Worker Rights", userBoycott.getCause_desc());
        assertEquals("COMP123#CAUSE456", userBoycott.getCompany_cause_id());
        assertEquals("Poor treatment of warehouse workers", userBoycott.getPersonal_reason());
        assertEquals("2023-12-15T09:15:30Z", userBoycott.getTimestamp());
    }

    @Test
    public void testNullStringFields() {
        userBoycott.setUser_id(null);
        userBoycott.setCompany_name(null);
        userBoycott.setCause_desc(null);
        userBoycott.setPersonal_reason(null);
        userBoycott.setTimestamp(null);

        assertNull(userBoycott.getUser_id());
        assertNull(userBoycott.getCompany_name());
        assertNull(userBoycott.getCause_desc());
        assertNull(userBoycott.getPersonal_reason());
        assertNull(userBoycott.getTimestamp());
    }

    @Test
    public void testEmptyStringFields() {
        userBoycott.setUser_id("");
        userBoycott.setCompany_name("");
        userBoycott.setCause_desc("");
        userBoycott.setPersonal_reason("");
        userBoycott.setTimestamp("");

        assertEquals("", userBoycott.getUser_id());
        assertEquals("", userBoycott.getCompany_name());
        assertEquals("", userBoycott.getCause_desc());
        assertEquals("", userBoycott.getPersonal_reason());
        assertEquals("", userBoycott.getTimestamp());
    }

    @Test
    public void testCompanyCauseIdWithEmptyStrings() {
        userBoycott.setCompany_id("");
        userBoycott.setCause_id("");
        assertEquals("#", userBoycott.getCompany_cause_id());
    }

    @Test
    public void testLongPersonalReason() {
        String longReason = "This is a very detailed personal reason for boycotting this company. ".repeat(50);
        userBoycott.setPersonal_reason(longReason);
        assertEquals(longReason, userBoycott.getPersonal_reason());
    }

    @Test
    public void testSpecialCharactersInIds() {
        userBoycott.setCompany_id("COMP-123_$%");
        userBoycott.setCause_id("CAUSE@456!&");
        assertEquals("COMP-123_$%#CAUSE@456!&", userBoycott.getCompany_cause_id());
    }

    @Test
    public void testTimestampFormats() {
        String[] timestamps = {
            "2023-12-01T10:30:00Z",
            "2023-12-01 10:30:00",
            "1672531200000", // Unix timestamp
            "Dec 1, 2023",
            "2023/12/01"
        };

        for (String timestamp : timestamps) {
            userBoycott.setTimestamp(timestamp);
            assertEquals(timestamp, userBoycott.getTimestamp());
        }
    }

    @Test
    public void testMultipleUpdatesCompanyCauseId() {
        // Test multiple updates to ensure company_cause_id stays correct
        userBoycott.setCompany_id("COMP1");
        userBoycott.setCause_id("CAUSE1");
        assertEquals("COMP1#CAUSE1", userBoycott.getCompany_cause_id());

        userBoycott.setCompany_id("COMP2");
        assertEquals("COMP2#CAUSE1", userBoycott.getCompany_cause_id());

        userBoycott.setCause_id("CAUSE2");
        assertEquals("COMP2#CAUSE2", userBoycott.getCompany_cause_id());

        userBoycott.setCompany_id("COMP3");
        assertEquals("COMP3#CAUSE2", userBoycott.getCompany_cause_id());
    }

    @Test
    public void testBoycottReasonCategories() {
        String[] reasons = {
            "Environmental concerns",
            "Labor rights violations",
            "Political disagreements",
            "Product quality issues",
            "Customer service problems",
            "Ethical concerns",
            "Data privacy violations",
            "Tax avoidance",
            "Anti-competitive practices"
        };

        for (String reason : reasons) {
            userBoycott.setPersonal_reason(reason);
            assertEquals(reason, userBoycott.getPersonal_reason());
        }
    }
}