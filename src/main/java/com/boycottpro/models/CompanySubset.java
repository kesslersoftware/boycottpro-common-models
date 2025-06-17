package com.boycottpro.models;

public class CompanySubset {

    private String company_id;
    private String company_name;
    private int boycott_count;

    public CompanySubset() {
    }

    public CompanySubset(String company_id, String company_name, int boycott_count) {
        this.company_id = company_id;
        this.company_name = company_name;
        this.boycott_count = boycott_count;
    }

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

    public int getBoycott_count() {
        return boycott_count;
    }

    public void setBoycott_count(int boycott_count) {
        this.boycott_count = boycott_count;
    }
}
