package com.github.floington500.FriendlyBot.common.command;

import com.github.floington500.FriendlyBot.common.lavaplayer.GuildMusicManager;
import com.github.floington500.FriendlyBot.jda.commands.VoiceAction;
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
        GuildMusicManager musicManager;

        try {
            musicManager = getMusicManager(ctx);
        } catch (IllegalStateException e) {
            ctx.reply(e.getMessage()).setEphemeral(true).queue();
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
