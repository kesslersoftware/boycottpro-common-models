package com.boycottpro.models;

public class ResponseMessage {

    private int status;
    private String message;
    private String devMsg;

    public ResponseMessage() {
    }

    public ResponseMessage(int status, String message, String devMsg) {
        this.status = status;
        this.message = message;
        this.devMsg = devMsg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDevMsg() {
        return devMsg;
    }

    public void setDevMsg(String devMsg) {
        this.devMsg = devMsg;
    }
}
