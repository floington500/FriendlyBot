package com.github.flo456123.FriendlyBot.common.lavaplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import org.jetbrains.annotations.NotNull;

/**
 * Manages audio playback for a single guild, including the {@link AudioPlayer}, {@link TrackScheduler}, and {@link AudioPlayerSendHandler}.
 */
public class GuildMusicManager {
    private final AudioPlayer audioPlayer;
    private final TrackScheduler scheduler;
    private final AudioPlayerSendHandler sendHandler;

    /**
     * Constructs a new {@link GuildMusicManager} instance for a guild, using the provided audio player manager to create
     * an audio player, track scheduler, and audio send handler.
     *
     * @param manager the {@link AudioPlayerManager} to create an audio player from
     */
    public GuildMusicManager(@NotNull AudioPlayerManager manager) {
        audioPlayer = manager.createPlayer();
        scheduler = new TrackScheduler(this.audioPlayer);
        audioPlayer.addListener(this.scheduler);
        sendHandler = new AudioPlayerSendHandler(audioPlayer);
    }

    /**
     * Returns the {@link TrackScheduler} associated with this guild music manager.
     *
     * @return the track scheduler
     */
    public TrackScheduler getScheduler() {
        return scheduler;
    }

    /**
     * Returns the {@link AudioPlayer} associated with this guild music manager.
     *
     * @return the audio player
     */
    public AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }

    /**
     * Returns the {@link AudioPlayerSendHandler} associated with this guild music manager.
     *
     * @return the audio send handler
     */
    public AudioPlayerSendHandler getSendHandler() {
        return sendHandler;
    }

    /**
     * Stops the {@link AudioPlayer} and clears its track queue.
     */
    public void stopPlayer() {
        audioPlayer.stopTrack();
        scheduler.getQueue().clear();
        audioPlayer.setVolume(100);
        scheduler.setLooping(false);
    }
}
