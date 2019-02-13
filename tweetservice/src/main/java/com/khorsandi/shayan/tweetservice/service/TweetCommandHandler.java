package com.khorsandi.shayan.tweetservice.service;

import com.khorsandi.shayan.api.tweet.command.RejectTweetCommand;
import com.khorsandi.shayan.api.tweet.command.ApproveTweetCommand;
import com.khorsandi.shayan.tweetservice.domain.Tweet;
import com.khorsandi.shayan.tweetservice.repository.TweetRepository;
import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import io.eventuate.tram.messaging.common.Message;

import java.util.NoSuchElementException;

import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withSuccess;

public class TweetCommandHandler {

    @Autowired
    private TweetRepository tweetRepository;

    public CommandHandlers commandHandlerDefinitions() {
        return SagaCommandHandlersBuilder
                .fromChannel("tweetService")
                .onMessage(ApproveTweetCommand.class, this::approve)
                .onMessage(RejectTweetCommand.class, this::reject)
                .build();
    }

    public Message approve(CommandMessage<ApproveTweetCommand> commandMessage) throws NoSuchElementException {
        int tweetId = commandMessage.getCommand().getTweetId();
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(() -> new NoSuchElementException("Tweet does not exist while approving"));
        tweet.noteTweetMailSent();
        System.out.printf("\n\n********* Approve Command Received ***********\n\n");
        return withSuccess();
    }

    public Message reject(CommandMessage<RejectTweetCommand> commandMessage) throws NoSuchElementException {
        int tweetId = commandMessage.getCommand().getTweetId();
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(() -> new NoSuchElementException("Tweet Does not exist while rejecting"));
        tweet.noteTweetMailSendFailed();
        System.out.printf("\n\n********* Reject Command Received ***********\n\n");
        return withSuccess();
    }
}

