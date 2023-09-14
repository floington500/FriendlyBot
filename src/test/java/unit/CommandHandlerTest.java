package unit;

import com.github.floington500.FriendlyBot.jda.commands.handler.CommandHandlerImpl;
import com.github.floington500.FriendlyBot.jda.config.DefaultReplyMessages;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import unit.mock.jda.*;
import unit.mock.common.TestCommand;

public class CommandHandlerTest {

    @Test
    public void runValidCommandTest() {
        JDA mockJDA = new MockJDA();
        CommandHandlerImpl commandHandler = new CommandHandlerImpl(mockJDA);
        TestCommand testCommandA = new TestCommand("testcommand_a");
        TestCommand testCommandB = new TestCommand("testcommand_b");

        commandHandler.addCommand(Commands.slash("testcommand_a", "Not a real command."), testCommandA);
        commandHandler.addCommand(Commands.slash("testcommand_b", "Not a real command."), testCommandB);
        commandHandler.updateCommands();

        //Run test.
        commandHandler.handleCommand(new SlashCommandInteractionEvent(mockJDA, 1, new MockSlashCommandInteraction(testCommandA)));
        Assertions.assertTrue(testCommandA.testSuccessful());

        commandHandler.handleCommand(new SlashCommandInteractionEvent(mockJDA, 1, new MockSlashCommandInteraction(testCommandB)));
        Assertions.assertTrue(testCommandB.testSuccessful());
    }

    @Test
    public void runInvalidCommandTest() {
        JDA mockJDA = new MockJDA();

        CommandHandlerImpl commandHandler = new CommandHandlerImpl(mockJDA);
        TestCommand testCommandA = new TestCommand("testcommand_a");

        commandHandler.addCommand(Commands.slash("testcommand_b", "Not a command."), testCommandA);
        commandHandler.updateCommands();

        //Run test.
        MockSlashCommandInteraction mock = new MockSlashCommandInteraction(testCommandA);
        MockSlashCommandInteractionEvent event = new MockSlashCommandInteractionEvent(mockJDA, 1, mock);
        commandHandler.handleCommand(event);

        Assertions.assertFalse(testCommandA.testSuccessful());
        Assertions.assertEquals(DefaultReplyMessages.ERROR_COMMAND_DOESNT_EXIST, mock.getLastMessage());
    }
}
