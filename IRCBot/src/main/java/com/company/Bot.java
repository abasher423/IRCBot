package com.company;

import com.company.commands.*;
import com.company.listeners.GreeterListener;
import com.ircclouds.irc.api.Callback;
import com.ircclouds.irc.api.IRCApi;
import com.ircclouds.irc.api.IRCApiImpl;
import com.ircclouds.irc.api.IServerParameters;
import com.ircclouds.irc.api.domain.IRCServer;
import com.ircclouds.irc.api.state.IIRCState;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Bot {

    public static IRCApi ircApi;
    public static String serverIP;
    public static String serverPort;
    public static String serverChannel;

    public static void main(String[] args) {
        ircApi = new IRCApiImpl(true);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter an IP address:");
        serverIP = scanner.nextLine();

        System.out.println("Enter port number:");
        serverPort = scanner.nextLine();

        System.out.println("Enter channel name:");
        serverChannel = scanner.nextLine();

        IServerParameters serverParameters = new IServerParameters() {
            @Override
            public String getNickname() {
                return "Tausif";
            }

            @Override
            public List<String> getAlternativeNicknames() {
                return Arrays.asList("TausifBot", "IRCBot");
            }

            @Override
            public String getIdent() {
                return "ident";
            }

            @Override
            public String getRealname() {
                return "Tausif";
            }

            @Override
            public IRCServer getServer() {
                return new IRCServer(serverIP, Integer.parseInt(serverPort), false);
            }
        };

        System.out.println("Connecting...");
        ircApi.connect(serverParameters, new Callback<IIRCState>() {
            @Override
            public void onSuccess(IIRCState iircState) {
                ircApi.joinChannel(serverChannel);
                System.out.println("Connected!");
                ircApi.addListener(new HelpCommand());
                ircApi.addListener(new PingCommand());
                ircApi.addListener(new WeatherCommand());
                ircApi.addListener(new DiceCommand());
                ircApi.addListener(new UptimeCommand());

                ircApi.addListener(new GreeterListener());
            }

            @Override
            public void onFailure(Exception e) {
                System.out.println("Failed connecting! " + e.getMessage());
                System.exit(-1);
            }
        });
    }
}
