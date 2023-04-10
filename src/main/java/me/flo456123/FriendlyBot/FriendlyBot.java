package me.flo456123.FriendlyBot;

import me.flo456123.FriendlyBot.common.command.*;
import me.flo456123.FriendlyBot.common.listeners.OnGuildVoiceUpdate;
import me.flo456123.FriendlyBot.common.listeners.OnSlashCommands;
import me.flo456123.FriendlyBot.jda.commands.handler.CommandHandlerImpl;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;
import java.util.EnumSet;

/**
 * Create a new bot instance and prepare and start it up.
 */
@SuppressWarnings("unused")
public class FriendlyBot {
    private String token;
    private JDA jda;

    public FriendlyBot(String token) throws InterruptedException {
        this.jda = createInstance(token);

        setup();
    }

    /**
     * Constructs a new {@link FriendlyBot} instance and initializes the {@link JDA} instance with the specified token and {@link GatewayIntent}.
     * Also sets up the {@link CommandHandlerImpl}, registers the bots slash commands, and adds the bots listeners.
     *
     * @throws LoginException       if the login credentials are invalid
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
        commandHandler.addCommand(Commands.slash("nowplaying", "gives you info on the current song that is playing"), new NowPlayingCommand());
        commandHandler.addCommand(Commands.slash("queue", "shows the songs in queue"), new QueueCommand());
        commandHandler.addCommand(Commands.slash("loop", "loops the current song"), new LoopCommand());
        commandHandler.addCommand(Commands.slash("loopqueue", "loops the queue"), new LoopQueueCommand());
        commandHandler.addCommand(Commands.slash("play", "makes the bot play a song")
                .addOption(OptionType.STRING, "query", "enter a link or search term for the bot to find your song with"), new PlayCommand());
        commandHandler.addCommand(Commands.slash("volume", "control how loud the music that the bot is playing")
                .addOption(OptionType.INTEGER, "amount", "the new volume for the player to play at"), new VolumeCommand());

        commandHandler.updateCommands();

        jda.addEventListener(new OnGuildVoiceUpdate());
        jda.addEventListener(new OnSlashCommands(commandHandler));
    }
}
