package com.github.floington500.FriendlyBot.jda.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public interface ICommand {
    /**
     * Executes the logic for the command given the command context.
     *
     * @param ctx The command context to use.
     */
    void handle(SlashCommandInteractionEvent ctx);

    /**
     * Returns the name of the command.
     *
     * @return The name of the command.
     */
    String getName();
}
