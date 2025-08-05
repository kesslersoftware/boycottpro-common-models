package com.boycottpro.models;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class Users {
    private String user_id;
    private String email_addr;
    private String username;
    private String password_hash;
    private Long created_ts;
    private boolean paying_user;

    public Users() {}

    public Users(String user_id, String email_addr, String username,
                 String password_hash, Long created_ts, boolean paying_user) {
        this.user_id = user_id;
        this.email_addr = email_addr;
        this.username = username;
        this.password_hash = password_hash;
        this.created_ts = created_ts;
        this.paying_user = paying_user;
    }

    // Getters and setters
    @DynamoDbPartitionKey
    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getEmail_addr() {
        return email_addr;
    }

    public void setEmail_addr(String email_addr) {
        this.email_addr = email_addr;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public Long getCreated_ts() {
        return created_ts;
    }

    public void setCreated_ts(Long created_ts) {
        this.created_ts = created_ts;
    }

    public boolean isPaying_user() {
        return paying_user;
    }

    public void setPaying_user(boolean paying_user) {
        this.paying_user = paying_user;
    }
}
