package me.flo456123.FriendlyBot.command.commands.music;

import me.flo456123.FriendlyBot.command.CommandContext;
import me.flo456123.FriendlyBot.command.ICommand;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;

abstract class VoiceAction implements ICommand {

    public boolean checkChannel(CommandContext ctx) {
        final Member self = ctx.getSelfMember();
        final GuildVoiceState selfVoiceState = self.getVoiceState();

        // check if the bot isn't in a voice channel
        if (!selfVoiceState.inAudioChannel()) {
            ctx.event().reply("I need to be in a voice channel to play songs!").setEphemeral(true).queue();
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

    @Override
    public void handle(CommandContext ctx) {
        checkChannel(ctx);
        handleVoice(ctx);
    }

    protected abstract void handleVoice(CommandContext ctx);

}