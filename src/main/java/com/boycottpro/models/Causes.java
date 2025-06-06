package com.boycottpro.models;

public class Causes {
    private String cause_id;
    private String name;
    private String description;
    private int follower_count;

    public Causes() {}

    public Causes(String cause_id, String name, String description, int follower_count) {
        this.cause_id = cause_id;
        this.name = name;
        this.description = description;
        this.follower_count = follower_count;
    }
// Getters and setters

    public String getCause_id() {
        return cause_id;
    }

    public void setCause_id(String cause_id) {
        this.cause_id = cause_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFollower_count() {
        return follower_count;
    }

    public void setFollower_count(int follower_count) {
        this.follower_count = follower_count;
    }
}
