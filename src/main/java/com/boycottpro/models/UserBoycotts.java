package com.boycottpro.models;

public class UserBoycotts {
    private String user_id;
    private String company_id;
    private String cause_id;
    private String personal_reason;
    private String timestamp;

    public UserBoycotts() {}

    public UserBoycotts(String user_id, String company_id, String cause_id,
                        String personal_reason, String timestamp) {
        this.user_id = user_id;
        this.company_id = company_id;
        this.cause_id = cause_id;
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

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getCause_id() {
        return cause_id;
    }

    public void setCause_id(String cause_id) {
        this.cause_id = cause_id;
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
}

