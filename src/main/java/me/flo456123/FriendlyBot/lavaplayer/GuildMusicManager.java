package me.flo456123.FriendlyBot.lavaplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import org.jetbrains.annotations.NotNull;

public class GuildMusicManager {
    private final AudioPlayer audioPlayer;

    private final TrackScheduler scheduler;
    private final AudioPlayerSendHandler sendHandler;

    public GuildMusicManager(@NotNull AudioPlayerManager manager) {
        this.audioPlayer = manager.createPlayer();
        this.scheduler = new TrackScheduler(this.audioPlayer);
        this.audioPlayer.addListener(this.scheduler);
        this.sendHandler = new AudioPlayerSendHandler(audioPlayer);
    }

    public TrackScheduler getScheduler() {
        return scheduler;
    }

    public AudioPlayer getAudioPlayer() {
        return this.audioPlayer;
    }

    public AudioPlayerSendHandler getSendHandler() {
        return this.sendHandler;
    }

    public void stopPlayer() {
        getScheduler().setLooping(false);
        getScheduler().getQueue().clear();
        getScheduler().getPlayer().stopTrack();
    }

}