package unit.mock.jda;

import com.github.flo456123.FriendlyBot.jda.commands.ICommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.unions.GuildMessageChannelUnion;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.interactions.DiscordLocale;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.modals.Modal;
import net.dv8tion.jda.api.requests.restaction.interactions.ModalCallbackAction;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MockSlashCommandInteraction implements SlashCommandInteraction {
    MockReplyCallbackAction lastReplyCallbackAction = null;
    private final ICommand command;

    public MockSlashCommandInteraction(ICommand command) {
        this.command = command;
    }

    @Override
    public int getTypeRaw() {
        throw new RuntimeException("Method does not exist!");

    }

    @Override
    public String getToken() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public Guild getGuild() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public User getUser() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public Member getMember() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public boolean isAcknowledged() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public MessageChannelUnion getChannel() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public @NotNull GuildMessageChannelUnion getGuildChannel() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public DiscordLocale getUserLocale() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public JDA getJDA() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public @NotNull ModalCallbackAction replyModal(Modal modal) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public @NotNull ReplyCallbackAction deferReply() {
        lastReplyCallbackAction = new MockReplyCallbackAction();
        return lastReplyCallbackAction;
    }

    @Override
    public InteractionHook getHook() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public Command.Type getCommandType() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public @NotNull String getName() {
        return command.getName();
    }

    @Override
    public String getSubcommandName() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public String getSubcommandGroup() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public long getCommandIdLong() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public boolean isGuildCommand() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public List<OptionMapping> getOptions() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public long getIdLong() {
        throw new RuntimeException("Method does not exist!");
    }

    public String getLastMessage() {
        return lastReplyCallbackAction.lastMessage;
    }
}
