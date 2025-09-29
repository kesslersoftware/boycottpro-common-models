package com.boycottpro.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive test suite for Companies model class
 */
public class CompaniesTest {

    private Companies company;

    @BeforeEach
    public void setUp() {
        company = new Companies();
    }

    @Test
    public void testDefaultConstructor() {
        assertNull(company.getCompany_id());
        assertNull(company.getCompany_name());
        assertNull(company.getDescription());
        assertNull(company.getIndustry());
        assertNull(company.getCity());
        assertNull(company.getState());
        assertNull(company.getZip());
        assertEquals(0, company.getEmployees());
        assertEquals(0L, company.getRevenue());
        assertEquals(0L, company.getValuation());
        assertEquals(0L, company.getProfits());
        assertNull(company.getStock_symbol());
        assertNull(company.getCeo());
        assertEquals(0, company.getBoycott_count());
    }

    @Test
    public void testParameterizedConstructor() {
        Companies testCompany = new Companies(
            "COMP123",
            "Apple Inc.",
            "Technology company",
            "Technology",
            "Cupertino",
            "CA",
            "95014",
            154000,
            394328000000L,
            3000000000000L,
            99803000000L,
            "AAPL",
            "Tim Cook",
            1250
        );

        assertEquals("COMP123", testCompany.getCompany_id());
        assertEquals("Apple Inc.", testCompany.getCompany_name());
        assertEquals("Technology company", testCompany.getDescription());
        assertEquals("Technology", testCompany.getIndustry());
        assertEquals("Cupertino", testCompany.getCity());
        assertEquals("CA", testCompany.getState());
        assertEquals("95014", testCompany.getZip());
        assertEquals(154000, testCompany.getEmployees());
        assertEquals(394328000000L, testCompany.getRevenue());
        assertEquals(3000000000000L, testCompany.getValuation());
        assertEquals(99803000000L, testCompany.getProfits());
        assertEquals("AAPL", testCompany.getStock_symbol());
        assertEquals("Tim Cook", testCompany.getCeo());
        assertEquals(1250, testCompany.getBoycott_count());
    }

    @Test
    public void testCompanyIdGetterSetter() {
        String companyId = "COMP456";
        company.setCompany_id(companyId);
        assertEquals(companyId, company.getCompany_id());
    }

    @Test
    public void testCompanyNameGetterSetter() {
        String companyName = "Microsoft Corporation";
        company.setCompany_name(companyName);
        assertEquals(companyName, company.getCompany_name());
    }

    @Test
    public void testDescriptionGetterSetter() {
        String description = "Software and cloud services company";
        company.setDescription(description);
        assertEquals(description, company.getDescription());
    }

    @Test
    public void testIndustryGetterSetter() {
        String industry = "Software";
        company.setIndustry(industry);
        assertEquals(industry, company.getIndustry());
    }

    @Test
    public void testLocationGetterSetters() {
        String city = "Seattle";
        String state = "WA";
        String zip = "98052";

        company.setCity(city);
        company.setState(state);
        company.setZip(zip);

        assertEquals(city, company.getCity());
        assertEquals(state, company.getState());
        assertEquals(zip, company.getZip());
    }

    @Test
    public void testFinancialDataGetterSetters() {
        int employees = 221000;
        long revenue = 211915000000L;
        long valuation = 2800000000000L;
        long profits = 72361000000L;

        company.setEmployees(employees);
        company.setRevenue(revenue);
        company.setValuation(valuation);
        company.setProfits(profits);

        assertEquals(employees, company.getEmployees());
        assertEquals(revenue, company.getRevenue());
        assertEquals(valuation, company.getValuation());
        assertEquals(profits, company.getProfits());
    }

    @Test
    public void testStockSymbolGetterSetter() {
        String stockSymbol = "MSFT";
        company.setStock_symbol(stockSymbol);
        assertEquals(stockSymbol, company.getStock_symbol());
    }

    @Test
    public void testCeoGetterSetter() {
        String ceo = "Satya Nadella";
        company.setCeo(ceo);
        assertEquals(ceo, company.getCeo());
    }

    @Test
    public void testBoycottCountGetterSetter() {
        int boycottCount = 500;
        company.setBoycott_count(boycottCount);
        assertEquals(boycottCount, company.getBoycott_count());
    }

    @Test
    public void testNullStringFields() {
        company.setCompany_id(null);
        company.setCompany_name(null);
        company.setDescription(null);
        company.setIndustry(null);
        company.setCity(null);
        company.setState(null);
        company.setZip(null);
        company.setStock_symbol(null);
        company.setCeo(null);

        assertNull(company.getCompany_id());
        assertNull(company.getCompany_name());
        assertNull(company.getDescription());
        assertNull(company.getIndustry());
        assertNull(company.getCity());
        assertNull(company.getState());
        assertNull(company.getZip());
        assertNull(company.getStock_symbol());
        assertNull(company.getCeo());
    }

    @Test
    public void testNegativeFinancialValues() {
        company.setEmployees(-1);
        company.setRevenue(-1000L);
        company.setValuation(-5000L);
        company.setProfits(-2000L);
        company.setBoycott_count(-10);

        assertEquals(-1, company.getEmployees());
        assertEquals(-1000L, company.getRevenue());
        assertEquals(-5000L, company.getValuation());
        assertEquals(-2000L, company.getProfits());
        assertEquals(-10, company.getBoycott_count());
    }

    @Test
    public void testZeroFinancialValues() {
        company.setEmployees(0);
        company.setRevenue(0L);
        company.setValuation(0L);
        company.setProfits(0L);
        company.setBoycott_count(0);

        assertEquals(0, company.getEmployees());
        assertEquals(0L, company.getRevenue());
        assertEquals(0L, company.getValuation());
        assertEquals(0L, company.getProfits());
        assertEquals(0, company.getBoycott_count());
    }

    @Test
    public void testEmptyStringFields() {
        company.setCompany_id("");
        company.setCompany_name("");
        company.setDescription("");
        company.setIndustry("");
        company.setCity("");
        company.setState("");
        company.setZip("");
        company.setStock_symbol("");
        company.setCeo("");

        assertEquals("", company.getCompany_id());
        assertEquals("", company.getCompany_name());
        assertEquals("", company.getDescription());
        assertEquals("", company.getIndustry());
        assertEquals("", company.getCity());
        assertEquals("", company.getState());
        assertEquals("", company.getZip());
        assertEquals("", company.getStock_symbol());
        assertEquals("", company.getCeo());
    }

    @Test
    public void testMaximumFinancialValues() {
        company.setEmployees(Integer.MAX_VALUE);
        company.setRevenue(Long.MAX_VALUE);
        company.setValuation(Long.MAX_VALUE);
        company.setProfits(Long.MAX_VALUE);
        company.setBoycott_count(Integer.MAX_VALUE);

        assertEquals(Integer.MAX_VALUE, company.getEmployees());
        assertEquals(Long.MAX_VALUE, company.getRevenue());
        assertEquals(Long.MAX_VALUE, company.getValuation());
        assertEquals(Long.MAX_VALUE, company.getProfits());
        assertEquals(Integer.MAX_VALUE, company.getBoycott_count());
    }
}