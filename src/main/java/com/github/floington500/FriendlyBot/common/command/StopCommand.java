package com.github.floington500.FriendlyBot.common.command;

import com.github.floington500.FriendlyBot.jda.commands.VoiceAction;
import com.github.floington500.FriendlyBot.common.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

/**
 * Responsible for stopping the current playing track and clearing the queue.
 */
public class StopCommand extends VoiceAction {

    /**
     * Stops the player and clears the current playing track.
     *
     * @param ctx the CommandContext of the command event.
     */
    @Override
    protected void handleVoice(SlashCommandInteractionEvent ctx) {
        Guild guild = ctx.getGuild();
        assert guild != null;
        PlayerManager.getInstance().getMusicManager(guild).stopPlayer();

        ctx.reply("The player has been stopped and the track has been cleared").queue();
    }

    @Override
    public String getName() {
        return "stop";
    }
}
