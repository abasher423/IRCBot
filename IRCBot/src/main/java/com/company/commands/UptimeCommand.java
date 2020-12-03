package com.company.commands;

import com.company.Bot;
import com.ircclouds.irc.api.domain.messages.ChannelPrivMsg;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UptimeCommand extends Command {

    private Date startupDate;

    public UptimeCommand() {
        super("uptime");

        startupDate = new Date();
    }

    @Override
    public void execute(ChannelPrivMsg message) {
        Date nowDate = new Date();

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        long diff = nowDate.getTime() - startupDate.getTime();

        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long diffDays = diff / (24 * 60 * 60 * 1000);

        Bot.ircApi.message(message.getChannelName(), String.format("It's been %s days, %s hours %s minutes and %s seconds since the last startup.",
                diffDays,
                diffHours,
                diffMinutes,
                diffSeconds));
    }
}
