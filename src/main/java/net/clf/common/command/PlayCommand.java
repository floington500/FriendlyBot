package net.clf.common.command;

import net.clf.common.lavaplayer.PlayerManager;
import net.clf.jda.commands.VoiceAction;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

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
    protected void handleVoice(SlashCommandInteractionEvent ctx) {
        String link = ctx.getOption("query").getAsString();

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
