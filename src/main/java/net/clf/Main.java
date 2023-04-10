package net.clf;

import net.clf.jda.config.Config;
import net.clf.jda.config.Environment;

public class Main {
    private static FriendlyBot startup = null;
    private static String token = null;

    public static void main(String[] args) {
        try {
            for (int x = 0; x < args.length; x += 2) {
                String value = args[x + 1];

                switch (args[x].toLowerCase()) {
                    case "-token" -> {
                        System.out.println("[Setup] Using args token!");
                        token = value;
                    }
                    case "-buffersize" -> {
                        int size = Integer.parseInt(value);
                        Config.setBufferSize(size);
                        System.out.println("[Setup] Using args buffer size of " + size + "!");
                    }
                }
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

            System.out.println("Starting bot...");
            startup = new FriendlyBot(token);
        } catch (InterruptedException e) {
            System.out.println("Failed to login!");
        }
    }
}
