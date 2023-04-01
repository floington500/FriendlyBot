package unit.mock.jda;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;
import org.jetbrains.annotations.NotNull;

public class MockSlashCommandInteractionEvent extends SlashCommandInteractionEvent {
    public MockSlashCommandInteractionEvent(JDA api, long responseNumber, SlashCommandInteraction interaction) {
        super(api, responseNumber, interaction);
    }

    @Override
    public @NotNull ReplyCallbackAction reply(@NotNull String content) {
        return super.reply(content);
    }
}
