package com.khorsandi.shayan.tweetservice.saga.createtweet;

public class CreateTweetSagaData {

    private int tweetId;
    private String username;

    public CreateTweetSagaData(int tweetId, String username) {
        this.tweetId = tweetId;
        this.username = username;
    }

    public CreateTweetSagaData(String username, int tweetId) {
        this.username = username;
        this.tweetId = tweetId;
    }

    public CreateTweetSagaData() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTweetId() {
        return tweetId;
    }

    public void setTweetId(int tweetId) {
        this.tweetId = tweetId;
    }
}
