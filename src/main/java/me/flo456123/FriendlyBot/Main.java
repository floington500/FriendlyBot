package me.flo456123.FriendlyBot;

import me.flo456123.FriendlyBot.jda.config.Config;
import me.flo456123.FriendlyBot.jda.config.Environment;

public class Main {
    private static DiscordBot startup = null;
    private static String token = null;

    public static void main(String[] args) {
        try {
            for (int x = 0; x < args.length; x += 2) {
                String value = args[x + 1];

                switch (args[x].toLowerCase()) {
                    case "-token":
                        System.out.println("[Setup] Using args token!");
                        token = value;
                        continue;

                    case "-buffersize":
                        int size = Integer.parseInt(value);
                        Config.setBufferSize(size);
                        System.out.println("[Setup] Using args buffer size of " + size + "!");
                        continue;
                }

                if (token == null) {
                    String env = Environment.get("TOKEN");

                    if (env != null) {
                        token = env;
                    } else {
                        System.out.println("[Setup] Failed to locate token");
                        return;
                    }
                }
            }

            System.out.println("Starting bot...");
            startup = new DiscordBot(token);
        } catch (InterruptedException e) {
            System.out.println("Failed to login!");
        }
    }
}
