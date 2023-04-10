package net.clf.common.command;

import net.clf.common.lavaplayer.GuildMusicManager;
import net.clf.common.lavaplayer.PlayerManager;
import net.clf.jda.commands.VoiceAction;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

/**
 * Used for setting the volume of a player in a guild
 */
public class VolumeCommand extends VoiceAction {

    /**
     * Sets a new volume for a player in the given CommandContext.
     *
     * @param ctx the CommandContext of the command event.
     */
    @Override
    protected void handleVoice(SlashCommandInteractionEvent ctx) {
        final Guild guild = ctx.getGuild();
        assert guild != null;
        final int newVolume = ctx.getOption("amount").getAsInt();

        final GuildMusicManager guildMusicManager = PlayerManager.getInstance().getMusicManager(guild);
        guildMusicManager.getAudioPlayer().setVolume(newVolume);

        ctx.replyFormat("New volume has been set to %d!", newVolume).queue();
    }

    @Override
    public String getName() {
        return "volume";
    }
}
