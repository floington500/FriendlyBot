package me.flo456123.FriendlyBot.jda.commands;

import me.flo456123.FriendlyBot.common.command.*;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private final List<ICommand> commands = new ArrayList<>();

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

    private void addCommand(ICommand command) {
        commands.add(command);
    }

    @Nullable
    private ICommand getCommand(String search) {

        for (ICommand command : commands) {
            if (command.getName().equals(search)) {
                return command;
            }
        }

        return null;
    }

    public void handle(@NotNull SlashCommandInteractionEvent event) {
        String commandName = event.getName();

        ICommand command = getCommand(commandName);
        CommandContext ctx = new CommandContext(event);
        command.handle(ctx);
    }
}