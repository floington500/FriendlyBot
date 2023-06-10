package com.github.flo456123.FriendlyBot.common.listeners;

import com.github.flo456123.FriendlyBot.common.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/**
 * This listener is used to detect when users leave or join a voice call.
 */
public class OnGuildVoiceUpdate extends ListenerAdapter {
    /**
     This listener is used to detect when users leave or join a voice call.
     Current features include:
     - Automatic disconnection when the bot is the only user left in the voice call.
     This method takes in an event parameter which is automatically passed in by JDA.
     @param event The event object that contains information about the user joining or leaving the voice call.
     */
    @Override
    public void onGuildVoiceUpdate(GuildVoiceUpdateEvent event) {
        Member self = event.getGuild().getSelfMember();

        // check if bot is in audio channel
        if (!self.getVoiceState().inAudioChannel()) {
            return;
        }

        // check if people are still in the voice channel
        if (self.getVoiceState().getChannel().asVoiceChannel().getMembers().size() > 1) {
            return;
        }

        // if the bot is alone in the voice channel, stop the player and leave
        final Guild guild = event.getGuild();
        PlayerManager.getInstance().getMusicManager(guild).stopPlayer();
        guild.getAudioManager().closeAudioConnection();
    }
}
