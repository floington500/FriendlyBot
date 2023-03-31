package me.flo456123.FriendlyBot.common.command;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import me.flo456123.FriendlyBot.common.lavaplayer.GuildMusicManager;
import me.flo456123.FriendlyBot.common.lavaplayer.PlayerManager;
import me.flo456123.FriendlyBot.jda.commands.VoiceAction;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class SkipCommand extends VoiceAction {

    @Override
    protected void handleVoice(SlashCommandInteractionEvent ctx) {
        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(ctx.getGuild());
        final AudioPlayer audioPlayer = musicManager.getAudioPlayer();

        // check if there is a track playing
        if (audioPlayer.getPlayingTrack() == null) {
            ctx.reply("There is no track currently playing").setEphemeral(true).queue();
            return;
        }

        musicManager.getScheduler().nextTrack();

        ctx.reply("Skipped the current track!").queue();
    }

    @Override
    public String getName() {
        return "skip";
    }

}