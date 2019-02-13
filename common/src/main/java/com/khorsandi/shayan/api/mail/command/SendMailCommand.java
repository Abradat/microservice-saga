package com.khorsandi.shayan.api.mail.command;

import io.eventuate.tram.commands.common.Command;

public class SendMailCommand implements Command{

    private String username;

    public SendMailCommand(String username) {
        this.username = username;
    }

    public SendMailCommand() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
