package me.flo456123.FriendlyBot.jda.config;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {
    private static final Dotenv dotenv = Dotenv.configure().directory("./.env").load();

    public static String get(String key) {
        return dotenv.get(key);
    }

}