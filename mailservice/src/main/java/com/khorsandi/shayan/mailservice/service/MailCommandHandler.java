package com.khorsandi.shayan.mailservice.service;

import com.khorsandi.shayan.api.mail.command.SendMailCommand;
import com.khorsandi.shayan.api.mail.reply.MailSent;
import com.khorsandi.shayan.api.mail.reply.MailSentFailed;
import com.khorsandi.shayan.mailservice.domain.MailType;
import com.khorsandi.shayan.mailservice.domain.SentMail;
import com.khorsandi.shayan.mailservice.domain.UserMail;
import com.khorsandi.shayan.mailservice.repository.SentMailRepository;
import com.khorsandi.shayan.mailservice.repository.UserMailRepository;
import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withFailure;
import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withSuccess;

public class MailCommandHandler {

    @Autowired
    private UserMailRepository userMailRepository;
    @Autowired
    private SentMailRepository sentMailRepository;

    public CommandHandlers commandHandlerDefinitions() {
        return SagaCommandHandlersBuilder
                .fromChannel("mailService")
                .onMessage(SendMailCommand.class, this::sendMail)
                .build();
    }

    public Message sendMail(CommandMessage<SendMailCommand> commandMessage) {
        SendMailCommand sendMailCommand = commandMessage.getCommand();
        String username = sendMailCommand.getUsername();

        UserMail userMail = userMailRepository.findByUsername(username);
        if(userMail == null) {
            sentMailRepository.save(new SentMail(userMail, "Username is not found", MailType.APOLOGY));
            System.out.printf("\n\n********* Apology Mail Sent ***********\n\n");
            return withFailure(new MailSentFailed());
        }
        if(!userMail.isConfirmed()){
            sentMailRepository.save(new SentMail(userMail, "Mail is not confirmed", MailType.APOLOGY));
            System.out.printf("\n\n********* Apology Mail Sent ***********\n\n");
            return withFailure(new MailSentFailed());
        } else {
            sentMailRepository.save(new SentMail(userMail, "Mail is confirmed and ok", MailType.NORMAL));
            System.out.printf("\n\n********* Approved Mail Sent ***********\n\n");
            return withSuccess(new MailSent());
        }
    }
}
