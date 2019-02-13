package com.khorsandi.shayan.tweetservice.repository;

import com.khorsandi.shayan.tweetservice.domain.Tweet;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends PagingAndSortingRepository<Tweet, Integer> {
}
