package com.github.floington500.FriendlyBot.common.command;

import com.github.floington500.FriendlyBot.jda.commands.VoiceAction;
import com.github.floington500.FriendlyBot.common.lavaplayer.GuildMusicManager;
import com.github.floington500.FriendlyBot.common.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

/**
 * Responsible for handling the "loop" command, which loops the current song that is playing.
 */
public class LoopCommand extends VoiceAction {

    /**
     * Handles the logic for looping the current song that is playing.
     *
     * @param ctx the CommandContext of the command event.
     */
    @Override
    protected void handleVoice(SlashCommandInteractionEvent ctx) {
        final Guild guild = ctx.getGuild();
        assert guild != null;
        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(guild);
        final boolean newLooping = !musicManager.getScheduler().isLooping();

        musicManager.getScheduler().setLooping(newLooping);

        ctx.replyFormat("Loop has been **%s**", newLooping ? "started" : "stopped").queue();
    }

    @Override
    public String getName() {
        return "loop";
    }
}
