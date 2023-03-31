package me.flo456123.FriendlyBot.common.command;

import me.flo456123.FriendlyBot.jda.commands.CommandContext;
import me.flo456123.FriendlyBot.common.lavaplayer.PlayerManager;
import me.flo456123.FriendlyBot.jda.commands.VoiceAction;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Responsible for adding a track to the queue.
 */
public class PlayCommand extends VoiceAction {

    /**
     * Handles the logic for playing a track.
     *
     * @param ctx The context of the command.
     */
    @Override
    protected void handleVoice(CommandContext ctx) {
        String link = ctx.event().getOption("query").getAsString();

        // check if the query is empty
        if (link == null) {
            ctx.event().reply("Invalid query").setEphemeral(true).queue();
            return;
        }

        if (!isUrl(link)) {
            // convert the search query to a YouTube search if it is not a URL
            link = "ytsearch:" + link;
        }

        PlayerManager.getInstance().loadAndPlay(ctx, link);
    }

    /**
     * Determines whether a given string is a valid URL.
     *
     * @param url The string to check.
     * @return Whether the given string is a valid URL.
     */
    private boolean isUrl(String url) {
        try {
            // attempt to parse the string as a URI, returning true if successful
            new URI(url);
            return true;
        } catch (URISyntaxException e) {
            // catch any exceptions that happen from an invalid URI
            return false;
        }
    }

    @Override
    public String getName() {
        return "play";
    }
}
