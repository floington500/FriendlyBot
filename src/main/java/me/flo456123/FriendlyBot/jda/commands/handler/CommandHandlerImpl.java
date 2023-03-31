package me.flo456123.FriendlyBot.jda.commands.handler;

import me.flo456123.FriendlyBot.jda.commands.ICommand;
import me.flo456123.FriendlyBot.jda.config.DefaultReplyMessages;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

import java.util.*;
import java.util.stream.Collectors;

public class CommandHandlerImpl implements CommandHandler {
    private List<StoredCommandData> commandList = new ArrayList<>();
    private JDA jda;

    public CommandHandlerImpl(JDA jda) {
        this.jda = jda;
    }

    @Override
    public Optional<ICommand> getCommand(String command) {
        Optional<StoredCommandData> optionalCommandData = commandList.stream().filter(storedCommandData -> storedCommandData.data.getName().equals(command)).findFirst();

        return optionalCommandData.map(storedCommandData -> storedCommandData.instance);
    }

    public void addCommand(SlashCommandData slashData, ICommand commandClass) {
        if (getCommand(slashData.getName()).isEmpty()) {
            commandList.add(new StoredCommandData(slashData, commandClass));
        }
    }

    public void updateCommands() {
        Collection<CommandData> commandData = commandList.stream().map(storedCommandData -> storedCommandData.data).collect(Collectors.toList());
        jda.updateCommands().addCommands(commandData).queue();
    }

    @Override
    public void handleCommand(SlashCommandInteractionEvent event) {
        Optional<ICommand> optionalCommand = getCommand(event.getName());

        if (optionalCommand.isEmpty()){
            event.reply(DefaultReplyMessages.ERROR_COMMAND_DOESNT_EXIST).queue();
            return;
        }

        optionalCommand.get().handle(event);
    }

    private record StoredCommandData(SlashCommandData data, ICommand instance) {
    }
}