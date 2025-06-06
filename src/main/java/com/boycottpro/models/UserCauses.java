package com.boycottpro.models;

public class UserCauses {
    private String user_id;
    private String cause_id;
    private String timestamp;

    public UserCauses() {}

    public UserCauses(String user_id, String cause_id, String timestamp) {
        this.user_id = user_id;
        this.cause_id = cause_id;
        this.timestamp = timestamp;
    }
// Getters and setters

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCause_id() {
        return cause_id;
    }

    public void setCause_id(String cause_id) {
        this.cause_id = cause_id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}

