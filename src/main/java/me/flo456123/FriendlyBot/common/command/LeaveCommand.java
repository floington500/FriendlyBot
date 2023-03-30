package me.flo456123.FriendlyBot.common.command;

import me.flo456123.FriendlyBot.jda.commands.CommandContext;
import me.flo456123.FriendlyBot.common.lavaplayer.PlayerManager;
import me.flo456123.FriendlyBot.jda.commands.VoiceAction;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.managers.AudioManager;

public class LeaveCommand extends VoiceAction {

    @Override
    protected void handleVoice(CommandContext ctx) {
        final Guild guild = ctx.getGuild();
        PlayerManager.getInstance().getMusicManager(guild).stopPlayer();

        final AudioManager audioManager = guild.getAudioManager();
        String channelName = audioManager.getConnectedChannel().getName();
        audioManager.closeAudioConnection();

        ctx.event().replyFormat("Left `%s`", channelName).queue();
    }

    @Override
    public String getName() {
        return "leave";
    }

}