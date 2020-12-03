package com.company.listeners;

import com.company.Bot;
import com.ircclouds.irc.api.domain.messages.ChanJoinMessage;
import com.ircclouds.irc.api.domain.messages.ChanPartMessage;
import com.ircclouds.irc.api.domain.messages.ChannelKick;
import com.ircclouds.irc.api.listeners.VariousMessageListenerAdapter;

public class GreeterListener extends VariousMessageListenerAdapter {

    @Override
    public void onChannelJoin(ChanJoinMessage aMsg) {
        Bot.ircApi.message(aMsg.getChannelName(), aMsg.getSource().getNick() + " has joined the channel.");
    }

    @Override
    public void onChannelKick(ChannelKick aMsg) {
        Bot.ircApi.message(aMsg.getChannelName(), aMsg.getSource().getNick() + " has been kicked from the channel.");
    }

    @Override
    public void onChannelPart(ChanPartMessage aMsg) {
        Bot.ircApi.message(aMsg.getChannelName(), aMsg.getSource().getNick() + " has left the channel.");
    }
}
