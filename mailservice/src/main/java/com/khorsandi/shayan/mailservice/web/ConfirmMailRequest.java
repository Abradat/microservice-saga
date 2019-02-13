package com.khorsandi.shayan.mailservice.web;

public class ConfirmMailRequest {
    private String username;

    public ConfirmMailRequest(String username) {
        this.username = username;
    }

    public ConfirmMailRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
