package com.khorsandi.shayan.tweetservice.conf;


import com.khorsandi.shayan.tweetservice.saga.createtweet.CreateTweetSaga;
import com.khorsandi.shayan.tweetservice.saga.createtweet.CreateTweetSagaData;
import com.khorsandi.shayan.tweetservice.service.TweetCommandHandler;
import io.eventuate.tram.commands.common.ChannelMapping;
import io.eventuate.tram.commands.common.DefaultChannelMapping;
import io.eventuate.tram.commands.consumer.CommandDispatcher;
import io.eventuate.tram.sagas.orchestration.Saga;
import io.eventuate.tram.sagas.orchestration.SagaManager;
import io.eventuate.tram.sagas.orchestration.SagaManagerImpl;
import io.eventuate.tram.sagas.participant.SagaCommandDispatcher;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TweetConfiguration {


    @Bean
    public SagaManager<CreateTweetSagaData> createTweetSagaManager(Saga<CreateTweetSagaData> saga) {
        return new SagaManagerImpl<>(saga);
    }

    @Bean
    public CreateTweetSaga createTweetSaga() {
        return new CreateTweetSaga();
    }

    @Bean
    public TweetCommandHandler tweetCommandHandler() {
        return new TweetCommandHandler();
    }

    @Bean
    public SagaCommandDispatcher tweetCommandDispatcher(TweetCommandHandler target) {
        return new SagaCommandDispatcher("tweetCommandDispatcher", target.commandHandlerDefinitions());
    }
}
