package com.boycottpro.models;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

@DynamoDbBean
public class UserCauses {

    private String user_id;
    private String cause_id;
    private String cause_desc;

    @DynamoDbPartitionKey
    public String getUser_id() { return user_id; }
    public void setUser_id(String user_id) { this.user_id = user_id; }

    @DynamoDbSortKey
    public String getCause_id() { return cause_id; }
    public void setCause_id(String cause_id) { this.cause_id = cause_id; }

    public String getCause_desc() { return cause_desc; }
    public void setCause_desc(String cause_desc) { this.cause_desc = cause_desc; }
}
