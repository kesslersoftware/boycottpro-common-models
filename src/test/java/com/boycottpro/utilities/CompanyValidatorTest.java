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
 * Test suite for CompanyValidator class
 */
@ExtendWith(MockitoExtension.class)
public class CompanyValidatorTest {

    @Mock
    private DynamoDbClient mockDynamoDbClient;

    private CompanyValidator companyValidator;
    private final String tableName = "test-companies-table";

    @BeforeEach
    public void setUp() {
        companyValidator = new CompanyValidator(mockDynamoDbClient, tableName);
    }

    @Test
    public void testConstructor() {
        CompanyValidator validator = new CompanyValidator(mockDynamoDbClient, "table");
        assertNotNull(validator);
    }

    @Test
    public void testValidateCompanyNameWithMatchingName() {
        // Setup
        String companyId = "COMP123";
        String expectedName = "Apple Inc.";

        GetItemResponse mockResponse = GetItemResponse.builder()
                .item(Map.of("company_name", AttributeValue.fromS(expectedName)))
                .build();

        when(mockDynamoDbClient.getItem(any(GetItemRequest.class))).thenReturn(mockResponse);

        // Execute
        boolean result = companyValidator.validateCompanyName(companyId, expectedName);

        // Verify
        assertTrue(result);
        verify(mockDynamoDbClient, times(1)).getItem(any(GetItemRequest.class));
    }

    @Test
    public void testValidateCompanyNameWithNonMatchingName() {
        // Setup
        String companyId = "COMP123";
        String storedName = "Apple Inc.";
        String inputName = "Microsoft Corporation";

        GetItemResponse mockResponse = GetItemResponse.builder()
                .item(Map.of("company_name", AttributeValue.fromS(storedName)))
                .build();

        when(mockDynamoDbClient.getItem(any(GetItemRequest.class))).thenReturn(mockResponse);

        // Execute
        boolean result = companyValidator.validateCompanyName(companyId, inputName);

        // Verify
        assertFalse(result);
        verify(mockDynamoDbClient, times(1)).getItem(any(GetItemRequest.class));
    }

    @Test
    public void testValidateCompanyNameWithNonExistentCompany() {
        // Setup
        String companyId = "NONEXISTENT";
        String companyName = "Some Company";

        GetItemResponse mockResponse = mock(GetItemResponse.class);
        when(mockResponse.hasItem()).thenReturn(false);
        when(mockDynamoDbClient.getItem(any(GetItemRequest.class))).thenReturn(mockResponse);

        // Execute
        boolean result = companyValidator.validateCompanyName(companyId, companyName);

        // Verify
        assertFalse(result);
        verify(mockDynamoDbClient, times(1)).getItem(any(GetItemRequest.class));
    }

    @Test
    public void testValidateCompanyNameWithDynamoDbException() {
        // Setup
        String companyId = "COMP123";
        String companyName = "Some Company";

        when(mockDynamoDbClient.getItem(any(GetItemRequest.class)))
                .thenThrow(DynamoDbException.builder().message("Database error").build());

        // Execute
        boolean result = companyValidator.validateCompanyName(companyId, companyName);

        // Verify
        assertFalse(result);
        verify(mockDynamoDbClient, times(1)).getItem(any(GetItemRequest.class));
    }
}