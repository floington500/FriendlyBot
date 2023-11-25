package com.github.floington500.FriendlyBot.common.command;

import com.github.floington500.FriendlyBot.common.lavaplayer.GuildMusicManager;
import com.github.floington500.FriendlyBot.common.lavaplayer.PlayerManager;
import com.github.floington500.FriendlyBot.jda.commands.VoiceAction;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class SeekCommand extends VoiceAction {


    @Override
    protected void handleVoice(SlashCommandInteractionEvent ctx) {
        int pos = ctx.getOption("seconds").getAsInt();
        int milliseconds = pos*1000;

        Guild guild = ctx.getGuild();
        assert guild != null;
        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(guild);
        final AudioPlayer audioPlayer = musicManager.getAudioPlayer();

        // check if there is a track playing
        if (audioPlayer.getPlayingTrack() == null) {
            ctx.reply("There is no track currently playing").setEphemeral(true).queue();
            return;
        }

        if (!musicManager.getScheduler().seek(milliseconds)) { // send an error message
            ctx.reply("Invalid position.").setEphemeral(true).queue();
            return;
        }

        ctx.reply("Seeking position " + pos + " in the track.").queue();
    }

    @Override
    public String getName() {
        return "seek";
    }
}
