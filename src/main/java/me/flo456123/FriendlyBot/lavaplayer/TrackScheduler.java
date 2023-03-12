package me.flo456123.FriendlyBot.lavaplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TrackScheduler extends AudioEventAdapter {
    private final AudioPlayer player;
    private final BlockingQueue<AudioTrack> queue;
    private boolean looping = false;

    public TrackScheduler(AudioPlayer player) {
        this.player = player;
        this.queue = new LinkedBlockingQueue<>();
    }

    public void queue(AudioTrack track) {

        if (!this.player.startTrack(track, true))
            this.queue.offer(track);

    }

    public AudioPlayer getPlayer() {
        return this.player;
    }

    public BlockingQueue<AudioTrack> getQueue() {
        return this.queue;
    }

    public boolean isLooping() {
        return looping;
    }

    public void setLooping(boolean isLooping) {
        this.looping = isLooping;
    }

    public void nextTrack() {
        this.player.startTrack(this.queue.poll(), false);
    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        if (endReason.mayStartNext) {
            if (this.looping) {
                this.getPlayer().startTrack(track.makeClone(), false);
                return;
            }

            if (endReason.mayStartNext)
                nextTrack();
        }
    }
}