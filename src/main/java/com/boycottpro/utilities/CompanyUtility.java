package com.boycottpro.utilities;

import com.boycottpro.models.Companies;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;

public class CompanyUtility {

    public static Companies mapToCompany(Map<String, AttributeValue> item) {
        Companies company = new Companies();
        company.setCompany_id(item.get("company_id").s());
        company.setCompany_name(item.get("company_name").s());
        company.setDescription(item.getOrDefault("description", AttributeValue.fromS("")).s());
        company.setIndustry(item.getOrDefault("industry", AttributeValue.fromS("")).s());
        company.setCity(item.getOrDefault("city", AttributeValue.fromS("")).s());
        company.setState(item.getOrDefault("state", AttributeValue.fromS("")).s());
        company.setZip(item.getOrDefault("zip", AttributeValue.fromS("")).s());
        company.setEmployees(Integer.parseInt(item.getOrDefault("employees", AttributeValue.fromN("0")).n()));
        company.setRevenue(Long.parseLong(item.getOrDefault("revenue", AttributeValue.fromN("0")).n()));
        company.setValuation(Long.parseLong(item.getOrDefault("valuation", AttributeValue.fromN("0")).n()));
        company.setProfits(Long.parseLong(item.getOrDefault("profits", AttributeValue.fromN("0")).n()));
        company.setStock_symbol(item.getOrDefault("stock_symbol", AttributeValue.fromS("")).s());
        company.setCeo(item.getOrDefault("ceo", AttributeValue.fromS("")).s());
        company.setBoycott_count(Integer.parseInt(item.getOrDefault("boycott_count", AttributeValue.fromN("0")).n()));
        return company;
    }
}
