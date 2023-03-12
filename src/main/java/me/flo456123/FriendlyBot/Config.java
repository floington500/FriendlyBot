package me.flo456123.FriendlyBot;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {
    private static final Dotenv dotenv = Dotenv.configure().directory("C:\\Users\\zacha\\IdeaProjects\\Friendly Bot\\.env").load();

    public static String get(String key) {
        return dotenv.get(key);
    }

}