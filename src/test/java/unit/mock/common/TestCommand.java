package unit.mock.common;

import com.github.flo456123.FriendlyBot.jda.commands.ICommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class TestCommand implements ICommand {
    private boolean testStatus = false;
    private final String commandName;

    public TestCommand(String commandName) {
        this.commandName = commandName;
    }

    @Override
    public void handle(SlashCommandInteractionEvent ctx) {
        if (ctx.getName().equals(getName())){
            testStatus = true;
        }
    }

    @Override
    public String getName() {
        return commandName;
    }

    public boolean testSuccessful() {
        return testStatus;
    }
}
