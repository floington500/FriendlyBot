package me.flo456123.FriendlyBot.command.commands.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import me.flo456123.FriendlyBot.command.CommandContext;
import me.flo456123.FriendlyBot.command.ICommand;
import me.flo456123.FriendlyBot.lavaplayer.GuildMusicManager;
import me.flo456123.FriendlyBot.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;

public class NowPlayingCommand implements ICommand {


    @Override
    public void handle(CommandContext ctx) {
        final Member self = ctx.getSelfMember();
        final GuildVoiceState selfVoiceState = self.getVoiceState();

        // check if the bot isn't in a voice channel
        if (!selfVoiceState.inAudioChannel()) {
            ctx.event().reply("I need to be in a voice channel for this to work.").setEphemeral(true).queue();
            return;
        }

        final Member member = ctx.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();

        // check to see if the user isn't in a voice channel
        if (!memberVoiceState.inAudioChannel()) {
            ctx.event().reply("You need to be in a voice channel for this to work.").setEphemeral(true).queue();
            return;
        }

        // check if bot and user are in the same voice channel
        if (!memberVoiceState.getChannel().equals(selfVoiceState.getChannel())) {
            ctx.event().reply("You need to be in the same voice channel as me for this to work!").setEphemeral(true).queue();
            return;
        }

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
