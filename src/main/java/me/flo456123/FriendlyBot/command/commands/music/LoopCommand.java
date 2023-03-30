package me.flo456123.FriendlyBot.command.commands.music;

import me.flo456123.FriendlyBot.command.CommandContext;
import me.flo456123.FriendlyBot.lavaplayer.GuildMusicManager;
import me.flo456123.FriendlyBot.lavaplayer.PlayerManager;

public class LoopCommand extends VoiceAction {

    @Override
    protected void handleVoice(CommandContext ctx) {
        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(ctx.getGuild());
        final boolean newLooping = !musicManager.getScheduler().isLooping();

        musicManager.getScheduler().setLooping(newLooping);

        ctx.event().replyFormat("Loop has been **%s**", newLooping ? "started" : "stopped").queue();
    }

    @Override
    public String getName() {
        return "loop";
    }

}