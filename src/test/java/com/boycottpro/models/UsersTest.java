package com.boycottpro.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive test suite for Users model class
 */
public class UsersTest {

    private Users user;

    @BeforeEach
    public void setUp() {
        user = new Users();
    }

    @Test
    public void testDefaultConstructor() {
        assertNull(user.getUser_id());
        assertNull(user.getEmail_addr());
        assertNull(user.getUsername());
        assertNull(user.getPassword_hash());
        assertNull(user.getCreated_ts());
        assertFalse(user.isPaying_user());
    }

    @Test
    public void testParameterizedConstructor() {
        Long currentTimestamp = System.currentTimeMillis();
        Users testUser = new Users(
            "USER123",
            "john.doe@example.com",
            "johndoe",
            "$2a$10$abcd1234567890abcdef",
            currentTimestamp,
            true
        );

        assertEquals("USER123", testUser.getUser_id());
        assertEquals("john.doe@example.com", testUser.getEmail_addr());
        assertEquals("johndoe", testUser.getUsername());
        assertEquals("$2a$10$abcd1234567890abcdef", testUser.getPassword_hash());
        assertEquals(currentTimestamp, testUser.getCreated_ts());
        assertTrue(testUser.isPaying_user());
    }

    @Test
    public void testUserIdGetterSetter() {
        String userId = "USER456";
        user.setUser_id(userId);
        assertEquals(userId, user.getUser_id());
    }

    @Test
    public void testEmailAddrGetterSetter() {
        String email = "jane.smith@example.com";
        user.setEmail_addr(email);
        assertEquals(email, user.getEmail_addr());
    }

    @Test
    public void testUsernameGetterSetter() {
        String username = "janesmith";
        user.setUsername(username);
        assertEquals(username, user.getUsername());
    }

    @Test
    public void testPasswordHashGetterSetter() {
        String passwordHash = "$2a$10$xyz9876543210fedcba";
        user.setPassword_hash(passwordHash);
        assertEquals(passwordHash, user.getPassword_hash());
    }

    @Test
    public void testCreatedTsGetterSetter() {
        Long timestamp = 1672531200000L; // January 1, 2023
        user.setCreated_ts(timestamp);
        assertEquals(timestamp, user.getCreated_ts());
    }

    @Test
    public void testPayingUserGetterSetter() {
        user.setPaying_user(true);
        assertTrue(user.isPaying_user());

        user.setPaying_user(false);
        assertFalse(user.isPaying_user());
    }

    @Test
    public void testNullStringFields() {
        user.setUser_id(null);
        user.setEmail_addr(null);
        user.setUsername(null);
        user.setPassword_hash(null);

        assertNull(user.getUser_id());
        assertNull(user.getEmail_addr());
        assertNull(user.getUsername());
        assertNull(user.getPassword_hash());
    }

    @Test
    public void testEmptyStringFields() {
        user.setUser_id("");
        user.setEmail_addr("");
        user.setUsername("");
        user.setPassword_hash("");

        assertEquals("", user.getUser_id());
        assertEquals("", user.getEmail_addr());
        assertEquals("", user.getUsername());
        assertEquals("", user.getPassword_hash());
    }

    @Test
    public void testNullTimestamp() {
        user.setCreated_ts(null);
        assertNull(user.getCreated_ts());
    }

    @Test
    public void testZeroTimestamp() {
        user.setCreated_ts(0L);
        assertEquals(0L, user.getCreated_ts());
    }

    @Test
    public void testNegativeTimestamp() {
        user.setCreated_ts(-1L);
        assertEquals(-1L, user.getCreated_ts());
    }

    @Test
    public void testMaximumTimestamp() {
        user.setCreated_ts(Long.MAX_VALUE);
        assertEquals(Long.MAX_VALUE, user.getCreated_ts());
    }

    @Test
    public void testValidEmailFormats() {
        String[] validEmails = {
            "user@example.com",
            "test.email@domain.co.uk",
            "user+tag@example.org",
            "firstname.lastname@company.io",
            "123@numbers.com"
        };

        for (String email : validEmails) {
            user.setEmail_addr(email);
            assertEquals(email, user.getEmail_addr());
        }
    }

    @Test
    public void testComplexUsernames() {
        String[] usernames = {
            "user123",
            "test_user",
            "user-name",
            "CamelCaseUser",
            "lowercase",
            "UPPERCASE",
            "mix3d_C4s3"
        };

        for (String username : usernames) {
            user.setUsername(username);
            assertEquals(username, user.getUsername());
        }
    }

    @Test
    public void testHashedPasswordFormats() {
        String[] passwordHashes = {
            "$2a$10$abcd1234567890abcdef", // bcrypt
            "$2b$12$xyz9876543210fedcba987", // bcrypt variant
            "sha256:abcdef1234567890", // SHA256
            "md5:098f6bcd4621d373cade4e832627b4f6", // MD5
            "pbkdf2:10000:salt:hash" // PBKDF2
        };

        for (String hash : passwordHashes) {
            user.setPassword_hash(hash);
            assertEquals(hash, user.getPassword_hash());
        }
    }

    @Test
    public void testCurrentTimestamp() {
        Long beforeTimestamp = System.currentTimeMillis();
        user.setCreated_ts(System.currentTimeMillis());
        Long afterTimestamp = System.currentTimeMillis();

        assertNotNull(user.getCreated_ts());
        assertTrue(user.getCreated_ts() >= beforeTimestamp);
        assertTrue(user.getCreated_ts() <= afterTimestamp);
    }

    @Test
    public void testUserWithMinimalData() {
        user.setUser_id("U1");
        user.setEmail_addr("a@b.c");
        user.setUsername("u");
        user.setPassword_hash("x");
        user.setCreated_ts(1L);
        user.setPaying_user(false);

        assertEquals("U1", user.getUser_id());
        assertEquals("a@b.c", user.getEmail_addr());
        assertEquals("u", user.getUsername());
        assertEquals("x", user.getPassword_hash());
        assertEquals(1L, user.getCreated_ts());
        assertFalse(user.isPaying_user());
    }

    @Test
    public void testUserWithMaximalData() {
        String longUserId = "USER_" + "X".repeat(100);
        String longEmail = "very.long.email.address.that.might.be.used@very-long-domain-name-for-testing.example.com";
        String longUsername = "very_long_username_that_might_be_allowed_in_some_systems";
        String longPasswordHash = "$2a$12$" + "a".repeat(100);
        Long maxTimestamp = Long.MAX_VALUE;

        user.setUser_id(longUserId);
        user.setEmail_addr(longEmail);
        user.setUsername(longUsername);
        user.setPassword_hash(longPasswordHash);
        user.setCreated_ts(maxTimestamp);
        user.setPaying_user(true);

        assertEquals(longUserId, user.getUser_id());
        assertEquals(longEmail, user.getEmail_addr());
        assertEquals(longUsername, user.getUsername());
        assertEquals(longPasswordHash, user.getPassword_hash());
        assertEquals(maxTimestamp, user.getCreated_ts());
        assertTrue(user.isPaying_user());
    }

    @Test
    public void testFreeUserScenario() {
        user.setUser_id("FREE_USER_001");
        user.setEmail_addr("free.user@example.com");
        user.setUsername("freeuser");
        user.setPassword_hash("$2a$10$freeuserhash");
        user.setCreated_ts(System.currentTimeMillis());
        user.setPaying_user(false);

        assertEquals("FREE_USER_001", user.getUser_id());
        assertEquals("free.user@example.com", user.getEmail_addr());
        assertEquals("freeuser", user.getUsername());
        assertFalse(user.isPaying_user());
    }

    @Test
    public void testPremiumUserScenario() {
        user.setUser_id("PREMIUM_USER_001");
        user.setEmail_addr("premium.user@example.com");
        user.setUsername("premiumuser");
        user.setPassword_hash("$2a$12$premiumuserhash");
        user.setCreated_ts(System.currentTimeMillis());
        user.setPaying_user(true);

        assertEquals("PREMIUM_USER_001", user.getUser_id());
        assertEquals("premium.user@example.com", user.getEmail_addr());
        assertEquals("premiumuser", user.getUsername());
        assertTrue(user.isPaying_user());
    }
}