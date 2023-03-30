package me.flo456123.FriendlyBot.command.commands.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import me.flo456123.FriendlyBot.command.CommandContext;
import me.flo456123.FriendlyBot.lavaplayer.GuildMusicManager;
import me.flo456123.FriendlyBot.lavaplayer.PlayerManager;

public class SkipCommand extends VoiceAction {

    @Override
    protected void handleVoice(CommandContext ctx) {
        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(ctx.getGuild());
        final AudioPlayer audioPlayer = musicManager.getAudioPlayer();

        // check if there is a track playing
        if (audioPlayer.getPlayingTrack() == null) {
            ctx.event().reply("There is no track currently playing").setEphemeral(true).queue();
            return;
        }

        musicManager.getScheduler().nextTrack();

        ctx.event().reply("Skipped the current track!").queue();
    }

    @Override
    public String getName() {
        return "skip";
    }

}