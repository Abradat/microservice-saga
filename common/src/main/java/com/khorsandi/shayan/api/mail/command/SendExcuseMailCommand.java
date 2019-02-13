package com.khorsandi.shayan.api.mail.command;

import io.eventuate.tram.commands.common.Command;

public class SendExcuseMailCommand implements Command {

    private String username;

    public SendExcuseMailCommand(String username) {
        this.username = username;
    }

    public SendExcuseMailCommand() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
