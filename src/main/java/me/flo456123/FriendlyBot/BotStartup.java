package me.flo456123.FriendlyBot;

import me.flo456123.FriendlyBot.listeners.Listener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class BotStartup {
    
    private final JDA jda;

    public BotStartup() throws LoginException, InterruptedException {
        jda = JDABuilder.createDefault(
                Config.get("TOKEN"),
                        GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.GUILD_VOICE_STATES)
                .disableCache(EnumSet.of(
                        CacheFlag.CLIENT_STATUS,
                        CacheFlag.ACTIVITY,
                        CacheFlag.EMOJI,
                        CacheFlag.STICKER,
                        CacheFlag.SCHEDULED_EVENTS
                ))
                .enableCache(CacheFlag.VOICE_STATE)
                .build()
                .awaitReady();
        List<CommandData> commands = new ArrayList<>();
        commands.add(Commands.slash("join", "makes the bot join your voice channel"));
        commands.add(Commands.slash("leave", "makes the bot leave your voice channel"));
        commands.add(Commands.slash("play", "makes the bot play a song")
                .addOption(OptionType.STRING, "query", "enter a link or search term for the bot to find your song with"));
        commands.add(Commands.slash("skip", "skips the current song that the bot is playing"));
        commands.add(Commands.slash("stop", "stops the current song and clears the queue"));
        commands.add(Commands.slash("nowplaying", "gives you info on the current song that is playing"));
        commands.add(Commands.slash("queue", "shows the songs in queue"));
        commands.add(Commands.slash("loop", "loops the current song"));

        jda.updateCommands().addCommands(commands).queue();

        jda.addEventListener(new Listener());
    }

    public JDA getJDA() {
        return jda;
    }

    public static void main(String[] args) {
        try {
            BotStartup startup = new BotStartup();
        } catch (LoginException | InterruptedException e) {
            System.out.println("Failed to login!");
        }

    }

}