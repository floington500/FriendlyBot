package me.flo456123.FriendlyBot.common.lavaplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.playback.MutableAudioFrame;
import me.flo456123.FriendlyBot.jda.config.Config;
import me.flo456123.FriendlyBot.jda.config.Environment;
import net.dv8tion.jda.api.audio.AudioSendHandler;

import java.nio.ByteBuffer;

/**
 * A {@link AudioSendHandler} implementation that sends audio data from an {@link AudioPlayer} to a Discord voice connection.
 */
public class AudioPlayerSendHandler implements AudioSendHandler {
    private final AudioPlayer audioPlayer;
    private final ByteBuffer buffer;
    private final MutableAudioFrame audioFrame;

    /**
     * Constructs a new AudioPlayerSendHandler with the specified {@link AudioPlayer}.
     *
     * @param audioPlayer the {@link AudioPlayer} to send audio from
     */
    public AudioPlayerSendHandler(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
        buffer = ByteBuffer.allocate(Config.getBufferSize()); // can be adjusted to optimize performance
        audioFrame = new MutableAudioFrame();
        audioFrame.setBuffer(buffer);
    }

    /**
     * Determines whether the AudioPlayerSendHandler can provide audio data to the Discord voice connection.
     *
     * @return true if the AudioPlayerSendHandler can provide audio data, false otherwise
     */
    @Override
    public boolean canProvide() {
        return audioPlayer.provide(audioFrame);
    }

    /**
     * Provides 20 milliseconds worth of audio data to the Discord voice connection.
     *
     * @return the audio data to send to the Discord voice connection
     */
    @Override
    public ByteBuffer provide20MsAudio() {
        return buffer.flip();
    }

    /**
     * Determines whether the AudioPlayerSendHandler uses Opus encoding.
     *
     * @return true if Opus encoding is used, false otherwise
     */
    @Override
    public boolean isOpus() {
        return true;
    }
}
