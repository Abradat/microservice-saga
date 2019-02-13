package com.khorsandi.shayan.tweetservice.controller;

import com.khorsandi.shayan.tweetservice.domain.Tweet;
import com.khorsandi.shayan.tweetservice.repository.TweetRepository;
import com.khorsandi.shayan.tweetservice.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/tweets")
public class TweetController {

    private TweetRepository tweetRepository;

    private TweetService tweetService;

    @Autowired
    public TweetController(TweetRepository tweetRepository, TweetService tweetService) {
        this.tweetRepository = tweetRepository;
        this.tweetService = tweetService;
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createTweet(@RequestBody Tweet tweet, UriComponentsBuilder ucBuilder)
    {
        Tweet resTweet = tweetService.createTweet(tweet);
        return new ResponseEntity<Void>(HttpStatus.CREATED);

    }

    @RequestMapping(path = "/{tweetId}", method = RequestMethod.GET)
    public ResponseEntity<Tweet> getTweet(@PathVariable("tweetId") int tweetId) {
        Tweet tweet = verifyTweetId(tweetId);
        if(tweet == null) {
            return new ResponseEntity<Tweet>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Tweet>(tweet, HttpStatus.OK);
    }


    private Tweet verifyTweetId(int tweetId) throws NoSuchElementException {
        return tweetRepository.findById(tweetId).orElseThrow(() -> new NoSuchElementException("Tour does not exist " + tweetId));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return400(NoSuchElementException ex) {
        return ex.getMessage();

    }
}
