package com.boycottpro.models;

public class UserBoycotts {
    private String user_id;
    private String company_cause_id;
    private String company_name;
    private String cause_desc;
    private String personal_reason;
    private String timestamp;

    public UserBoycotts() {}

    public UserBoycotts(String user_id, String company_cause_id, String company_name, String cause_desc,
                        String personal_reason, String timestamp) {
        this.user_id = user_id;
        this.company_cause_id = company_cause_id;
        this.company_name = company_name;
        this.cause_desc = cause_desc;
        this.personal_reason = personal_reason;
        this.timestamp = timestamp;
    }

    // Getters and setters

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCompany_cause_id() {
        return company_cause_id;
    }

    public void setCompany_cause_id(String company_cause_id) {
        this.company_cause_id = company_cause_id;
    }

    public String getPersonal_reason() {
        return personal_reason;
    }

    public void setPersonal_reason(String personal_reason) {
        this.personal_reason = personal_reason;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCause_desc() {
        return cause_desc;
    }

    public void setCause_desc(String cause_desc) {
        this.cause_desc = cause_desc;
    }
}

