package me.flo456123.FriendlyBot.common.listeners;

import me.flo456123.FriendlyBot.common.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class OnGuildVoiceUpdate extends ListenerAdapter {
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
}