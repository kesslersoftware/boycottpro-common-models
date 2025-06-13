package com.boycottpro.models;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

@DynamoDbBean
public class Causes {
    private String cause_id;
    private String category;
    private String cause_desc;
    private int follower_count;

    public Causes() {}

    public Causes(String cause_id, String category, String cause_desc, int follower_count) {
        this.cause_id = cause_id;
        this.category = category;
        this.cause_desc = cause_desc;
        this.follower_count = follower_count;
    }
// Getters and setters

    @DynamoDbPartitionKey
    public String getCause_id() {
        return cause_id;
    }

    public void setCause_id(String cause_id) {
        this.cause_id = cause_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCause_desc() {
        return cause_desc;
    }

    public void setCause_desc(String cause_desc) {
        this.cause_desc = cause_desc;
    }

    public int getFollower_count() {
        return follower_count;
    }

    public void setFollower_count(int follower_count) {
        this.follower_count = follower_count;
    }
}
