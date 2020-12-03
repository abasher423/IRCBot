package com.company.commands;

import com.ircclouds.irc.api.domain.messages.ChannelPrivMsg;
import com.ircclouds.irc.api.listeners.VariousMessageListenerAdapter;

public abstract class Command extends VariousMessageListenerAdapter {

    public static final String PREFIX = ">>";

    private String command;

    public Command(String command) {
        this.command = command;
    }

    @Override
    public void onChannelMessage(ChannelPrivMsg aMsg) {
        if (aMsg.getText().startsWith(PREFIX + command)) execute(aMsg);
    }

    public void execute(ChannelPrivMsg message) {

    }

    public String getCommand() {
        return command;
    }
}
