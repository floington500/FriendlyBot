package me.flo456123.FriendlyBot.jda.commands;

import me.flo456123.FriendlyBot.common.command.JoinCommand;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public abstract class VoiceAction implements ICommand {

    public void checkChannel(SlashCommandInteractionEvent ctx) {
        final Member self = ctx.getGuild().getSelfMember();
        final GuildVoiceState selfVoiceState = self.getVoiceState();

        // check if the bot isn't in a voice channel
        if (!selfVoiceState.inAudioChannel()) {
            //Run the join command, and have the bot join the channel.
            JoinCommand runCmd = new JoinCommand();
            runCmd.handle(ctx);
        }

        final Member user = ctx.getMember();
        final GuildVoiceState memberVoiceState = user.getVoiceState();

        // check to see if the user isn't in a voice channel
        if (!memberVoiceState.inAudioChannel()) {
            ctx.reply("You need to be in a channel in order to use this command!").setEphemeral(true).queue();
            return;
        }

        // check if bot and user are in the same voice channel
        if (!memberVoiceState.getChannel().equals(selfVoiceState.getChannel())) {
            ctx.reply("You need to be in the same channel as me to use this command!").setEphemeral(true).queue();
        }

    }

    @Override
    public void handle(SlashCommandInteractionEvent ctx) {
        checkChannel(ctx);
        handleVoice(ctx);
    }

    protected abstract void handleVoice(SlashCommandInteractionEvent ctx);

}