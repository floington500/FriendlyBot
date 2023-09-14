package com.github.floington500.FriendlyBot.jda.config;

public class Config {
    private static int bufferSize = 2000;

    public static void setBufferSize(int size) {
        bufferSize = size;
    }

    public static int getBufferSize() {
        return bufferSize;
    }
}
