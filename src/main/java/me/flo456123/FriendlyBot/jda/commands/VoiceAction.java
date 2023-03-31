package me.flo456123.FriendlyBot.jda.commands;

import me.flo456123.FriendlyBot.common.command.JoinCommand;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;

/**
 * VoiceAction is an abstract class that implements the ICommand interface and provides a method for handling
 * voice-related commands.
 */
public abstract class VoiceAction implements ICommand {

    /**
     * Checks whether the user who triggered the command and the bot are in the same voice channel.
     * If the bot or the user are not in a voice channel, the bot will automatically join the user's voice channel.
     *
     * @param ctx the CommandContext of the command event.
     * @return true if the user and bot are in the same channel, false otherwise.
     */
    public boolean checkChannel(CommandContext ctx) {
        final Member self = ctx.getSelfMember();
        final GuildVoiceState selfVoiceState = self.getVoiceState();

        // check if the bot isn't in a voice channel
        if (!selfVoiceState.inAudioChannel()) {
            // Run the join command, and have the bot join the channel.
            JoinCommand runCmd = new JoinCommand();
            runCmd.handle(ctx);
        }

        final Member user = ctx.getMember();
        final GuildVoiceState memberVoiceState = user.getVoiceState();

        // check to see if the user isn't in a voice channel
        if (!memberVoiceState.inAudioChannel()) {
            ctx.event().reply("You need to be in a channel in order to use this command!").setEphemeral(true).queue();
            return false;
        }

        // check if bot and user are in the same voice channel
        if (!memberVoiceState.getChannel().equals(selfVoiceState.getChannel())) {
            ctx.event().reply("You need to be in the same channel as me to use this command!").setEphemeral(true).queue();
            return false;
        }

        return true;
    }

    /**
     * Handles the command by first checking if the user and bot are in the same voice channel, then executing
     * the implementation-specific logic in the handleVoice method.
     *
     * @param ctx The CommandContext of the command event.
     */
    @Override
    public void handle(CommandContext ctx) {
        checkChannel(ctx);
        handleVoice(ctx);
    }

    /**
     * Implementation-specific logic for the command that should be executed.
     *
     * @param ctx the CommandContext of the command event.
     */
    protected abstract void handleVoice(CommandContext ctx);
}
