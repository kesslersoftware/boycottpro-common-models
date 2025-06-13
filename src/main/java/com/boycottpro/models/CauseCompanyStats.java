package com.boycottpro.models;

public class CauseCompanyStats {
    private String cause_id;
    private String cause_desc;
    private String company_id;
    private String company_name;
    private int boycott_count;

    public CauseCompanyStats() {}

    public CauseCompanyStats(String cause_id, String cause_desc, String company_id, String company_name,
                             int boycott_count) {
        this.cause_id = cause_id;
        this.cause_desc = cause_desc;
        this.company_id = company_id;
        this.company_name = company_name;
        this.boycott_count = boycott_count;
    }

    // Getters and setters

    public String getCause_id() {
        return cause_id;
    }

    public void setCause_id(String cause_id) {
        this.cause_id = cause_id;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public int getBoycott_count() {
        return boycott_count;
    }

    public void setBoycott_count(int boycott_count) {
        this.boycott_count = boycott_count;
    }

    public String getCause_desc() {
        return cause_desc;
    }

    public void setCause_desc(String cause_desc) {
        this.cause_desc = cause_desc;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }
}

