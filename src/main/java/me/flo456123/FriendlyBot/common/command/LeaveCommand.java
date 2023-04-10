package me.flo456123.FriendlyBot.common.command;

import me.flo456123.FriendlyBot.common.lavaplayer.PlayerManager;
import me.flo456123.FriendlyBot.jda.commands.VoiceAction;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.managers.AudioManager;

/**
 * Responsible for handling the "leave" command, which makes the bot leave the user's voice channel.
 */
public class LeaveCommand extends VoiceAction {

    /**
     * Handles the logic for making the bot leave the voice channel that the user is currently in.
     *
     * @param ctx the CommandContext of the command event.
     */
    @Override
    protected void handleVoice(SlashCommandInteractionEvent ctx) {
        final Guild guild = ctx.getGuild();
        assert guild != null;
        PlayerManager.getInstance().getMusicManager(guild).stopPlayer();

        final AudioManager audioManager = guild.getAudioManager();
        String channelName = audioManager.getConnectedChannel().getName();
        audioManager.closeAudioConnection();

        ctx.replyFormat("Left `%s`", channelName).queue();
    }

    @Override
    public String getName() {
        return "leave";
    }
}
