package me.flo456123.FriendlyBot.common.command;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import me.flo456123.FriendlyBot.common.lavaplayer.GuildMusicManager;
import me.flo456123.FriendlyBot.common.lavaplayer.PlayerManager;
import me.flo456123.FriendlyBot.jda.commands.VoiceAction;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

/**
 * Responsible for skipping the current song that is playing and proceeding to the next one.
 */
public class SkipCommand extends VoiceAction {

    /**
     * Skips the current song that is playing and proceeds to the next one in the queue.
     *
     * @param ctx the CommandContext of the command event.
     */
    @Override
    protected void handleVoice(SlashCommandInteractionEvent ctx) {
        Guild guild = ctx.getGuild();
        assert guild != null;
        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(guild);
        final AudioPlayer audioPlayer = musicManager.getAudioPlayer();

        // check if there is a track playing
        if (audioPlayer.getPlayingTrack() == null) {
            ctx.reply("There is no track currently playing").setEphemeral(true).queue();
            return;
        }

        // skip to the next track
        musicManager.getScheduler().nextTrack();

        // notify the user that the current track has been skipped
        ctx.reply("Skipped the current track!").queue();
    }

    @Override
    public String getName() {
        return "skip";
    }
}
