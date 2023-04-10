package net.clf.jda.commands.handler;

import net.clf.jda.commands.ICommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import java.util.Optional;

public interface CommandHandler {
    Optional<ICommand> getCommand(String command);
    void handleCommand(SlashCommandInteractionEvent event);
}
