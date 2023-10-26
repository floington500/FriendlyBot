package com.github.floington500.FriendlyBot;

import com.github.floington500.FriendlyBot.common.listeners.OnGuildVoiceUpdate;
import com.github.floington500.FriendlyBot.common.listeners.OnSlashCommands;
import com.github.floington500.FriendlyBot.jda.commands.handler.CommandHandlerImpl;
import com.github.floington500.FriendlyBot.common.command.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EnumSet;

/**
 * Create a new bot instance and prepare and start it up.
 */
@SuppressWarnings("unused")
public class DiscordBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private String token;
    private final JDA jda;

    public DiscordBot(String token) throws InterruptedException {
        LOGGER.info("Starting up music bot.");
        this.jda = createInstance(token);

        setup();
    }

    /**
     * Constructs a new {@link DiscordBot} instance and initializes the {@link JDA} instance with the specified token and {@link GatewayIntent}.
     * Also sets up the {@link CommandHandlerImpl}, registers the bots slash commands, and adds the bots listeners.
     *
     * @throws InterruptedException if the JDA instance was interrupted while waiting to connect
     */
    private JDA createInstance(String token) throws InterruptedException {
        return JDABuilder.createDefault(
                        token,
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
    }

    private void setup() {
        CommandHandlerImpl commandHandler = new CommandHandlerImpl(jda);

        commandHandler.addCommand(Commands.slash("join", "makes the bot join your voice channel"), new JoinCommand());
        commandHandler.addCommand(Commands.slash("leave", "makes the bot leave your voice channel"), new LeaveCommand());
        commandHandler.addCommand(Commands.slash("skip", "skips the current song that the bot is playing"), new SkipCommand());
        commandHandler.addCommand(Commands.slash("stop", "stops the current song and clears the queue"), new StopCommand());
        commandHandler.addCommand(Commands.slash("now-playing", "gives you info on the current song that is playing"), new NowPlayingCommand());
        commandHandler.addCommand(Commands.slash("queue", "shows the songs in queue"), new QueueCommand());
        commandHandler.addCommand(Commands.slash("loop", "loops the current song"), new LoopCommand());
        commandHandler.addCommand(Commands.slash("loop-queue", "loops the queue"), new LoopQueueCommand());
        commandHandler.addCommand(Commands.slash("play", "makes the bot play a song")
                .addOption(OptionType.STRING, "query", "enter a link or search term for the bot to find your song with", true), new PlayCommand());
        commandHandler.addCommand(Commands.slash("volume", "control how loud the music that the bot is playing")
                .addOption(OptionType.INTEGER, "amount", "the new volume for the player to play at", true), new VolumeCommand());
        commandHandler.addCommand(Commands.slash("pause", "used for both pausing and resuming a song"), new PauseCommand());
        commandHandler.addCommand(Commands.slash("shuffle", "used to shuffle the queue"), new ShuffleCommand());

        commandHandler.updateCommands();

        jda.addEventListener(new OnGuildVoiceUpdate());
        jda.addEventListener(new OnSlashCommands(commandHandler));
    }
}
