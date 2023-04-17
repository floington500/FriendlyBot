package net.clf.common.lavaplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Responsible for managing the guild-specific
 * guild music managers, and for loading and playing audio tracks and playlists
 * using LavaPlayer.
 */
public class PlayerManager {
    private static PlayerManager INSTANCE;
    private final Map<Long, GuildMusicManager> musicManagers;
    private final AudioPlayerManager audioPlayerManager;

    /**
     * Constructs a new {@link PlayerManager} with an empty map of music managers
     * and initializes the audio player manager with remote and local sources.
     */
    public PlayerManager() {
        musicManagers = new HashMap<>();
        audioPlayerManager = new DefaultAudioPlayerManager();

        AudioSourceManagers.registerRemoteSources(audioPlayerManager);
        AudioSourceManagers.registerLocalSource(audioPlayerManager);
    }

    /**
     * Loads and plays an audio track for the specified guild.
     *
     * @param ctx      the command context for the event
     * @param trackUrl the URL string of the audio track to be played
     */
    public void loadAndPlay(SlashCommandInteractionEvent ctx, String trackUrl) {
        final GuildMusicManager musicManager = this.getMusicManager(ctx.getGuild());
        ctx.deferReply().queue();

        // load the track from the specified URL and handle the result
        audioPlayerManager.loadItemOrdered(musicManager, trackUrl, new AudioLoadResultHandler() {
            /**
             * Called when an audio track is successfully loaded from a link.
             * Adds the track to the queue and sends a message to the user indicating success.
             *
             * @param audioTrack the loaded audio track
             */
            @Override
            public void trackLoaded(AudioTrack audioTrack) {
                loadSong(audioTrack);

                String message = "Adding to queue: `" + audioTrack.getInfo().title + "` by `" + audioTrack.getInfo().author + '`';
                ctx.getHook().editOriginal(message).queue();
            }

            /**
             * Called when a YouTube playlist is successfully loaded from a YouTube search.
             * Adds the first track of the playlist to the queue and sends a message to the user indicating success.
             *
             * @param audioPlaylist the loaded playlist
             */
            @Override
            public void playlistLoaded(AudioPlaylist audioPlaylist) {

                if (audioPlaylist.isSearchResult()){
                    AudioTrack audioTrack = audioPlaylist.getTracks().get(0);
                    loadSong(audioTrack);

                    String message = "Adding to queue: `" + audioTrack.getInfo().title + "` by `" + audioTrack.getInfo().author + '`';
                    ctx.getHook().editOriginal(message).queue();
                    return;
                }

                for (AudioTrack track: audioPlaylist.getTracks()) {
                    loadSong(track);
                }

                String message = "Adding to `playlist` to queue!";
                ctx.getHook().editOriginal(message).queue();
            }

            /**
             * Load the song in into the player
             * @param audio The audio track to load
             * @return true if the song is the first in the queue, and is now playing.
             */
            private boolean loadSong(AudioTrack audio) {
                TrackScheduler trackScheduler = musicManager.getScheduler();

                trackScheduler.queue(audio);

                if (trackScheduler.getPlayer().getPlayingTrack() == null) {
                    trackScheduler.nextTrack();
                    return true;
                }

                return false;
            }

            /**
             * Called when no matches are found for the user's query.
             */
            @Override
            public void noMatches() {
                ctx.reply("No matches were found!").setEphemeral(true).queue();
            }

            /**
             * Triggered if the query submitted by the user is empty or there was an error loading the track.
             *
             * @param e the exception that occurred
             */
            @Override
            public void loadFailed(FriendlyException e) {
                ctx.getHook().editOriginal("Failed to load query").queue();
            }
        });
    }

    /**
     * Gets the {@link GuildMusicManager} for a specific guild. Creates a new one if it doesn't already exist.
     *
     * @param guild the guild to get the {@link GuildMusicManager} for
     * @return the {@link GuildMusicManager} for the guild
     */
    public GuildMusicManager getMusicManager(Guild guild) {
        return musicManagers.computeIfAbsent(guild.getIdLong(), (guildId) -> {
            final GuildMusicManager guildMusicManager = new GuildMusicManager(this.audioPlayerManager);

            guild.getAudioManager().setSendingHandler(guildMusicManager.getSendHandler());

            return guildMusicManager;
        });
    }

    /**
     * Gets the instance of the {@link PlayerManager}. Creates a new one if it doesn't already exist.
     *
     * @return the instance of the {@link PlayerManager}
     */
    public static PlayerManager getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new PlayerManager();
        }

        return INSTANCE;
    }
}
