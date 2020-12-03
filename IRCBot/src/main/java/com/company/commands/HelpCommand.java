package com.company.commands;

import com.company.Bot;
import com.ircclouds.irc.api.domain.messages.ChannelPrivMsg;

/**
 * Basic command displaying all available commands.
 */
public class HelpCommand extends Command {

    public HelpCommand() {
        super("help");
    }

    @Override
    public void execute(ChannelPrivMsg message) {
        Bot.ircApi.message(message.getChannelName(), "Available commands; help, ping, weather, dice, uptime");
    }
}
