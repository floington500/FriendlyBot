package com.github.floington500.FriendlyBot.jda.commands;

import com.github.floington500.FriendlyBot.common.command.JoinCommand;
import com.github.floington500.FriendlyBot.common.lavaplayer.GuildMusicManager;
import com.github.floington500.FriendlyBot.common.lavaplayer.PlayerManager;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

/**
 * VoiceAction is an abstract class that implements the ICommand interface and provides a method for handling
 * voice-related commands.
 */
public abstract class VoiceAction implements ICommand {
    /**
     * Checks whether the user who triggered the command and the bot are in the same voice channel.
     * If the bot or the user are not in a voice channel, the bot will automatically join the user's voice channel.
     *
     * @param ctx the {@link SlashCommandInteractionEvent}.
     * @return true if the user and bot are in the same channel, false otherwise.
     */
    protected void checkChannel(SlashCommandInteractionEvent ctx) {
        final Member self = ctx.getGuild().getSelfMember();
        final Member user = ctx.getMember();

        final GuildVoiceState selfVoiceState = self.getVoiceState();
        final GuildVoiceState memberVoiceState = user.getVoiceState();

        // check to see if the user isn't in a voice channel
        if (!memberVoiceState.inAudioChannel()) {
            ctx.reply("You need to be in a channel in order to use this command!").setEphemeral(true).queue();
            return;
        }

        // check if the bot isn't in a voice channel
        if (!selfVoiceState.inAudioChannel()) {
            JoinCommand runCmd = new JoinCommand(true);
            runCmd.handle(ctx);
        }

        handleVoice(ctx);
    }

    /**
     * Gets the music manager for a guild if a track is playing.
     *
     * @param ctx the context of the interaction
     * @return the music manager if a track is playing
     * @throws IllegalStateException if there is no track currently playing
     */
    protected GuildMusicManager getMusicManager(SlashCommandInteractionEvent ctx) throws IllegalStateException {
        final Guild guild = ctx.getGuild();
        assert guild != null;
        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(guild);
        final AudioPlayer audioPlayer = musicManager.getAudioPlayer();

        if (audioPlayer.getPlayingTrack() == null) {
            throw new IllegalStateException("There is no track currently playing!");
        }

        return musicManager;
    }

    /**
     * Handles the command by first checking if the user and bot are in the same voice channel, then executing
     * the implementation-specific logic in the handleVoice method.
     *
     * @param ctx The CommandContext of the command event.
     */
    @Override
    public void handle(SlashCommandInteractionEvent ctx) {
        checkChannel(ctx);
    }

    /**
     * Implementation-specific logic for the command that should be executed.
     *
     * @param ctx the CommandContext of the command event.
     */
    protected abstract void handleVoice(SlashCommandInteractionEvent ctx);

}
