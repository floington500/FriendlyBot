package me.flo456123.FriendlyBot.jda.commands;

public interface ICommand {
    /**
     * Executes the logic for the command given the command context.
     *
     * @param ctx The command context to use.
     */
    void handle(CommandContext ctx);

    /**
     * Returns the name of the command.
     *
     * @return The name of the command.
     */
    String getName();
}
