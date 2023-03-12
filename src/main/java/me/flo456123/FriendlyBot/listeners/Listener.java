package me.flo456123.FriendlyBot.listeners;

import me.flo456123.FriendlyBot.command.CommandManager;
import me.flo456123.FriendlyBot.lavaplayer.GuildMusicManager;
import me.flo456123.FriendlyBot.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;
import org.jetbrains.annotations.NotNull;

public class Listener extends ListenerAdapter {
    private final CommandManager commandManager = new CommandManager();

    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
        // will make this reaction stuff later
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        // Bot is ready!
    }

    @Override
    public void onGuildVoiceUpdate(@NotNull GuildVoiceUpdateEvent event) {
        Member self = event.getGuild().getSelfMember();

        // Check to see if the bot is in a voice channel
        if (!self.getVoiceState().inAudioChannel()) {
            return;
        }

        // Check to see if there are still members in the voice channel
        if (self.getVoiceState().getChannel().asVoiceChannel().getMembers().size() > 1) {
            return;
        }

        // Clear songs if bot is alone, then leave
        final Guild guild = event.getGuild();
        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(guild);
        musicManager.getScheduler().setLooping(false);
        musicManager.getScheduler().getQueue().clear();
        musicManager.getScheduler().getPlayer().stopTrack();

        final AudioManager audioManager = guild.getAudioManager();
        String channelName = audioManager.getConnectedChannel().getName();
        audioManager.closeAudioConnection();
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        commandManager.handle(event);
    }
}