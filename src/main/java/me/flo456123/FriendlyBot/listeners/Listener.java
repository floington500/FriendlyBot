package me.flo456123.FriendlyBot.listeners;

import me.flo456123.FriendlyBot.command.CommandManager;
import me.flo456123.FriendlyBot.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class Listener extends ListenerAdapter {
    private final CommandManager commandManager = new CommandManager();

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        // Bot is ready!
    }

    @Override
    public void onGuildVoiceUpdate(GuildVoiceUpdateEvent event) {
        Member self = event.getGuild().getSelfMember();

        // Check if bot is in audio channel
        if (!self.getVoiceState().inAudioChannel()) {
            return;
        }

        // Check if people are still in the voice channel
        if (self.getVoiceState().getChannel().getMembers().size() > 1) {
            return;
        }

        // If the bot is alone in the voice channel, stop the player and leave
        final Guild guild = event.getGuild();
        PlayerManager.getInstance().getMusicManager(guild).stopPlayer();
        guild.getAudioManager().closeAudioConnection();
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        commandManager.handle(event);
    }

}