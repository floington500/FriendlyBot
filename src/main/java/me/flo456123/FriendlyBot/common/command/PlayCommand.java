package me.flo456123.FriendlyBot.common.command;

import me.flo456123.FriendlyBot.jda.commands.CommandContext;
import me.flo456123.FriendlyBot.common.lavaplayer.PlayerManager;
import me.flo456123.FriendlyBot.jda.commands.VoiceAction;

import java.net.URI;
import java.net.URISyntaxException;

public class PlayCommand extends VoiceAction {

    @Override
    protected void handleVoice(CommandContext ctx) {
        String link = ctx.event().getOption("query").getAsString();

        if (link == null) {
            ctx.event().reply("Invalid query").setEphemeral(true).queue();
            return;
        }

        if (!isUrl(link)) {
            link = "ytsearch:" + link;
        }

        PlayerManager.getInstance().loadAndPlay(ctx, link);
    }

    @Override
    public String getName() {
        return "play";
    }

    private boolean isUrl(String url) {
        try {
            new URI(url);
            return true;
        } catch (URISyntaxException e) {
            return false;
        }
    }

}