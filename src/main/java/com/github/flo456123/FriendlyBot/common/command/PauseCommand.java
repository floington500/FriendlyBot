package com.github.flo456123.FriendlyBot.common.command;

import com.github.flo456123.FriendlyBot.jda.commands.VoiceAction;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.github.flo456123.FriendlyBot.common.lavaplayer.PlayerManager;
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
        ctx.replyFormat("Song has been **%s**", newState ? "paused" : "resumed").queue();
    }

    @Override
    public String getName() {
        return "pause";
    }
}
