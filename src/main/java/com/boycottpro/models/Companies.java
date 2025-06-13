package com.boycottpro.models;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.io.Serializable;

@DynamoDbBean
public class Companies implements Serializable {
    private String company_id;
    private String company_name;
    private String description;
    private String industry;
    private String city;
    private String state;
    private String zip;
    private int employees;
    private long revenue;
    private long valuation;
    private long profits;
    private String stock_symbol;
    private String ceo;
    private int boycott_count;

    public Companies() {
    }

    public Companies(String company_id, String company_name, String description, String industry,
                     String city, String state, String zip, int employees, long revenue, long valuation,
                     long profits, String stock_symbol, String ceo, int boycott_count) {
        this.company_id = company_id;
        this.company_name = company_name;
        this.description = description;
        this.industry = industry;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.employees = employees;
        this.revenue = revenue;
        this.valuation = valuation;
        this.profits = profits;
        this.stock_symbol = stock_symbol;
        this.ceo = ceo;
        this.boycott_count = boycott_count;
    }

    @DynamoDbPartitionKey
    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public int getEmployees() {
        return employees;
    }

    public void setEmployees(int employees) {
        this.employees = employees;
    }

    public long getRevenue() {
        return revenue;
    }

    public void setRevenue(long revenue) {
        this.revenue = revenue;
    }

    public long getValuation() {
        return valuation;
    }

    public void setValuation(long valuation) {
        this.valuation = valuation;
    }

    public long getProfits() {
        return profits;
    }

    public void setProfits(long profits) {
        this.profits = profits;
    }

    public String getStock_symbol() {
        return stock_symbol;
    }

    public void setStock_symbol(String stock_symbol) {
        this.stock_symbol = stock_symbol;
    }

    public String getCeo() {
        return ceo;
    }

    public void setCeo(String ceo) {
        this.ceo = ceo;
    }

    public int getBoycott_count() {
        return boycott_count;
    }

    public void setBoycott_count(int boycott_count) {
        this.boycott_count = boycott_count;
    }
}
