package me.flo456123.FriendlyBot.listeners;

import me.flo456123.FriendlyBot.command.CommandManager;
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
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        commandManager.handle(event);
    }

}