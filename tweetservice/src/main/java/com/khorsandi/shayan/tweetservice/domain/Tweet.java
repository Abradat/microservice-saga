package com.khorsandi.shayan.tweetservice.domain;


import io.eventuate.tram.events.ResultWithEvents;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collections;

@Entity
public class Tweet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    @NotNull
    private String username;

    @Column(length = 200)
    @NotNull
    private String text;

    @Enumerated(EnumType.STRING)
    private TweetState tweetState;

    public Tweet(String username, String text) {
        this.username = username;
        this.text = text;
        this.tweetState = TweetState.PENDING;
    }


    public Tweet() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TweetState getTweetState() { return tweetState; }

    public void noteTweetMailSent() { this.tweetState = TweetState.APRROVED; }

    public void noteTweetMailSendFailed() { this.tweetState = TweetState.REJECTED; }

    public static ResultWithEvents<Tweet> createTweet(Tweet tweet) {
        return new ResultWithEvents<Tweet>(new Tweet(tweet.getUsername(), tweet.getText()), Collections.emptyList());
    }
}
