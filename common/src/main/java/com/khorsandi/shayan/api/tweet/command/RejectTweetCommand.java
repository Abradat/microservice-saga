package com.khorsandi.shayan.api.tweet.command;

import io.eventuate.tram.commands.common.Command;

public class RejectTweetCommand implements Command{
    private int tweetId;

    public RejectTweetCommand(int tweetId) {
        this.tweetId = tweetId;
    }

    public RejectTweetCommand() {
    }

    public int getTweetId() {
        return tweetId;
    }

    public void setTweetId(int tweetId) {
        this.tweetId = tweetId;
    }
}
