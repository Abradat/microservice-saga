package com.khorsandi.shayan.mailservice.web;

public class ConfirmMailResponse {

    private int userId;

    public ConfirmMailResponse(int userId) {
        this.userId = userId;
    }

    public ConfirmMailResponse() {
    }

    public int getUserId() {
        return userId;
    }

}
