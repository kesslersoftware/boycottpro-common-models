package com.boycottpro.utilities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemResponse;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Test suite for CauseValidator class
 */
@ExtendWith(MockitoExtension.class)
public class CauseValidatorTest {

    @Mock
    private DynamoDbClient mockDynamoDbClient;

    private CauseValidator causeValidator;
    private final String tableName = "test-causes-table";

    @BeforeEach
    public void setUp() {
        causeValidator = new CauseValidator(mockDynamoDbClient, tableName);
    }

    @Test
    public void testConstructor() {
        CauseValidator validator = new CauseValidator(mockDynamoDbClient, "table");
        assertNotNull(validator);
    }

    @Test
    public void testValidateCauseDescriptionWithMatchingDescription() {
        // Setup
        String causeId = "CAUSE123";
        String expectedDesc = "Environmental protection";

        GetItemResponse mockResponse = GetItemResponse.builder()
                .item(Map.of("cause_desc", AttributeValue.fromS(expectedDesc)))
                .build();

        when(mockDynamoDbClient.getItem(any(GetItemRequest.class))).thenReturn(mockResponse);

        // Execute
        boolean result = causeValidator.validateCauseDescription(causeId, expectedDesc);

        // Verify
        assertTrue(result);
        verify(mockDynamoDbClient, times(1)).getItem(any(GetItemRequest.class));
    }

    @Test
    public void testValidateCauseDescriptionWithNonMatchingDescription() {
        // Setup
        String causeId = "CAUSE123";
        String storedDesc = "Environmental protection";
        String inputDesc = "Animal rights";

        GetItemResponse mockResponse = GetItemResponse.builder()
                .item(Map.of("cause_desc", AttributeValue.fromS(storedDesc)))
                .build();

        when(mockDynamoDbClient.getItem(any(GetItemRequest.class))).thenReturn(mockResponse);

        // Execute
        boolean result = causeValidator.validateCauseDescription(causeId, inputDesc);

        // Verify
        assertFalse(result);
        verify(mockDynamoDbClient, times(1)).getItem(any(GetItemRequest.class));
    }

    @Test
    public void testValidateCauseDescriptionWithNonExistentCause() {
        // Setup
        String causeId = "NONEXISTENT";
        String causeDesc = "Some description";

        GetItemResponse mockResponse = mock(GetItemResponse.class);
        when(mockResponse.hasItem()).thenReturn(false);
        when(mockDynamoDbClient.getItem(any(GetItemRequest.class))).thenReturn(mockResponse);

        // Execute
        boolean result = causeValidator.validateCauseDescription(causeId, causeDesc);

        // Verify
        assertFalse(result);
        verify(mockDynamoDbClient, times(1)).getItem(any(GetItemRequest.class));
    }

    @Test
    public void testValidateCauseDescriptionWithDynamoDbException() {
        // Setup
        String causeId = "CAUSE123";
        String causeDesc = "Some description";

        when(mockDynamoDbClient.getItem(any(GetItemRequest.class)))
                .thenThrow(DynamoDbException.builder().message("Database error").build());

        // Execute
        boolean result = causeValidator.validateCauseDescription(causeId, causeDesc);

        // Verify
        assertFalse(result);
        verify(mockDynamoDbClient, times(1)).getItem(any(GetItemRequest.class));
    }
}