package com.company.commands;

import com.company.Bot;
import com.ircclouds.irc.api.domain.messages.ChannelPrivMsg;

/**
 * Basic command for testing chat permissions. Should reply with "pong" if everything is correct.
 */
public class PingCommand extends Command {

    public PingCommand() {
        super("ping");
    }

    @Override
    public void execute(ChannelPrivMsg message) {
        Bot.ircApi.message(message.getChannelName(), "Pong!");
    }
}
