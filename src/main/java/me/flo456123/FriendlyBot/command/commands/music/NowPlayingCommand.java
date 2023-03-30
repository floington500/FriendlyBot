package me.flo456123.FriendlyBot.command.commands.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import me.flo456123.FriendlyBot.command.CommandContext;
import me.flo456123.FriendlyBot.lavaplayer.GuildMusicManager;
import me.flo456123.FriendlyBot.lavaplayer.PlayerManager;

public class NowPlayingCommand extends VoiceAction {

    @Override
    protected void handleVoice(CommandContext ctx) {
        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(ctx.getGuild());
        final AudioPlayer audioPlayer = musicManager.getAudioPlayer();
        final AudioTrack track = audioPlayer.getPlayingTrack();

        if (track == null) {
            ctx.event().reply("There is no track currently playing").setEphemeral(true).queue();
            return;
        }

        final AudioTrackInfo audioTrackInfo = track.getInfo();
        ctx.event().replyFormat("Now playing: [%s](%s) by `%s` ", audioTrackInfo.title, audioTrackInfo.uri, audioTrackInfo.author)
                .setEphemeral(true)
                .queue();
    }

    @Override
    public String getName() {
        return "nowplaying";
    }

}