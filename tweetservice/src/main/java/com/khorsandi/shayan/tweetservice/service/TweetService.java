package com.khorsandi.shayan.tweetservice.service;

import com.khorsandi.shayan.tweetservice.domain.Tweet;
import com.khorsandi.shayan.tweetservice.repository.TweetRepository;
import com.khorsandi.shayan.tweetservice.saga.createtweet.CreateTweetSagaData;
import io.eventuate.tram.events.ResultWithEvents;
import io.eventuate.tram.sagas.orchestration.SagaManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TweetService {

    @Autowired
    private SagaManager<CreateTweetSagaData> createTweetSagaManager;

    @Autowired
    private TweetRepository tweetRepository;

    @Transactional
    public Tweet createTweet(Tweet tweet) {
        ResultWithEvents<Tweet> te = tweet.createTweet(tweet);
        Tweet resultTweet = te.result;
        tweetRepository.save(resultTweet);
        CreateTweetSagaData createTweetSagaData = new CreateTweetSagaData(resultTweet.getId(), tweet.getUsername());
        createTweetSagaManager.create(createTweetSagaData, Tweet.class, resultTweet.getId());
        return resultTweet;
    }
}
