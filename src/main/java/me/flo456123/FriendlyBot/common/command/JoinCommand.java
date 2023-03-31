package me.flo456123.FriendlyBot.common.command;

import me.flo456123.FriendlyBot.jda.commands.VoiceAction;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.managers.AudioManager;

import java.util.Objects;

public class JoinCommand extends VoiceAction {
    private AudioManager audioManager;
    private VoiceChannel memberChannel;

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

        audioManager = ctx.getGuild().getAudioManager();
        memberChannel = Objects.requireNonNull(memberVoiceState.getChannel()).asVoiceChannel();

        if (!memberChannel.canTalk(self)) {
            ctx.reply("I don't have permission to join that channel!").setEphemeral(true).queue();
            return;
        }

        handleVoice(ctx);
    }

    @Override
    protected void handleVoice(SlashCommandInteractionEvent ctx) {
        audioManager.openAudioConnection(memberChannel);
        ctx.replyFormat("Successfully joined `%s`", memberChannel.getName()).queue();
    }

    @Override
    public String getName() {
        return "join";
    }

}
