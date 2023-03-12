package me.flo456123.FriendlyBot.command;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.sharding.ShardManager;

public interface ICommandContext {
    Guild getGuild();

    SlashCommandInteractionEvent event();

    default TextChannel getTextChannel() { return this.event().getChannel().asTextChannel(); }

    default Channel getChannel() {
        return this.event().getChannel();
    }

    default User getAuthor() {
        return this.event().getUser();
    }

    default Member getMember() {
        return this.event().getMember();
    }

    default JDA getJDA() {
        return this.event().getJDA();
    }

    default ShardManager getShardManager() {
        return this.getJDA().getShardManager();
    }

    default User getSelfUser() {
        return this.getJDA().getSelfUser();
    }

    default Member getSelfMember() {
        return this.getGuild().getSelfMember();
    }
}
