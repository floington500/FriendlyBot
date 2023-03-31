package me.flo456123.FriendlyBot.jda.commands;

import me.flo456123.FriendlyBot.common.command.*;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for managing commands in the bot. It maintains a list of commands
 * and is capable of handling command execution by finding and executing the corresponding command.
 */
public class CommandManager {
    private final List<ICommand> commands = new ArrayList<>();

    /**
     * Constructs a new CommandManager object.
     * Adds all available commands to the list of commands.
     */
    public CommandManager() {
        addCommand(new JoinCommand());
        addCommand(new LeaveCommand());
        addCommand(new PlayCommand());
        addCommand(new StopCommand());
        addCommand(new SkipCommand());
        addCommand(new NowPlayingCommand());
        addCommand(new QueueCommand());
        addCommand(new LoopCommand());
    }

    /**
     * Adds the specified command to the list of available commands.
     *
     * @param command The command to add to the list.
     */
    private void addCommand(ICommand command) {
        commands.add(command);
    }

    /**
     * Returns an instance of a command with the specified name.
     *
     * @param search The string name of the command to search for.
     * @return An instance of the command, or null if the command was not found.
     */
    @Nullable
    private ICommand getCommand(String search) {

        for (ICommand command : commands) {
            if (command.getName().equals(search)) {
                return command;
            }
        }

        return null;
    }

    /**
     * Handles SlashCommandInteractionEvents by finding and executing the corresponding command.
     *
     * @param event The SlashCommandInteractionEvent to handle.
     */
    public void handle(@NotNull SlashCommandInteractionEvent event) {
        String commandName = event.getName();

        ICommand command = getCommand(commandName);
        CommandContext ctx = new CommandContext(event);
        command.handle(ctx);
    }
}