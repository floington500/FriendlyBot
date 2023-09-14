package com.github.floington500.FriendlyBot;

import com.github.floington500.FriendlyBot.jda.config.Config;
import com.github.floington500.FriendlyBot.jda.config.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static DiscordBot startup = null;
    private static String token = null;

    public static void main(String[] args) {
        try {
            for (int x = 0; x < args.length; x += 2) {
                String value = args[x + 1];

                switch (args[x].toLowerCase()) {
                    case "-token" -> {
                        LOGGER.info("[Setup] Using args token!");
                        token = value;
                    }
                    case "-buffersize" -> {
                        int size = Integer.parseInt(value);
                        Config.setBufferSize(size);
                        LOGGER.info("[Setup] Using args buffer size of " + size + "!");
                    }
                }
            }

            if (token == null) {
                String env = Environment.get("TOKEN");

                if (env != null) {
                    token = env;
                } else {
                    LOGGER.info("[Setup] Failed to locate token");
                    return;
                }
            }

            LOGGER.info("Starting bot...");
            startup = new DiscordBot(token);
        } catch (InterruptedException e) {
            LOGGER.error("Failed to login!");
        }
    }
}
