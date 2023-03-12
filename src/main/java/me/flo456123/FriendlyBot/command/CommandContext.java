package me.flo456123.FriendlyBot.command;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public record CommandContext(SlashCommandInteractionEvent event) implements ICommandContext {

    @Override
    public Guild getGuild() {
        return this.event().getGuild();
    }

    public String getName() {
        return this.event().getName();
    }

}
