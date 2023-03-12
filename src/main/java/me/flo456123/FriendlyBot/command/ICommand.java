package me.flo456123.FriendlyBot.command;

public interface ICommand {
    void handle(CommandContext ctx);

    String getName();

}