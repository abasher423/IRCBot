package com.company.commands;

import com.company.Bot;
import com.ircclouds.irc.api.domain.messages.ChannelPrivMsg;

import java.util.Random;

/**
 * A basic command that generates a random dice number.
 */
public class DiceCommand extends Command {

    public DiceCommand() {
        super("dice");
    }

    @Override
    public void execute(ChannelPrivMsg message) {
        Bot.ircApi.message(message.getChannelName(), "Rolling the dice... " + (new Random().nextInt(6) + 1) + "!");
    }
}
