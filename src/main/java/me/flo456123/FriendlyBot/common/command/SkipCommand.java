package me.flo456123.FriendlyBot.common.command;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import me.flo456123.FriendlyBot.jda.commands.CommandContext;
import me.flo456123.FriendlyBot.common.lavaplayer.GuildMusicManager;
import me.flo456123.FriendlyBot.common.lavaplayer.PlayerManager;
import me.flo456123.FriendlyBot.jda.commands.VoiceAction;

/**
 * Responsible for skipping the current song that is playing and proceeding to the next one.
 */
public class SkipCommand extends VoiceAction {

    /**
     * Skips the current song that is playing and proceeds to the next one in the queue.
     *
     * @param ctx the CommandContext of the command event.
     */
    @Override
    protected void handleVoice(CommandContext ctx) {
        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(ctx.getGuild());
        final AudioPlayer audioPlayer = musicManager.getAudioPlayer();

        // check if there is a track playing
        if (audioPlayer.getPlayingTrack() == null) {
            ctx.event().reply("There is no track currently playing").setEphemeral(true).queue();
            return;
        }

        // skip to the next track
        musicManager.getScheduler().nextTrack();

        // notify the user that the current track has been skipped
        ctx.event().reply("Skipped the current track!").queue();
    }

    @Override
    public String getName() {
        return "skip";
    }
}
