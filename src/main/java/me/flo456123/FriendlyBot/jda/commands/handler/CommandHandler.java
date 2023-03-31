package me.flo456123.FriendlyBot.jda.commands.handler;

import me.flo456123.FriendlyBot.jda.commands.ICommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

import java.util.Optional;

public interface CommandHandler {
    Optional<ICommand> getCommand(String command);
    void handleCommand(SlashCommandInteractionEvent event);
}
