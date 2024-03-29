package com.github.floington500.FriendlyBot.common.command;

import com.github.floington500.FriendlyBot.jda.commands.VoiceAction;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.managers.AudioManager;

import java.util.Objects;

/**
 * Responsible for handling the "join" command, which makes the bot join the user's voice
 */
public class JoinCommand extends VoiceAction {
    private AudioManager audioManager;
    private VoiceChannel memberChannel;

    boolean isSilent = false;

    public JoinCommand() {
    }

    public JoinCommand(boolean isSilent) {
        this.isSilent = isSilent;
    }

    /**
     * Handles the execution of the "join" command, which makes the bot join the user's
     * voice channel after checking the user's and bots voice states and permissions.
     *
     * @param ctx the CommandContext of the command event.
     */
    @Override
    public void handle(SlashCommandInteractionEvent ctx) {
        final Member self = Objects.requireNonNull(ctx.getGuild()).getSelfMember();
        final GuildVoiceState selfVoiceState = self.getVoiceState();

        // check if the bot isn't in a voice channel
        assert selfVoiceState != null;
        if (selfVoiceState.inAudioChannel()) {
            ctx.reply("Sorry! I am already in a voice channel.").setEphemeral(true).queue();
            return;
        }

        final Member client = ctx.getMember();
        assert client != null;
        final GuildVoiceState memberVoiceState = client.getVoiceState();

        // check to see if the user isn't in a voice channel
        assert memberVoiceState != null;
        if (!memberVoiceState.inAudioChannel()) {
            ctx.reply("You need to be in a voice channel to use this command!").setEphemeral(true).queue();
            return;
        }

        memberChannel = Objects.requireNonNull(memberVoiceState.getChannel()).asVoiceChannel();

        final int maxMemberSize = memberChannel.getUserLimit(),
                numMembers = memberChannel.getMembers().size();
        // check if channel is full
        if (maxMemberSize != 0 && numMembers >= maxMemberSize) {
            ctx.reply("Too many users are in the channel!!").setEphemeral(true).queue();
            return;
        }

        audioManager = ctx.getGuild().getAudioManager();

        handleVoice(ctx);
    }

    /**
     * Handles the logic to make the bot join the user's voice channel.
     *
     * @param ctx The context of the command.
     */
    @Override
    protected void handleVoice(SlashCommandInteractionEvent ctx) {
        audioManager.openAudioConnection(memberChannel);

        if (isSilent){
            return;
        }

        ctx.replyFormat("Joined channel **%s**", ctx.getChannel().getName()).queue();
    }

    @Override
    public String getName() {
        return "join";
    }
}
