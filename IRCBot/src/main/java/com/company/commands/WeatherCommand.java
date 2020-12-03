package com.company.commands;

import com.company.Bot;
import com.ircclouds.irc.api.domain.messages.ChannelPrivMsg;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import sun.net.www.http.HttpClient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * A basic command that checks the weather for the given place.
 */
public class WeatherCommand extends Command {

    // Get this key from creating an application on https://home.openweathermap.org/users/sign_up
    private String apiKey = "9fc7232171f25d52663d179b7fe7fb38";

    public WeatherCommand() {
        super("weather");
    }

    @Override
    public void execute(ChannelPrivMsg message) {
        final String arguments = message.getText().substring(PREFIX.length() + getCommand().length());
        if (arguments.isEmpty()) {
            Bot.ircApi.message(message.getChannelName(), "No arguments applied! use ´weather <city>`.");
            return;
        }

        try {
            String output = Unirest.get("http://api.openweathermap.org/data/2.5/weather")
                    .queryString("q", arguments.trim())
                    .queryString("appid", apiKey)
                    .asString().getBody();
            JSONObject object = new JSONObject(output);

            double kelvin = object.getJSONObject("main")
                    .getDouble("temp");

            double celcius = kelvin - 273.15;

            double fahrenheit = celcius * (double)(9/5) + 32;

            Bot.ircApi.message(message.getChannelName(), String.format("%s: %s (%s°F %s°C)",
                    object.getString("name"),
                    object.getJSONArray("weather")
                        .getJSONObject(0)
                            .getString("main"),
                    Math.round(fahrenheit * 100) / 100,
                    Math.round(celcius * 100) / 100
            ));

        } catch (UnirestException e) {
            Bot.ircApi.message(message.getChannelName(), "Failed parsing data from server.");
        }
    }
}
