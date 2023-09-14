package com.github.floington500.FriendlyBot.common.command;

import com.github.floington500.FriendlyBot.jda.commands.VoiceAction;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import com.github.floington500.FriendlyBot.common.lavaplayer.GuildMusicManager;
import com.github.floington500.FriendlyBot.common.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

/**
 * Responsible for displaying information about the currently playing song to the user.
 */
public class NowPlayingCommand extends VoiceAction {

    /**
     * Displays information about the currently playing song to the user. If there is no
     * track playing, a message will be sent to the user indicating this.
     *
     * @param ctx The CommandContext of the command event.
     */
    @Override
    protected void handleVoice(SlashCommandInteractionEvent ctx) {
        final Guild guild = ctx.getGuild();
        assert guild != null;
        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(ctx.getGuild());
        final AudioPlayer audioPlayer = musicManager.getAudioPlayer();
        final AudioTrack track = audioPlayer.getPlayingTrack();

        if (track == null) {
            ctx.reply("There is no track currently playing").setEphemeral(true).queue();
            return;
        }

        final AudioTrackInfo audioTrackInfo = track.getInfo();
        ctx.replyFormat("Now playing: [%s](%s) by `%s` ", audioTrackInfo.title, audioTrackInfo.uri, audioTrackInfo.author)
                .setEphemeral(true)
                .queue();
    }

    @Override
    public String getName() {
        return "nowplaying";
    }
}
