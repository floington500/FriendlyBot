package me.flo456123.FriendlyBot.common.command;

import me.flo456123.FriendlyBot.jda.commands.CommandContext;
import me.flo456123.FriendlyBot.jda.commands.VoiceAction;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

/**
 * Responsible for handling the "join" command, which makes the bot join the user's voice
 */
public class JoinCommand extends VoiceAction {
    private AudioManager audioManager;
    private VoiceChannel memberChannel;

    /**
     * Handles the execution of the "join" command, which makes the bot join the user's
     * voice channel after checking the user's and bots voice states and permissions.
     *
     * @param ctx the CommandContext of the command event.
     */
    @Override
    public void handle(CommandContext ctx) {
        final Member self = ctx.getSelfMember();
        final GuildVoiceState selfVoiceState = self.getVoiceState();

        // check if the bot isn't in a voice channel
        if (selfVoiceState.inAudioChannel()) {
            ctx.event().reply("Sorry! I am already in a voice channel.").setEphemeral(true).queue();
            return;
        }

        final Member client = ctx.getMember();
        final GuildVoiceState memberVoiceState = client.getVoiceState();

        // check to see if the user isn't in a voice channel
        if (!memberVoiceState.inAudioChannel()) {
            ctx.event().reply("You need to be in a voice channel to use this command!").setEphemeral(true).queue();
            return;
        }

        audioManager = ctx.getGuild().getAudioManager();
        memberChannel = memberVoiceState.getChannel().asVoiceChannel();

        // check if the bot has permission to join the user's voice channel
        if (!memberChannel.canTalk(self)) {
            ctx.event().reply("I don't have permission to join that channel!").setEphemeral(true).queue();
            return;
        }

        handleVoice(ctx);
    }

    /**
     * Handles the logic to make the bot join the user's voice channel.
     *
     * @param ctx The context of the command.
     */
    @Override
    protected void handleVoice(CommandContext ctx) {
        audioManager.openAudioConnection(memberChannel);
        ctx.event().replyFormat("Successfully joined `%s`", memberChannel.getName()).queue();
    }

    @Override
    public String getName() {
        return "join";
    }
}
