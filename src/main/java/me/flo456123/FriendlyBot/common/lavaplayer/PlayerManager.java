package me.flo456123.FriendlyBot.common.lavaplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import me.flo456123.FriendlyBot.jda.commands.CommandContext;
import net.dv8tion.jda.api.entities.Guild;

import java.util.HashMap;
import java.util.Map;

public class PlayerManager {
    private static PlayerManager INSTANCE;
    private final Map<Long, GuildMusicManager> musicManagers;
    private final AudioPlayerManager audioPlayerManager;

    public PlayerManager() {
        this.musicManagers = new HashMap<>();
        this.audioPlayerManager = new DefaultAudioPlayerManager();

        AudioSourceManagers.registerRemoteSources(this.audioPlayerManager);
        AudioSourceManagers.registerLocalSource(this.audioPlayerManager);
    }

    public void loadAndPlay(CommandContext ctx, String trackUrl) {
        final GuildMusicManager musicManager = this.getMusicManager(ctx.getGuild());

        this.audioPlayerManager.loadItemOrdered(musicManager, trackUrl, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack audioTrack) {
                musicManager.getScheduler().queue(audioTrack);

                String message = "Adding to queue: `" +
                        audioTrack.getInfo().title +
                        "` by `" +
                        audioTrack.getInfo().author +
                        '`';

                ctx.event().reply(message).queue();
            }

            @Override
            public void playlistLoaded(AudioPlaylist audioPlaylist) {
                final AudioTrack audioTrack = audioPlaylist.getTracks().get(0);
                musicManager.getScheduler().queue(audioTrack);

                String message = "Adding to queue: `" +
                        audioTrack.getInfo().title +
                        "` by `" +
                        audioTrack.getInfo().author +
                        '`';

                ctx.event().reply(message).queue();
            }

            @Override
            public void noMatches() {
                ctx.event().reply("No matches were found!").setEphemeral(true).queue();
            }

            @Override
            public void loadFailed(FriendlyException e) {
                ctx.event().reply("Failed to load query").setEphemeral(true).queue();
            }
        });
    }

    public GuildMusicManager getMusicManager(Guild guild) {
        return this.musicManagers.computeIfAbsent(guild.getIdLong(), (guildId) -> {
            final GuildMusicManager guildMusicManager = new GuildMusicManager(this.audioPlayerManager);

            guild.getAudioManager().setSendingHandler(guildMusicManager.getSendHandler());

            return guildMusicManager;
        });
    }

    public static PlayerManager getInstance() {

        if (INSTANCE == null)
            INSTANCE = new PlayerManager();

        return INSTANCE;
    }

}