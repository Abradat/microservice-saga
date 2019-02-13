package com.khorsandi.shayan.mailservice.conf;

import com.khorsandi.shayan.mailservice.service.MailCommandHandler;
import io.eventuate.tram.commands.consumer.CommandDispatcher;
import io.eventuate.tram.sagas.participant.SagaCommandDispatcher;
import io.eventuate.tram.sagas.participant.SagaLockManager;
import io.eventuate.tram.sagas.participant.SagaParticipantConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({SagaParticipantConfiguration.class})
public class MailConfiguration {

    @Bean
    public MailCommandHandler mailCommandHandler() {
        return new MailCommandHandler();
    }

    @Bean
    public CommandDispatcher mailCommandDispatcher(MailCommandHandler target, SagaLockManager sagaLockManager) {
        return new SagaCommandDispatcher("mailCommandDispatcher", target.commandHandlerDefinitions());
    }
}
