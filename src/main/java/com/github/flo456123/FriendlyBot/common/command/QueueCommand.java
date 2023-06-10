package com.github.flo456123.FriendlyBot.common.command;

import com.github.flo456123.FriendlyBot.jda.commands.VoiceAction;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import com.github.flo456123.FriendlyBot.common.lavaplayer.GuildMusicManager;
import com.github.flo456123.FriendlyBot.common.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Responsible for displaying the current music queue to the user.
 */
public class QueueCommand extends VoiceAction {
    /**
     * Displays the current music queue to the user.
     *
     * @param ctx The CommandContext of the command event.
     */
    @Override
    protected void handleVoice(SlashCommandInteractionEvent ctx) {
        Guild guild = ctx.getGuild();
        assert guild != null;
        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(guild);
        final BlockingQueue<AudioTrack> queue = musicManager.getScheduler().getQueue();

        // check if queue is empty
        if (queue.isEmpty()) {
            ctx.reply("The queue is currently empty!").setEphemeral(true).queue();
            return;
        }

        final int track_count = Math.min(queue.size(), 5); // limiting the songs displayed to 5
        final List<AudioTrack> trackList = new ArrayList<>(queue);

        StringBuilder message = new StringBuilder("**Queue:**\n");

        for (int i = 0; i < track_count; i++) {
            final AudioTrack track = trackList.get(i);
            final AudioTrackInfo trackInfo = track.getInfo();

            message.append('#')
                    .append(i + 1)
                    .append(" `")
                    .append(trackInfo.title)
                    .append("` by `")
                    .append(trackInfo.author)
                    .append("` [`")
                    .append(formatTime(track.getDuration()))
                    .append("`]\n");
        }

        if (trackList.size() > track_count) {
            message.append("And `")
                    .append(trackList.size() - track_count)
                    .append("` more...");
        }

        // send the queue message to the user
        ctx.reply(message.toString()).setEphemeral(true).queue();
    }

    /**
     * Formats a duration in milliseconds into a formatted string of the format "HH:MM:SS".
     *
     * @param timeInMillis The duration to format, in milliseconds.
     * @return A formatted string of the duration in the format "HH:MM:SS".
     */
    private String formatTime(long timeInMillis) {
        final long hours = timeInMillis / TimeUnit.HOURS.toMillis(1);
        final long minutes = timeInMillis / TimeUnit.MINUTES.toMillis(1);
        final long seconds = timeInMillis % TimeUnit.MINUTES.toMillis(1) / TimeUnit.SECONDS.toMillis(1);

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    @Override
    public String getName() {
        return "queue";
    }
}
