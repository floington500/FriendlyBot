package unit.mock.jda;

import com.sedmelluq.discord.lavaplayer.filter.PcmFilterFactory;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventListener;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.playback.AudioFrame;
import com.sedmelluq.discord.lavaplayer.track.playback.MutableAudioFrame;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MockAudioPlayer implements AudioPlayer {
    private AudioTrack currentTrack = null;

    @Override
    public AudioTrack getPlayingTrack() {
        return currentTrack;
    }

    @Override
    public void playTrack(AudioTrack audioTrack) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public boolean startTrack(AudioTrack audioTrack, boolean b) {
        currentTrack = audioTrack;
        return true;
    }

    @Override
    public void stopTrack() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public int getVolume() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public void setVolume(int i) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public void setFilterFactory(PcmFilterFactory pcmFilterFactory) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public void setFrameBufferDuration(Integer integer) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public boolean isPaused() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public void setPaused(boolean b) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public void destroy() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public void addListener(AudioEventListener audioEventListener) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public void removeListener(AudioEventListener audioEventListener) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public void checkCleanup(long l) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public AudioFrame provide() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public AudioFrame provide(long l, TimeUnit timeUnit) throws TimeoutException, InterruptedException {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public boolean provide(MutableAudioFrame mutableAudioFrame) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public boolean provide(MutableAudioFrame mutableAudioFrame, long l, TimeUnit timeUnit) throws TimeoutException, InterruptedException {
        throw new RuntimeException("Method does not exist!");
    }
}
