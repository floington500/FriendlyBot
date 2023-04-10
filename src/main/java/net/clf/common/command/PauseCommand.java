package net.clf.common.command;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.clf.common.lavaplayer.PlayerManager;
import net.clf.jda.commands.VoiceAction;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

/**
 * Used for both pausing and resuming a song
 */
public class PauseCommand extends VoiceAction {
    /**
     * Pauses or resumes a song depending on the current state
     * of the {@link AudioPlayer}.
     *
     * @param ctx the CommandContext of the command event.
     */
    @Override
    protected void handleVoice(SlashCommandInteractionEvent ctx) {
        final Guild guild = ctx.getGuild();
        assert guild != null;
        final AudioPlayer player = PlayerManager.getInstance().getMusicManager(guild).getAudioPlayer();
        final boolean newState = !player.isPaused();

        player.setPaused(newState);
        ctx.replyFormat("Song has been **%sd**", newState ? "paused" : "resumed").queue();
    }

    @Override
    public String getName() {
        return "pause";
    }
}
