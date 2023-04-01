package unit.mock.jda;

import com.sedmelluq.discord.lavaplayer.source.AudioSourceManager;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackState;
import com.sedmelluq.discord.lavaplayer.track.TrackMarker;

public class MockAudioTrack implements AudioTrack {
    private AudioTrackInfo info;

    public MockAudioTrack(String title, String author, long length, String identifier, boolean isStream, String uri) {
        info = new AudioTrackInfo(title, author, length, identifier, isStream, uri);
    }

    @Override
    public AudioTrackInfo getInfo() {
        return info;
    }

    @Override
    public String getIdentifier() {
        return info.identifier;
    }

    @Override
    public AudioTrackState getState() {
        return null;
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isSeekable() {
        return false;
    }

    @Override
    public long getPosition() {
        return 0;
    }

    @Override
    public void setPosition(long l) {

    }

    @Override
    public void setMarker(TrackMarker trackMarker) {

    }

    @Override
    public long getDuration() {
        return 0;
    }

    @Override
    public AudioTrack makeClone() {
        return new MockAudioTrack(info.title, info.author, info.length, info.identifier, info.isStream, info.uri);
    }

    @Override
    public AudioSourceManager getSourceManager() {
        return null;
    }

    @Override
    public void setUserData(Object o) {

    }

    @Override
    public Object getUserData() {
        return null;
    }

    @Override
    public <T> T getUserData(Class<T> aClass) {
        return null;
    }
}
