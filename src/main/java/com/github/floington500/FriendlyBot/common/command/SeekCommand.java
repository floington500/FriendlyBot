package com.github.floington500.FriendlyBot.common.command;

import com.github.floington500.FriendlyBot.common.lavaplayer.GuildMusicManager;
import com.github.floington500.FriendlyBot.jda.commands.VoiceAction;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class SeekCommand extends VoiceAction {

    @Override
    protected void handleVoice(SlashCommandInteractionEvent ctx) {
        int seconds = ctx.getOption("seconds").getAsInt();
        long pos = secondsToMilliseconds(seconds);

        try {
            GuildMusicManager musicManager = getMusicManager(ctx);

            musicManager.getScheduler().seek(pos);
        } catch (IllegalStateException e) {
            ctx.reply(e.getMessage()).setEphemeral(true).queue();
            return;
        }

        ctx.reply("Seeking position " + seconds + " in the track.").queue();
    }

    private long secondsToMilliseconds(int seconds) {
        return seconds* 1000L;
    }

    @Override
    public String getName() {
        return "seek";
    }
}
