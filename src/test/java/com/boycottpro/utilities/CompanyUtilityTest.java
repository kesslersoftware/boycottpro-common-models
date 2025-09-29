package com.boycottpro.utilities;

import com.boycottpro.models.Companies;
import com.boycottpro.models.CompanySubset;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Comprehensive test suite for CompanyUtility class
 */
public class CompanyUtilityTest {

    private Map<String, AttributeValue> fullCompanyItem;
    private Map<String, AttributeValue> minimalCompanyItem;
    private Map<String, AttributeValue> subsetCompanyItem;

    @BeforeEach
    public void setUp() {
        // Full company item with all fields
        fullCompanyItem = new HashMap<>();
        fullCompanyItem.put("company_id", AttributeValue.fromS("COMP123"));
        fullCompanyItem.put("company_name", AttributeValue.fromS("Apple Inc."));
        fullCompanyItem.put("description", AttributeValue.fromS("Technology company"));
        fullCompanyItem.put("industry", AttributeValue.fromS("Technology"));
        fullCompanyItem.put("city", AttributeValue.fromS("Cupertino"));
        fullCompanyItem.put("state", AttributeValue.fromS("CA"));
        fullCompanyItem.put("zip", AttributeValue.fromS("95014"));
        fullCompanyItem.put("employees", AttributeValue.fromN("154000"));
        fullCompanyItem.put("revenue", AttributeValue.fromN("394328000000"));
        fullCompanyItem.put("valuation", AttributeValue.fromN("3000000000000"));
        fullCompanyItem.put("profits", AttributeValue.fromN("99803000000"));
        fullCompanyItem.put("stock_symbol", AttributeValue.fromS("AAPL"));
        fullCompanyItem.put("ceo", AttributeValue.fromS("Tim Cook"));
        fullCompanyItem.put("boycott_count", AttributeValue.fromN("1250"));

        // Minimal company item with only required fields
        minimalCompanyItem = new HashMap<>();
        minimalCompanyItem.put("company_id", AttributeValue.fromS("COMP456"));
        minimalCompanyItem.put("company_name", AttributeValue.fromS("Test Company"));

        // Subset company item
        subsetCompanyItem = new HashMap<>();
        subsetCompanyItem.put("company_id", AttributeValue.fromS("COMP789"));
        subsetCompanyItem.put("company_name", AttributeValue.fromS("Subset Company"));
        subsetCompanyItem.put("boycott_count", AttributeValue.fromN("500"));
    }

    @Test
    public void testMapToCompanyWithFullData() {
        Companies company = CompanyUtility.mapToCompany(fullCompanyItem);

        assertEquals("COMP123", company.getCompany_id());
        assertEquals("Apple Inc.", company.getCompany_name());
        assertEquals("Technology company", company.getDescription());
        assertEquals("Technology", company.getIndustry());
        assertEquals("Cupertino", company.getCity());
        assertEquals("CA", company.getState());
        assertEquals("95014", company.getZip());
        assertEquals(154000, company.getEmployees());
        assertEquals(394328000000L, company.getRevenue());
        assertEquals(3000000000000L, company.getValuation());
        assertEquals(99803000000L, company.getProfits());
        assertEquals("AAPL", company.getStock_symbol());
        assertEquals("Tim Cook", company.getCeo());
        assertEquals(1250, company.getBoycott_count());
    }

    @Test
    public void testMapToCompanyWithMinimalData() {
        Companies company = CompanyUtility.mapToCompany(minimalCompanyItem);

        assertEquals("COMP456", company.getCompany_id());
        assertEquals("Test Company", company.getCompany_name());
        assertEquals("", company.getDescription());
        assertEquals("", company.getIndustry());
        assertEquals("", company.getCity());
        assertEquals("", company.getState());
        assertEquals("", company.getZip());
        assertEquals(0, company.getEmployees());
        assertEquals(0L, company.getRevenue());
        assertEquals(0L, company.getValuation());
        assertEquals(0L, company.getProfits());
        assertEquals("", company.getStock_symbol());
        assertEquals("", company.getCeo());
        assertEquals(0, company.getBoycott_count());
    }

    @Test
    public void testMapToCompanyWithPartialData() {
        Map<String, AttributeValue> partialItem = new HashMap<>();
        partialItem.put("company_id", AttributeValue.fromS("COMP789"));
        partialItem.put("company_name", AttributeValue.fromS("Partial Company"));
        partialItem.put("description", AttributeValue.fromS("Some description"));
        partialItem.put("employees", AttributeValue.fromN("5000"));
        partialItem.put("revenue", AttributeValue.fromN("1000000000"));

        Companies company = CompanyUtility.mapToCompany(partialItem);

        assertEquals("COMP789", company.getCompany_id());
        assertEquals("Partial Company", company.getCompany_name());
        assertEquals("Some description", company.getDescription());
        assertEquals("", company.getIndustry()); // Default value
        assertEquals("", company.getCity()); // Default value
        assertEquals("", company.getState()); // Default value
        assertEquals("", company.getZip()); // Default value
        assertEquals(5000, company.getEmployees());
        assertEquals(1000000000L, company.getRevenue());
        assertEquals(0L, company.getValuation()); // Default value
        assertEquals(0L, company.getProfits()); // Default value
        assertEquals("", company.getStock_symbol()); // Default value
        assertEquals("", company.getCeo()); // Default value
        assertEquals(0, company.getBoycott_count()); // Default value
    }

    @Test
    public void testMapToCompanyWithZeroValues() {
        Map<String, AttributeValue> zeroItem = new HashMap<>();
        zeroItem.put("company_id", AttributeValue.fromS("ZERO123"));
        zeroItem.put("company_name", AttributeValue.fromS("Zero Company"));
        zeroItem.put("employees", AttributeValue.fromN("0"));
        zeroItem.put("revenue", AttributeValue.fromN("0"));
        zeroItem.put("valuation", AttributeValue.fromN("0"));
        zeroItem.put("profits", AttributeValue.fromN("0"));
        zeroItem.put("boycott_count", AttributeValue.fromN("0"));

        Companies company = CompanyUtility.mapToCompany(zeroItem);

        assertEquals("ZERO123", company.getCompany_id());
        assertEquals("Zero Company", company.getCompany_name());
        assertEquals(0, company.getEmployees());
        assertEquals(0L, company.getRevenue());
        assertEquals(0L, company.getValuation());
        assertEquals(0L, company.getProfits());
        assertEquals(0, company.getBoycott_count());
    }

    @Test
    public void testMapToCompanyWithLargeNumbers() {
        Map<String, AttributeValue> largeItem = new HashMap<>();
        largeItem.put("company_id", AttributeValue.fromS("LARGE123"));
        largeItem.put("company_name", AttributeValue.fromS("Large Company"));
        largeItem.put("employees", AttributeValue.fromN("2000000"));
        largeItem.put("revenue", AttributeValue.fromN("999999999999"));
        largeItem.put("valuation", AttributeValue.fromN("5000000000000"));
        largeItem.put("profits", AttributeValue.fromN("200000000000"));
        largeItem.put("boycott_count", AttributeValue.fromN("50000"));

        Companies company = CompanyUtility.mapToCompany(largeItem);

        assertEquals("LARGE123", company.getCompany_id());
        assertEquals("Large Company", company.getCompany_name());
        assertEquals(2000000, company.getEmployees());
        assertEquals(999999999999L, company.getRevenue());
        assertEquals(5000000000000L, company.getValuation());
        assertEquals(200000000000L, company.getProfits());
        assertEquals(50000, company.getBoycott_count());
    }

    @Test
    public void testMapToCompanyWithEmptyStrings() {
        Map<String, AttributeValue> emptyStringItem = new HashMap<>();
        emptyStringItem.put("company_id", AttributeValue.fromS("EMPTY123"));
        emptyStringItem.put("company_name", AttributeValue.fromS(""));
        emptyStringItem.put("description", AttributeValue.fromS(""));
        emptyStringItem.put("industry", AttributeValue.fromS(""));

        Companies company = CompanyUtility.mapToCompany(emptyStringItem);

        assertEquals("EMPTY123", company.getCompany_id());
        assertEquals("", company.getCompany_name());
        assertEquals("", company.getDescription());
        assertEquals("", company.getIndustry());
    }

    @Test
    public void testMapToSubsetWithFullData() {
        CompanySubset subset = CompanyUtility.mapToSubset(subsetCompanyItem);

        assertEquals("COMP789", subset.getCompany_id());
        assertEquals("Subset Company", subset.getCompany_name());
        assertEquals(500, subset.getBoycott_count());
        assertEquals(0, subset.getRank()); // Rank is not set in the mapping
    }

    @Test
    public void testMapToSubsetWithMinimalData() {
        Map<String, AttributeValue> minimalSubsetItem = new HashMap<>();
        minimalSubsetItem.put("company_id", AttributeValue.fromS("MIN123"));
        minimalSubsetItem.put("company_name", AttributeValue.fromS("Minimal Company"));

        CompanySubset subset = CompanyUtility.mapToSubset(minimalSubsetItem);

        assertEquals("MIN123", subset.getCompany_id());
        assertEquals("Minimal Company", subset.getCompany_name());
        assertEquals(0, subset.getBoycott_count()); // Default value
        assertEquals(0, subset.getRank()); // Default value
    }

    @Test
    public void testMapToSubsetWithZeroBoycottCount() {
        Map<String, AttributeValue> zeroSubsetItem = new HashMap<>();
        zeroSubsetItem.put("company_id", AttributeValue.fromS("ZERO456"));
        zeroSubsetItem.put("company_name", AttributeValue.fromS("Zero Boycott Company"));
        zeroSubsetItem.put("boycott_count", AttributeValue.fromN("0"));

        CompanySubset subset = CompanyUtility.mapToSubset(zeroSubsetItem);

        assertEquals("ZERO456", subset.getCompany_id());
        assertEquals("Zero Boycott Company", subset.getCompany_name());
        assertEquals(0, subset.getBoycott_count());
    }

    @Test
    public void testMapToSubsetWithHighBoycottCount() {
        Map<String, AttributeValue> highSubsetItem = new HashMap<>();
        highSubsetItem.put("company_id", AttributeValue.fromS("HIGH789"));
        highSubsetItem.put("company_name", AttributeValue.fromS("High Boycott Company"));
        highSubsetItem.put("boycott_count", AttributeValue.fromN("100000"));

        CompanySubset subset = CompanyUtility.mapToSubset(highSubsetItem);

        assertEquals("HIGH789", subset.getCompany_id());
        assertEquals("High Boycott Company", subset.getCompany_name());
        assertEquals(100000, subset.getBoycott_count());
    }

    @Test
    public void testMapToCompanyWithSpecialCharacters() {
        Map<String, AttributeValue> specialItem = new HashMap<>();
        specialItem.put("company_id", AttributeValue.fromS("COMP-123_$%"));
        specialItem.put("company_name", AttributeValue.fromS("AT&T Inc."));
        specialItem.put("description", AttributeValue.fromS("Telecommunications & Technology"));
        specialItem.put("ceo", AttributeValue.fromS("John D. Smith Jr."));
        specialItem.put("stock_symbol", AttributeValue.fromS("T"));

        Companies company = CompanyUtility.mapToCompany(specialItem);

        assertEquals("COMP-123_$%", company.getCompany_id());
        assertEquals("AT&T Inc.", company.getCompany_name());
        assertEquals("Telecommunications & Technology", company.getDescription());
        assertEquals("John D. Smith Jr.", company.getCeo());
        assertEquals("T", company.getStock_symbol());
    }

    @Test
    public void testMapToCompanyWithInternationalData() {
        Map<String, AttributeValue> intlItem = new HashMap<>();
        intlItem.put("company_id", AttributeValue.fromS("INTL001"));
        intlItem.put("company_name", AttributeValue.fromS("Nestlé S.A."));
        intlItem.put("city", AttributeValue.fromS("Vevey"));
        intlItem.put("state", AttributeValue.fromS("Vaud"));
        intlItem.put("zip", AttributeValue.fromS("1800"));
        intlItem.put("ceo", AttributeValue.fromS("Ulf Mark Schneider"));

        Companies company = CompanyUtility.mapToCompany(intlItem);

        assertEquals("INTL001", company.getCompany_id());
        assertEquals("Nestlé S.A.", company.getCompany_name());
        assertEquals("Vevey", company.getCity());
        assertEquals("Vaud", company.getState());
        assertEquals("1800", company.getZip());
        assertEquals("Ulf Mark Schneider", company.getCeo());
    }

    @Test
    public void testMapToCompanyWithNegativeValues() {
        Map<String, AttributeValue> negativeItem = new HashMap<>();
        negativeItem.put("company_id", AttributeValue.fromS("NEG001"));
        negativeItem.put("company_name", AttributeValue.fromS("Negative Company"));
        negativeItem.put("profits", AttributeValue.fromN("-1000000000")); // Negative profits (losses)
        negativeItem.put("boycott_count", AttributeValue.fromN("-1")); // Edge case

        Companies company = CompanyUtility.mapToCompany(negativeItem);

        assertEquals("NEG001", company.getCompany_id());
        assertEquals("Negative Company", company.getCompany_name());
        assertEquals(-1000000000L, company.getProfits());
        assertEquals(-1, company.getBoycott_count());
    }

    @Test
    public void testBothMappingMethods() {
        // Test that both mapping methods work correctly with the same data
        Map<String, AttributeValue> testItem = new HashMap<>();
        testItem.put("company_id", AttributeValue.fromS("TEST123"));
        testItem.put("company_name", AttributeValue.fromS("Test Corporation"));
        testItem.put("boycott_count", AttributeValue.fromN("5000"));
        testItem.put("description", AttributeValue.fromS("Test description"));
        testItem.put("employees", AttributeValue.fromN("10000"));

        Companies fullCompany = CompanyUtility.mapToCompany(testItem);
        CompanySubset subset = CompanyUtility.mapToSubset(testItem);

        // Both should have the same core fields
        assertEquals(fullCompany.getCompany_id(), subset.getCompany_id());
        assertEquals(fullCompany.getCompany_name(), subset.getCompany_name());
        assertEquals(fullCompany.getBoycott_count(), subset.getBoycott_count());

        // Full company should have additional fields
        assertEquals("Test description", fullCompany.getDescription());
        assertEquals(10000, fullCompany.getEmployees());

        // Subset should have default rank
        assertEquals(0, subset.getRank());
    }

    @Test
    public void testRealWorldCompanyScenarios() {
        // Test realistic company mapping scenarios
        Object[][] companyData = {
            {"TECH001", "Apple Inc.", "154000", "394328000000", "3000000000000", "1250"},
            {"RETAIL001", "Amazon", "1600000", "513983000000", "1500000000000", "2500"},
            {"AUTO001", "Tesla", "127855", "96773000000", "800000000000", "1800"},
            {"SOCIAL001", "Meta", "86482", "117929000000", "750000000000", "3200"},
            {"SEARCH001", "Alphabet", "174014", "307394000000", "1700000000000", "1500"}
        };

        for (Object[] data : companyData) {
            Map<String, AttributeValue> item = new HashMap<>();
            item.put("company_id", AttributeValue.fromS((String) data[0]));
            item.put("company_name", AttributeValue.fromS((String) data[1]));
            item.put("employees", AttributeValue.fromN((String) data[2]));
            item.put("revenue", AttributeValue.fromN((String) data[3]));
            item.put("valuation", AttributeValue.fromN((String) data[4]));
            item.put("boycott_count", AttributeValue.fromN((String) data[5]));

            Companies company = CompanyUtility.mapToCompany(item);

            assertEquals(data[0], company.getCompany_id());
            assertEquals(data[1], company.getCompany_name());
            assertEquals(Integer.parseInt((String) data[2]), company.getEmployees());
            assertEquals(Long.parseLong((String) data[3]), company.getRevenue());
            assertEquals(Long.parseLong((String) data[4]), company.getValuation());
            assertEquals(Integer.parseInt((String) data[5]), company.getBoycott_count());
        }
    }
}