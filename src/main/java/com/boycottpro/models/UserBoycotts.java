package com.boycottpro.models;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

@DynamoDbBean
public class UserBoycotts {

    private String user_id;
    private String company_id;
    private String company_name;
    private String cause_id;
    private String cause_desc;
    private String company_cause_id;
    private String personal_reason;
    private String timestamp;

    @DynamoDbPartitionKey
    public String getUser_id() { return user_id; }
    public void setUser_id(String user_id) { this.user_id = user_id; }

    @DynamoDbSortKey
    public String getCompany_cause_id() { return company_cause_id; }
    public void setCompany_cause_id(String company_cause_id) { this.company_cause_id = company_cause_id; }

    public String getCompany_id() { return company_id; }
    public void setCompany_id(String company_id) {
        this.company_id = company_id;
        updateCompanyCauseId();
    }

    public String getCause_id() { return cause_id; }
    public void setCause_id(String cause_id) {
        this.cause_id = cause_id;
        updateCompanyCauseId();
    }

    public String getCompany_name() { return company_name; }
    public void setCompany_name(String company_name) { this.company_name = company_name; }

    public String getCause_desc() { return cause_desc; }
    public void setCause_desc(String cause_desc) { this.cause_desc = cause_desc; }

    public String getPersonal_reason() { return personal_reason; }
    public void setPersonal_reason(String personal_reason) { this.personal_reason = personal_reason; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    private void updateCompanyCauseId() {
        if (this.company_id != null && this.cause_id != null) {
            this.company_cause_id = this.company_id + "#" + this.cause_id;
        }
    }
}
