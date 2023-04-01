package unit;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import me.flo456123.FriendlyBot.common.lavaplayer.TrackScheduler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import unit.mock.jda.MockAudioPlayer;
import unit.mock.jda.MockAudioTrack;

public class TrackSchedulerTest {
    @Test
    public void runLoopQueueTest() {
        AudioPlayer audioPlayer = new MockAudioPlayer();
        TrackScheduler trackScheduler = new TrackScheduler(audioPlayer);

        MockAudioTrack track1 = new MockAudioTrack("track1", "JuiceWrld", 65, "0", true, "a");
        MockAudioTrack track2 = new MockAudioTrack("track2", "JuiceWrld", 65, "1", true, "b");
        trackScheduler.setLoopingQueue(true);

        trackScheduler.queue(track1);
        trackScheduler.queue(track2);

        trackScheduler.nextTrack();
        Assertions.assertEquals(track1.getInfo().title, trackScheduler.getPlayer().getPlayingTrack().getInfo().title);
        trackScheduler.onTrackEnd(audioPlayer, track1, AudioTrackEndReason.FINISHED);

        Assertions.assertEquals(track2.getInfo().title, trackScheduler.getPlayer().getPlayingTrack().getInfo().title);
        trackScheduler.onTrackEnd(audioPlayer, track1, AudioTrackEndReason.FINISHED);

        Assertions.assertEquals(track1.getInfo().title, trackScheduler.getPlayer().getPlayingTrack().getInfo().title);
        trackScheduler.onTrackEnd(audioPlayer, track1, AudioTrackEndReason.FINISHED);

    }

    @Test
    public void runLoopTest() {
        AudioPlayer audioPlayer = new MockAudioPlayer();
        TrackScheduler trackScheduler = new TrackScheduler(audioPlayer);

        MockAudioTrack track1 = new MockAudioTrack("track1", "JuiceWrld", 65, "0", true, "a");
        MockAudioTrack track2 = new MockAudioTrack("track2", "JuiceWrld", 65, "1", true, "b");
        trackScheduler.setLooping(true);

        trackScheduler.queue(track1);
        trackScheduler.queue(track2);

        trackScheduler.nextTrack();
        Assertions.assertEquals(track1.getInfo().title, trackScheduler.getPlayer().getPlayingTrack().getInfo().title);
        trackScheduler.onTrackEnd(audioPlayer, track1, AudioTrackEndReason.FINISHED);

        Assertions.assertEquals(track1.getInfo().title, trackScheduler.getPlayer().getPlayingTrack().getInfo().title);
        trackScheduler.onTrackEnd(audioPlayer, track1, AudioTrackEndReason.FINISHED);
    }

    @Test
    public void runTrackTest() {
        AudioPlayer audioPlayer = new MockAudioPlayer();
        TrackScheduler trackScheduler = new TrackScheduler(audioPlayer);

        MockAudioTrack track1 = new MockAudioTrack("track1", "JuiceWrld", 65, "0", true, "a");
        MockAudioTrack track2 = new MockAudioTrack("track2", "JuiceWrld", 65, "1", true, "b");

        trackScheduler.queue(track1);
        trackScheduler.queue(track2);

        trackScheduler.nextTrack();
        Assertions.assertEquals(track1.getInfo().title, trackScheduler.getPlayer().getPlayingTrack().getInfo().title);
        trackScheduler.onTrackEnd(audioPlayer, track1, AudioTrackEndReason.FINISHED);

        Assertions.assertEquals(track2.getInfo().title, trackScheduler.getPlayer().getPlayingTrack().getInfo().title);
        trackScheduler.onTrackEnd(audioPlayer, track1, AudioTrackEndReason.FINISHED);

        Assertions.assertNull(trackScheduler.getPlayer().getPlayingTrack());
        trackScheduler.onTrackEnd(audioPlayer, track1, AudioTrackEndReason.FINISHED);
    }
}
