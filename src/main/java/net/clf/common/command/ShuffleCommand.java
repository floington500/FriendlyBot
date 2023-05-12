package net.clf.common.command;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.clf.common.lavaplayer.GuildMusicManager;
import net.clf.common.lavaplayer.PlayerManager;
import net.clf.jda.commands.VoiceAction;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * The shuffle command is used to shuffle the current songs in the queue.
 */
public class ShuffleCommand extends VoiceAction {
    /**
     * Randomly shuffles the songs in a queue.
     *
     * @param ctx the CommandContext of the command event.
     */
    @Override
    protected void handleVoice(SlashCommandInteractionEvent ctx) {
        Guild guild = ctx.getGuild();
        assert guild != null;
        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(guild);
        final BlockingQueue<AudioTrack> queue = musicManager.getScheduler().getQueue();

        final int sz = queue.size();

        // create a temp list to store the audio tracks in
        List<AudioTrack> tempList = new ArrayList<>(sz);
        queue.drainTo(tempList);

        // shuffle them using the collections API, because WHY would i do this myself
        Collections.shuffle(tempList);

        // clear the current queue before rebuilding it
        queue.clear();

        // add the new songs to the queue
        for (AudioTrack track : tempList) {
            queue.offer(track);
        }

        ctx.reply("The queue has been randomly shuffled!").queue();
    }

    @Override
    public String getName() {
        return "shuffle";
    }
}
