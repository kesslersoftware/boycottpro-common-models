package com.boycottpro.models;

public class Users {
    private String user_id;
    private String email_addr;
    private String username;
    private String password_hash;
    private String timestamp;

    public Users() {}

    public Users(String user_id, String email_addr, String username,
                 String password_hash, String timestamp) {
        this.user_id = user_id;
        this.email_addr = email_addr;
        this.username = username;
        this.password_hash = password_hash;
        this.timestamp = timestamp;
    }
// Getters and setters

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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
