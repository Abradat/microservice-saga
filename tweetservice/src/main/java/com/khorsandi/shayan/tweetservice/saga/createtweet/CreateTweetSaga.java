package com.khorsandi.shayan.tweetservice.saga.createtweet;

import com.khorsandi.shayan.api.mail.command.SendMailCommand;
import com.khorsandi.shayan.api.tweet.command.ApproveTweetCommand;
import com.khorsandi.shayan.api.tweet.command.RejectTweetCommand;
import io.eventuate.tram.commands.consumer.CommandWithDestination;
import io.eventuate.tram.sagas.simpledsl.SimpleSaga;
import io.eventuate.tram.sagas.orchestration.SagaDefinition;

import static io.eventuate.tram.commands.consumer.CommandWithDestinationBuilder.send;


public class CreateTweetSaga implements SimpleSaga<CreateTweetSagaData>  {

    private SagaDefinition<CreateTweetSagaData> sagaDefinition = step()
            .withCompensation(this::reject).step()
            .invokeParticipant(this::sendMail).step()
            .invokeParticipant(this::approve).build();


    @Override
    public SagaDefinition<CreateTweetSagaData> getSagaDefinition() {
        return this.sagaDefinition;
    }

    public CommandWithDestination sendMail(CreateTweetSagaData createTweetSagaData) {
        String username = createTweetSagaData.getUsername();
        return send(new SendMailCommand(username)).to("mailService").build();

    }

    public CommandWithDestination approve(CreateTweetSagaData createTweetSagaData) {
        int tweetId = createTweetSagaData.getTweetId();
        return send(new ApproveTweetCommand(tweetId)).to("tweetService").build();
    }

    public CommandWithDestination reject(CreateTweetSagaData createTweetSagaData) {
        int tweetId = createTweetSagaData.getTweetId();
        return send(new RejectTweetCommand(tweetId)).to("tweetService").build();
    }
}
