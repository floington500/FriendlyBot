package com.github.floington500.FriendlyBot.common.command;

import com.github.floington500.FriendlyBot.jda.commands.VoiceAction;
import com.github.floington500.FriendlyBot.common.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.managers.AudioManager;

/**
 * Responsible for handling the "leave" command, which makes the bot leave the user's voice channel.
 */
public class LeaveCommand extends VoiceAction {
    /**
     * Differs from superclass in the fact that it cannot be called
     * if the bot is not in a voice channel.
     *
     * @param ctx the {@link SlashCommandInteractionEvent}.
     */
    @Override
    public void checkChannel(SlashCommandInteractionEvent ctx) {
        final Member self = ctx.getGuild().getSelfMember();
        final Member user = ctx.getMember();

        // check to see if the user isn't in a voice channel
        if (!user.getVoiceState().inAudioChannel()) {
            ctx.reply("You need to be in a channel in order to use this command!").setEphemeral(true).queue();
            return;
        }

        // check if the bot isn't in a voice channel
        if (!self.getVoiceState().inAudioChannel()) {
            ctx.reply("I need to be in a channel in order to use this command!").setEphemeral(true).queue();
            return;
        }

        handleVoice(ctx);
    }

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
