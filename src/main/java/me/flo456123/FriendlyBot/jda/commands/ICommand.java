package me.flo456123.FriendlyBot.jda.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public interface ICommand {
    void handle(SlashCommandInteractionEvent ctx);

    String getName();

}