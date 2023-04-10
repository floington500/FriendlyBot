package net.clf.common.lavaplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * The TrackScheduler class is responsible for managing the audio track queue for a specific guild's audio player.
 * It provides methods for adding tracks to the queue, getting the current audio player instance and audio track queue,
 * setting the loop status of the current track, and starting the next track in the queue when a track finishes playing.
 */
public class TrackScheduler extends AudioEventAdapter {
    private final AudioPlayer player;
    private final BlockingQueue<AudioTrack> queue;
    private int looping = 0; // 0 = off, 1 = loop track, 2 = loop queue.

    /**
     * Constructs a new TrackScheduler for the given AudioPlayer.
     *
     * @param player the AudioPlayer instance to associate this TrackScheduler with.
     */
    public TrackScheduler(AudioPlayer player) {
        this.player = player;
        queue = new LinkedBlockingQueue<>();
    }

    /**
     * Adds an audio track to the queue.
     *
     * @param track the audio track to add to the queue.
     */
    public void queue(AudioTrack track) {
        queue.offer(track);
    }

    /**
     * Returns the associated AudioPlayer instance.
     *
     * @return the AudioPlayer instance associated with this TrackScheduler.
     */
    public AudioPlayer getPlayer() {
        return player;
    }

    /**
     * Returns the current queue of audio tracks.
     *
     * @return the current queue of audio tracks.
     */
    public BlockingQueue<AudioTrack> getQueue() {
        return queue;
    }

    /**
     * Returns whether the player is currently set to loop the current track.
     *
     * @return true if the player is currently looping, false otherwise.
     */
    public boolean isLooping() {
        return looping == 1;
    }

    /**
     * Loops the current songs in the queue.
     * @return
     */
    public boolean isLoopingQueue() {
        return looping == 2;
    }

    /**
     * Sets whether the player should loop the current track.
     *
     * @param isLooping whether the player should loop the current track.
     */
    public void setLooping(boolean isLooping) {
        if (isLoopingQueue()) {
            return;
        }

        if (isLooping) {
            looping = 1;
            return;
        }

        looping = 0;
    }

    /**
     * Sets whether the player should loop the queue.
     *
     * @param isLoopingQueue whether the player should loop the current track.
     */
    public void setLoopingQueue(boolean isLoopingQueue) {
        if (isLooping()) {
            return;
        }

        if (isLoopingQueue) {
            looping = 2;
            return;
        }

        looping = 0;
    }

    /**
     * Starts playing the next track in the queue. If there are no tracks in the queue,
     * the player will stop.
     */
    public void nextTrack() {
        player.startTrack(queue.poll(), false);
    }

    /**
     * Called automatically when a track has finished playing.
     *
     * @param player    the AudioPlayer instance associated with this TrackScheduler.
     * @param track     the track that has finished playing.
     * @param endReason the reason the track stopped playing.
     */
    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        if (endReason.mayStartNext) {
            if (isLooping()) {
                this.getPlayer().startTrack(track.makeClone(), false);
                return;
            }

            if (isLoopingQueue()) {
                this.queue.add(track.makeClone());
            }

            if (endReason.mayStartNext) {
                nextTrack();
            }
        }
    }
}