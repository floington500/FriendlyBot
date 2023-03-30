package me.flo456123.FriendlyBot.common.command;

import me.flo456123.FriendlyBot.jda.commands.CommandContext;
import me.flo456123.FriendlyBot.common.lavaplayer.PlayerManager;
import me.flo456123.FriendlyBot.jda.commands.VoiceAction;
import net.dv8tion.jda.api.entities.Guild;

public class StopCommand extends VoiceAction {

    @Override
    protected void handleVoice(CommandContext ctx) {
        final Guild guild = ctx.getGuild();
        PlayerManager.getInstance().getMusicManager(guild).stopPlayer();

        ctx.event().reply("The player has been stopped and the track has been cleared").queue();
    }

    @Override
    public String getName() {
        return "stop";
    }

}