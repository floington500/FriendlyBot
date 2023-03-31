package me.flo456123.FriendlyBot.common.listeners;

import me.flo456123.FriendlyBot.jda.commands.CommandManager;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class OnSlashCommands extends ListenerAdapter {
    private final CommandManager commandManager;

    public OnSlashCommands(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        commandManager.handle(event);
    }
}
