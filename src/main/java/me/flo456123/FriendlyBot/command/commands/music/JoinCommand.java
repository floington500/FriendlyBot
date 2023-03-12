package me.flo456123.FriendlyBot.command.commands.music;

import me.flo456123.FriendlyBot.command.CommandContext;
import me.flo456123.FriendlyBot.command.ICommand;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

public class JoinCommand implements ICommand {

    @Override
    public void handle(final CommandContext ctx) {
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

        final AudioManager audioManager = ctx.getGuild().getAudioManager();
        final VoiceChannel memberChannel = memberVoiceState.getChannel().asVoiceChannel();

        // check if bot has permission to join the channel
        if (!memberChannel.canTalk(self)) {
            ctx.event().reply("I don't have permission to join that channel!").setEphemeral(true).queue();
            return;
        }

        audioManager.openAudioConnection(memberChannel);
        ctx.event().replyFormat("Successfully joined `%s`", memberChannel.getName()).queue();
    }

    @Override
    public String getName() {
        return "join";
    }

}
