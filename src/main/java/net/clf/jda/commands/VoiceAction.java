package net.clf.jda.commands;

import net.clf.common.command.JoinCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

/**
 * VoiceAction is an abstract class that implements the ICommand interface and provides a method for handling
 * voice-related commands.
 */
public abstract class VoiceAction implements ICommand {
    /**
     * Checks whether the user who triggered the command and the bot are in the same voice channel.
     * If the bot or the user are not in a voice channel, the bot will automatically join the user's voice channel.
     *
     * @param ctx the {@link SlashCommandInteractionEvent}.
     * @return true if the user and bot are in the same channel, false otherwise.
     */
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
            JoinCommand runCmd = new JoinCommand();
            runCmd.handle(ctx);
        }

        handleVoice(ctx);
    }

    /**
     * Handles the command by first checking if the user and bot are in the same voice channel, then executing
     * the implementation-specific logic in the handleVoice method.
     *
     * @param ctx The CommandContext of the command event.
     */
    @Override
    public void handle(SlashCommandInteractionEvent ctx) {
        checkChannel(ctx);
    }

    /**
     * Implementation-specific logic for the command that should be executed.
     *
     * @param ctx the CommandContext of the command event.
     */
    protected abstract void handleVoice(SlashCommandInteractionEvent ctx);

}
