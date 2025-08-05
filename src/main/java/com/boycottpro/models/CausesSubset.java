package com.boycottpro.models;

public class CausesSubset {

    private int rank;
    private String cause_id;
    private String cause_desc;
    private int follower_count;

    public CausesSubset() {
    }

    public CausesSubset(int rank, String cause_id, String cause_desc, int follower_count) {
        this.rank = rank;
        this.cause_id = cause_id;
        this.cause_desc = cause_desc;
        this.follower_count = follower_count;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getCause_id() {
        return cause_id;
    }

    public void setCause_id(String cause_id) {
        this.cause_id = cause_id;
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
