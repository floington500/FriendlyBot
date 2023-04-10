package net.clf.common.listeners;

import net.clf.jda.commands.handler.CommandHandler;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

/**
 * A listener class for handling slash commands.
 */
public class OnSlashCommands extends ListenerAdapter {
    /**
     * The command manager used to handle incoming slash command interactions.
     */
    private final CommandHandler commandManager;

    /**
     * Constructs a new {@link OnSlashCommands} instance with the given command manager.
     *
     * @param commandManager the {@link CommandHandler} to be used for handling slash commands
     */
    public OnSlashCommands(CommandHandler commandManager) {
        this.commandManager = commandManager;
    }

    /**
     * Called when a slash command interaction is received from the Discord API.
     *
     * @param event the {@link SlashCommandInteractionEvent} containing information about the received interaction
     */
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        commandManager.handleCommand(event);
    }
}
