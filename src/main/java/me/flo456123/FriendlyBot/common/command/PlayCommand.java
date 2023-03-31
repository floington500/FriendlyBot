package me.flo456123.FriendlyBot.common.command;

import me.flo456123.FriendlyBot.common.lavaplayer.PlayerManager;
import me.flo456123.FriendlyBot.jda.commands.VoiceAction;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.net.URI;
import java.net.URISyntaxException;

public class PlayCommand extends VoiceAction {

    @Override
    protected void handleVoice(SlashCommandInteractionEvent ctx) {
        String link = ctx.getOption("query").getAsString();

        if (link == null) {
            ctx.reply("Invalid query").setEphemeral(true).queue();
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