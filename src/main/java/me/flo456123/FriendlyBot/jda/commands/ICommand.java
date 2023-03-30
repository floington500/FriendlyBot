package me.flo456123.FriendlyBot.jda.commands;

public interface ICommand {
    void handle(CommandContext ctx);

    String getName();

}