package me.flo456123.FriendlyBot.jda.config;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * The {@link Config} class provides access to environment variables stored in a .env file located in the application's root directory.
 */
public class Config {
    private static final Dotenv dotenv = Dotenv.configure().directory("./.env").load();

    /**
     * Retrieves the value of the environment variable with the specified key.
     *
     * @param key the name of the environment variable
     * @return the value of the environment variable, or null if it is not set
     */
    public static String get(String key) {
        return dotenv.get(key);
    }
}
