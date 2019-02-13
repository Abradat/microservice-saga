package com.khorsandi.shayan.api.tweet.command;

import io.eventuate.tram.commands.common.Command;

public class ApproveTweetCommand implements Command {

    private int tweetId;

    public ApproveTweetCommand(int tweetId) {
        this.tweetId = tweetId;
    }

    public ApproveTweetCommand() {
    }

    public int getTweetId() {
        return tweetId;
    }

    public void setTweetId(int tweetId) {
        this.tweetId = tweetId;
    }
}
